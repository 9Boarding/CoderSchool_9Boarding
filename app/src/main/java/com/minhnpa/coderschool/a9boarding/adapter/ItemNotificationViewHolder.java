package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.minhnpa.coderschool.a9boarding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DangF on 11/16/16.
 */

public class ItemNotificationViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = ItemNotificationViewHolder.class.getSimpleName();

    @BindView(R.id.swipe)
    SwipeLayout swipe;

    @BindView(R.id.lnRight)
    LinearLayout lnDelete;

    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;

    @BindView(R.id.tvMessage)
    TextView msg;

    public View itemView;

    public ItemNotificationViewHolder(View itemView) {
        super(itemView);
        itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(){
    }


}
