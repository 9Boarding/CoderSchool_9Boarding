package com.minhnpa.coderschool.a9boarding.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.minhnpa.coderschool.a9boarding.R.id.rvPhotos;

/**
 * Created by DangF on 11/08/16.
 */

public class PostItemAdapter extends RecyclerView.Adapter<PostItemAdapter.myViewHolder> {
    public static final String TAG = PostItemAdapter.class.getSimpleName();

    List<Post> postList;

    public PostItemAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Post post = postList.get(position);
        bindPost(holder, post);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

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

        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void bindPost(myViewHolder viewHolder, Post post) {
        //TODO: load image avatar, photos
        viewHolder.tvUserName.setText(post.getUser().getUserInformation().getName());

        //TODO: add attribute Username for Post

        if (post.getPostAt() != null) {
            viewHolder.tvTimeStamp.setText(AppUtils.getRelativeTimeAgo(post.getPostAt()));
        }
//        tvLocation.setText(post.getPostInformation().getAddress());

//        tvPrice.setText("$" + post.getPostInformation().getPrice() + " per month");



        //List Photos
        bindPhotos(viewHolder.rvPhotos, post.getImages());

    }

    private void bindPhotos(RecyclerView rvPhotos, List<String> photoList) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(rvPhotos.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvPhotos.setItemAnimator(new DefaultItemAnimator());
        rvPhotos.setLayoutManager(manager);
        rvPhotos.setAdapter(new PhotosPostAdapter(photoList));
    }
}