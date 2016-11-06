package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DangF on 11/08/16.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    public static final String TAG = PostViewHolder.class.getSimpleName();

    @BindView(R.id.imgAvatar)
    public CircleImageView imgAvatar;

    @BindView(R.id.tvUserName)
    public TextView tvUserName;

    @BindView(R.id.tvLocation)
    public TextView tvLocation;

    @BindView(R.id.tvTimeStamp)
    public TextView tvTimeStamp;

    @BindView(R.id.rvPhoto)
    public RecyclerView rvPhoto;

    @BindView(R.id.tvPrice)
    public TextView tvPrice;

    @BindView(R.id.btnComment)
    public Button btnComment;

    @BindView(R.id.btnShare)
    public Button btnShare;

    @BindView(R.id.btnBookmark)
    public Button btnBookmark;


    public PostViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindPost(Post post) {
        Log.e(TAG, "bindPost: ");
        //TODO: load image avatar, photos

        //TODO: add attribute Username for Post

        tvTimeStamp.setText(post.getCreatedAt());
        tvLocation.setText(post.getAddress());

        //TODO: load photos into RecyclerView

        tvPrice.setText("$" + post.getPrice() + " per month");
    }
}