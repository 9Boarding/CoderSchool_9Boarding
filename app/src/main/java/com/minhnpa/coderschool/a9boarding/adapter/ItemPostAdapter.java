package com.minhnpa.coderschool.a9boarding.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class ItemPostAdapter extends RecyclerView.Adapter<ItemPostAdapter.myViewHolder> {
    public static final String TAG = ItemPostAdapter.class.getSimpleName();

    List<Post> postList;
    Context context;
    Listener listener;

    public interface Listener{
        void onClickPost(Post post);
    }

    public ItemPostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    public void setListener(Listener listener){
        this.listener = listener;
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

        @BindView(R.id.rlPostInfor)
        RelativeLayout postInfor;

        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void bindPost(final myViewHolder viewHolder, final Post post) {
        viewHolder.tvUserName.setText(post.getUser().getName());

        Glide.with(this.context)
                .load(post.getUser().getProfilePicUrl() + "")
                .placeholder(R.drawable.avatar_user_default)
                .crossFade()
                .into(viewHolder.civProfile);

        if (post.getPostAt() != null) {
            viewHolder.tvTimeStamp.setText(AppUtils.getRelativeTimeAgo(post.getPostAt()));
        }

        viewHolder.tvPrice.setText("$" + post.getPostInformation().getPrice());
        viewHolder.tvAddress.setText(post.getPostInformation().getAddress() + "");

        if (post.isBookMark())
            viewHolder.imbtnBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_active2));
        else
            viewHolder.imbtnBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark));

        //List Photos
        bindPhotos(viewHolder.rvPhotos, post.getImages());

        viewHolder.imbtnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.setBookMark(!post.isBookMark());
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
                animation.setInterpolator(new AccelerateInterpolator());
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        if (post.isBookMark()) {
                            viewHolder.imbtnBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_active2));
                            Post.addBookMark(post);
                        } else {
                            viewHolder.imbtnBookmark.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark));
                            Post.removeBookMark(post);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                viewHolder.imbtnBookmark.startAnimation(animation);
            }
        });

        viewHolder.postInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickPost(post);
            }
        });


    }

    private void bindPhotos(RecyclerView rvPhotos, List<String> photoList) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(rvPhotos.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvPhotos.setItemAnimator(new DefaultItemAnimator());
        rvPhotos.setLayoutManager(manager);
        rvPhotos.setAdapter(new PhotosPostAdapter(photoList));
    }
}