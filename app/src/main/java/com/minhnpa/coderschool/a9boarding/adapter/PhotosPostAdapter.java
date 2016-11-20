package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.minhnpa.coderschool.a9boarding.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by dangfiztssi on 20/11/2016.
 */

public class PhotosPostAdapter extends RecyclerView.Adapter<PhotosPostAdapter.myViewHolder>  {

    private List<String> photoList;

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
    public void onBindViewHolder(myViewHolder holder, int position) {
        Glide.with(holder.imgPhoto.getContext())
                .load(photoList.get(position))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
