package com.example.todolistapp.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolistapp.R;
import com.example.todolistapp.db.EventDataHelper;
import com.example.todolistapp.model.CategoryModel;

import java.util.List;


public class CategoryAdapter extends CommonRecyclerAdapter<CategoryModel> {

    public CategoryAdapter(Context mContext, List<CategoryModel> data) {
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
    protected void convertView(CommonRecyclerHolder holder, final CategoryModel data) {
        TextView textView = (TextView) holder.getViewById(R.id.tvCategoryName);
        textView.setText(data.getCategory());


        ImageView ivDelete = (ImageView) holder.getViewById(R.id.ivDelete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(data);
                EventDataHelper.getInstance().delete(data);
                notifyDataSetChanged();
            }
        });
    }

    protected void convertView(CommonRecyclerHolder holder, CategoryModel data, int type) {

    }
}
