package com.minhnpa.coderschool.a9boarding.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.adapter.PostViewHolder;
import com.minhnpa.coderschool.a9boarding.model.Post;

/**
 * Created by DangF on 11/08/16.
 */

public class HomePresenter {
    public static final String TAG = HomePresenter.class.getSimpleName();

    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mFirebaseAdapter;
    private Context mContext;

    public HomePresenter(Context context) {
        mContext = context;

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Log.e(TAG, "database " + mDatabaseReference.child("posts").getRef().getDatabase());

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){

        Log.e(TAG, "setUpFirebaseAdapter");
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.item_post, PostViewHolder.class, mDatabaseReference.child("posts")) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {
                Log.e(TAG, "populateViewHolder: ");

                viewHolder.bindPost(model);
                viewHolder.tvPrice.setText(model.getPrice() + "");

                if(!model.getImages().isEmpty()){
                    Log.d("BaoBao model", model.getImages().toString());
                    Glide.with(viewHolder.itemView.getContext())
                            .load(model.getImages().get(0))
                            .into(viewHolder.ivPhoto);
                }
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int sum = mFirebaseAdapter.getItemCount();
                Log.e(TAG, "onItemRangeInserted: " + sum );
            }
        });
    }

    public FirebaseRecyclerAdapter getAdapter(){
        return this.mFirebaseAdapter;
    }
}
