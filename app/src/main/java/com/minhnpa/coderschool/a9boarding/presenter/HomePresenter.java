package com.minhnpa.coderschool.a9boarding.presenter;

import android.content.Context;

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
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.item_post,
                PostViewHolder.class, mDatabaseReference.child("posts")) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {
                viewHolder.bindPost(model);
//                mFirebaseAdapter.notifyDataSetChanged();
            }
        };
    }

    public FirebaseRecyclerAdapter getAdapter() {
        return this.mFirebaseAdapter;
    }
}
