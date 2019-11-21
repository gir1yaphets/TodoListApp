package com.example.todolistapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.todolistapp.R;
import com.example.todolistapp.db.EventDataHelper;
import com.example.todolistapp.model.EventModel;
import com.example.todolistapp.recyclerview.CategoryAdapter;
import com.example.todolistapp.recyclerview.CommonRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SummaryFragment extends Fragment {
    private Context context;

    private View view;
    private ImageView ivAdd;
    private EditText etSummary;
    private EditText etDetail;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;

    private List<EventModel> list;

    private OnActionListener listener;

    private static final String TAG = "SummaryFragment";

    public static final String SUMMARY_EVENT_LIST = "SUMMARY_EVENT_LIST";

    public interface OnActionListener{
        void onActionCallback(EventModel EventModel);
    }

    public static SummaryFragment newInstance(ArrayList<EventModel> events) {
        Bundle args = new Bundle();
        args.putSerializable(SUMMARY_EVENT_LIST, events);
        SummaryFragment fragment = new SummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();
        view = inflater.inflate(R.layout.fragment_summary_layout, container, false);
        list = (List<EventModel>) getArguments().getSerializable(SUMMARY_EVENT_LIST);
        initView();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            listener = (OnActionListener) context;
        }
    }

    private void initView() {
        etSummary = view.findViewById(R.id.etSummary);
        etDetail = view.findViewById(R.id.etDetailContent);

        ivAdd = view.findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String summary = etSummary.getText().toString();
                String detail = etDetail.getText().toString();

                etSummary.setText("");
                etDetail.setText("");

                if (!summary.isEmpty() && !detail.isEmpty()) {
                    EventModel eventModel = new EventModel();
                    eventModel.setSummaryTitle(summary);
                    eventModel.setDetailContent(detail);

                    list.add(eventModel);
                    adapter.notifyDataSetChanged();

                    EventDataHelper.getInstance().insert(eventModel);
                }
            }
        });

        recyclerView = view.findViewById(R.id.rvSummary);

        adapter = new CategoryAdapter(context, list);
        adapter.setMultipleViewType(false);
        adapter.setOnItemViewClickListener(new CommonRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (listener != null) {
                    listener.onActionCallback(list.get(position));
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }
}
