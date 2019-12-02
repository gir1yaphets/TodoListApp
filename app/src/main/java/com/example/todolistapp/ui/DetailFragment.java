package com.example.todolistapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolistapp.R;
import com.example.todolistapp.db.EventDataHelper;
import com.example.todolistapp.model.CategoryModel;
import com.example.todolistapp.model.EventModel;
import com.example.todolistapp.recyclerview.DetailAdapter;

import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailFragment extends Fragment {
    private Context context;

    private View view;

    private ImageView ivAdd;
    private EditText etDetail;
    private RecyclerView recyclerView;
    private DetailAdapter adapter;

    private CategoryModel categoryModel;

    private TextView tvCategory;

    private TextView tvSorting;

    private EditText etSearchContent;

    private TextView tvSearch;

    private int eventId = 0;

    public static final String CATEGORY_MODEL = "CATEGORY_MODEL";

    public static DetailFragment newInstance(Bundle args) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();
        view = inflater.inflate(R.layout.fragment_detail_layout, container, false);
        initView(getArguments());
        return view;
    }

    private void initView(Bundle args) {
        if (args != null) {
            categoryModel = (CategoryModel) args.getSerializable(CATEGORY_MODEL);

            tvCategory = view.findViewById(R.id.tvCategoryTitle);
            tvCategory.setText(categoryModel.getCategory());

            etDetail = view.findViewById(R.id.etDetailContent);

            tvSorting = view.findViewById(R.id.tvSorting);
            tvSorting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(categoryModel.getEventList(), new Comparator<EventModel>() {
                        @Override
                        public int compare(EventModel t1, EventModel t2) {
                            return t1.getEventContent().compareTo(t2.getEventContent());
                        }
                    });

                    adapter.setData(categoryModel.getEventList());
                    adapter.notifyDataSetChanged();
                }
            });

            etSearchContent = view.findViewById(R.id.etSearchContent);
            tvSearch = view.findViewById(R.id.tvSearch);
            tvSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String searchContent = etSearchContent.getText().toString();
                    categoryModel.setEventList(EventDataHelper.getInstance().queryEvent(searchContent));
                    adapter.setData(categoryModel.getEventList());
                    adapter.notifyDataSetChanged();
                }
            });

            ivAdd = view.findViewById(R.id.ivAdd);
            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String detail = etDetail.getText().toString();

                    etDetail.setText("");

                    if (!detail.isEmpty() && categoryModel != null) {
                        EventModel eventModel = new EventModel();
                        eventModel.setId(eventId++);
                        eventModel.setEventContent(detail);
                        eventModel.setCategoryId(categoryModel.getId());
                        categoryModel.getEventList().add(eventModel);

                        EventDataHelper.getInstance().insertEvent(eventModel);

                        adapter.notifyDataSetChanged();
                    }
                }
            });

            recyclerView = view.findViewById(R.id.rvDetail);

            adapter = new DetailAdapter(context, categoryModel.getEventList());
            adapter.setMultipleViewType(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);

            runLayoutAnimation(recyclerView);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.common_dev_list_in_anim);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
