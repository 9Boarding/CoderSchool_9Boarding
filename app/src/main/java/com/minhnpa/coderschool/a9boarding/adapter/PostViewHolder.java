package com.minhnpa.coderschool.a9boarding.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DangF on 11/08/16.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    public static final String TAG = PostViewHolder.class.getSimpleName();

    @BindView(R.id.civProfile)
    public CircleImageView civProfile;

    @BindView(R.id.tvUserName)
    public TextView tvUserName;

    @BindView(R.id.tvAddress)
    public TextView tvAddress;

    @BindView(R.id.tvTimeStamp)
    public TextView tvTimeStamp;

    @BindView(R.id.tvPrice)
    public TextView tvPrice;

    @BindView(R.id.imbtnShare)
    public ImageButton imbtnShare;

    @BindView(R.id.imbtnBookmark)
    public ImageButton imbtnBookmark;

    @BindView(R.id.rvPhotos)
    RecyclerView rvPhotos;

    public View itemView;

    private Context context;


    public PostViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);

        this.context = itemView.getContext();
    }

    public void bindPost(Post post) {
        //TODO: load image avatar, photos
        tvUserName.setText(post.getUser().getUserInformation().getName());

        //TODO: add attribute Username for Post

        if (post.getPostAt() != null) {
            tvTimeStamp.setText(AppUtils.getRelativeTimeAgo(post.getPostAt()));
        }
//        tvLocation.setText(post.getPostInformation().getAddress());

//        tvPrice.setText("$" + post.getPostInformation().getPrice() + " per month");

        //List Photos
        bindPhotos(post.getImages());

    }

    private void bindPhotos(List<String> photoList) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvPhotos.setItemAnimator(new DefaultItemAnimator());
        rvPhotos.setLayoutManager(manager);
        rvPhotos.setAdapter(new PhotosPostAdapter(photoList));
    }
}