package com.example.todolistapp.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolistapp.R;
import com.example.todolistapp.db.EventDataHelper;
import com.example.todolistapp.model.EventModel;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.List;

import static com.example.todolistapp.model.EventModel.STATUS_DOING;
import static com.example.todolistapp.model.EventModel.STATUS_DONE;
import static com.example.todolistapp.model.EventModel.STATUS_TO_DO;

public class DetailAdapter extends CommonRecyclerAdapter<EventModel> {
    public DetailAdapter(Context mContext, List<EventModel> mData) {
        super(mContext, mData);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_item_detail;
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
    protected void convertView(CommonRecyclerHolder holder, final EventModel data) {
        TextView textView = (TextView) holder.getViewById(R.id.tvDetailName);
        textView.setText(data.getEventContent());

        final TextView tvStatus = (TextView) holder.getViewById(R.id.tvStatus);
        tvStatus.setText(data.getStatus());
        tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(data);
            }
        });

        tvStatus.setText(data.getStatus());

        ImageView ivDelete = (ImageView) holder.getViewById(R.id.ivDelete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(data);
                EventDataHelper.getInstance().deleteEvent(data);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void convertView(CommonRecyclerHolder holder, EventModel data, int type) {

    }

    private void showDialog(final EventModel data) {
        final String[] items = new String[]{STATUS_TO_DO, STATUS_DOING, STATUS_DONE};

        new QMUIDialog.MenuDialogBuilder(mContext)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.setStatus(items[which]);
                        EventDataHelper.getInstance().updateEvent(data);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }

                }).show();

    }
}
