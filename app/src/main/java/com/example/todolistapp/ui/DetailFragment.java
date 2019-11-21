package com.example.todolistapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todolistapp.R;
import com.example.todolistapp.model.EventModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private View view;
    private TextView tvDetailContent;

    public static final String DETAIL_EVENT_MODEL = "DETAIL_EVENT_MODEL";

    public static DetailFragment newInstance(Bundle args) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_detail_layout, container, false);
        initView(getArguments());
        return view;
    }

    private void initView(Bundle args) {
        if (args != null) {
            tvDetailContent = view.findViewById(R.id.tvDetailContent);
            EventModel eventModel = (EventModel) args.getSerializable(DETAIL_EVENT_MODEL);
            tvDetailContent.setText(eventModel.getDetailContent());
        }
    }
}
