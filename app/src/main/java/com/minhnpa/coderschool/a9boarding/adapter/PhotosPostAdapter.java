package com.minhnpa.coderschool.a9boarding.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.activity.PhotoActivity;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by dangfiztssi on 20/11/2016.
 */

public class PhotosPostAdapter extends RecyclerView.Adapter<PhotosPostAdapter.myViewHolder>  {

    private List<String> photoList;

    public interface Listener{
        void onClickPhoto(int post);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        public myViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView)itemView.findViewById(R.id.photoSample);
        }
    }

    public PhotosPostAdapter(List<String> photoList) {
        this.photoList = photoList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_post,parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        Glide.with(holder.imgPhoto.getContext())
                .load(photoList.get(position))
                .into(holder.imgPhoto);

        holder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.imgPhoto.getContext();
                Intent i = new Intent(holder.imgPhoto.getContext(), PhotoActivity.class);
                i.putExtra("url", photoList.get(position));
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context,
                        ((View) holder.imgPhoto),
                        "previewPhoto");
                holder.imgPhoto.getContext().startActivity(i, optionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
