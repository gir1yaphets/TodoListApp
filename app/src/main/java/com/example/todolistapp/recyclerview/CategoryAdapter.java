package com.example.todolistapp.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolistapp.R;
<<<<<<< HEAD
import com.example.todolistapp.db.EventDataHelper;
import com.example.todolistapp.model.EventModel;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
=======
import com.example.todolistapp.model.CategoryModel;
>>>>>>> feature/Exercise_5

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

    @Override
<<<<<<< HEAD
    protected void convertView(CommonRecyclerHolder holder, EventModel data, int type) {

    }

    private void showDialog(final EventModel data) {
        final String[] items = new String[]{STATUS_TO_DO, STATUS_DOING, STATUS_DONE};

        new QMUIDialog.MenuDialogBuilder(mContext)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.setStatus(items[which]);
                        EventDataHelper.getInstance().update(data);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }

                }).show();
=======
    protected void convertView(CommonRecyclerHolder holder, CategoryModel data, int type) {
>>>>>>> feature/Exercise_5

    }
}
