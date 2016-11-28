package com.minhnpa.coderschool.a9boarding.presenter;

import android.content.Context;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.adapter.PostViewHolder;
import com.minhnpa.coderschool.a9boarding.model.Post;

import java.util.Map;
import java.util.Objects;

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
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.item_post,
//                PostViewHolder.class, mDatabaseReference.child("posts")) {
//            @Override
//            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {
//                viewHolder.bindPost(model);
////                mFirebaseAdapter.notifyDataSetChanged();
//            }
//        };

        mDatabaseReference.child("posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: " + dataSnapshot);
                for (DataSnapshot dataChild: dataSnapshot.getChildren()) {
//                    Map<String, Objects> post = ((Map<String, Objects>) dataChild.getValue());
//                    Log.d(TAG, "onDataChange: " + post.get("bookmarks_count"));
//                    Log.d(TAG, "onDataChange: " + post.get("comments_count"));

                    Post post = Post.newInstance(dataChild);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    public FirebaseRecyclerAdapter getAdapter() {
        return this.mFirebaseAdapter;
    }
}