package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.minhnpa.coderschool.a9boarding.R;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public class ItemImageChoseAdapter extends RecyclerView.Adapter<ItemImageChoseAdapter.viewHolder> {

    List<Object> listPhotos;

    public ItemImageChoseAdapter(List<Object> listPhotos) {
        this.listPhotos = listPhotos;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_chose,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        if(position < listPhotos.size()){
            Object f = listPhotos.get(position);

            Glide.with(holder.img.getContext())
                    .load(f)
                    .into(holder.img);
            holder.btnClear.setVisibility(View.VISIBLE);
        }
        else{
            holder.btnClear.setVisibility(View.GONE);
        }

        holder.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPhotos.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhotos.size()+1;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgChose)
        ImageView img;
        @BindView(R.id.btnClear)
        ImageButton btnClear;

        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
