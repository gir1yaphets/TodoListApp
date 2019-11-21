package com.example.todolistapp.recyclerview;

import android.content.Context;
import android.widget.TextView;

import com.example.todolistapp.R;
import com.example.todolistapp.model.EventModel;

import java.util.List;

public class CategoryAdapter extends CommonRecyclerAdapter<EventModel> {

    public CategoryAdapter(Context mContext, List<EventModel> data) {
        super(mContext, data);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_item_summary;
    }

    @Override
    protected int getLayoutResId(int type) {
        return 0;
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }

    @Override
    protected void convertView(CommonRecyclerHolder holder, EventModel data) {
        TextView textView = (TextView) holder.getViewById(R.id.tvCategoryName);
        textView.setText(data.getSummaryTitle());
    }

    @Override
    protected void convertView(CommonRecyclerHolder holder, EventModel data, int type) {

    }
}
