package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.utils.AppUtils;

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

    @BindView(R.id.ivPhoto)
    public ImageView ivPhoto;

    @BindView(R.id.tvPrice)
    public TextView tvPrice;

    @BindView(R.id.btnComment)
    public Button btnComment;

    @BindView(R.id.btnShare)
    public Button btnShare;

    @BindView(R.id.btnBookmark)
    public Button btnBookmark;

    public View itemView;


    public PostViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bindPost(Post post) {
        //TODO: load image avatar, photos
        tvUserName.setText(post.getUser().getUserInformation().getName());

        //TODO: add attribute Username for Post
        tvTimeStamp.setText(AppUtils.getRelativeTimeAgo(post.getPostAt()));
        tvLocation.setText(post.getPostInformation().getAddress());

        //TODO: load photos into RecyclerView
        if (!post.getImages().isEmpty()) {
            Glide.with(itemView.getContext())
                    .load(post.getImages().get(0))
                    .into(ivPhoto);
        }

        tvPrice.setText("$" + post.getPostInformation().getPrice() + " per month");
    }
}