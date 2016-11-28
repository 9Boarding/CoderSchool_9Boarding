package com.minhnpa.coderschool.a9boarding.presenter;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhnpa.coderschool.a9boarding.adapter.PostItemAdapter;
import com.minhnpa.coderschool.a9boarding.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DangF on 11/08/16.
 */

public class HomePresenter {
    public static final String TAG = HomePresenter.class.getSimpleName();

    private DatabaseReference mDatabaseReference;
//    private FirebaseRecyclerAdapter<Post, PostItemAdapter> mFirebaseAdapter;
    private Context mContext;
    private PostItemAdapter adapter;
    private List<Post> postList = new ArrayList<>();

    public HomePresenter(Context context) {
        mContext = context;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new PostItemAdapter(postList);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mDatabaseReference.child("posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataChild: dataSnapshot.getChildren()) {
                    Post post = Post.newInstance(dataChild);
                    postList.add(post);
                    adapter.notifyItemInserted(postList.size());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

//    public FirebaseRecyclerAdapter getAdapter() {
//        return this.mFirebaseAdapter;
//    }

    public PostItemAdapter getAdapter(){
        return this.adapter;
    }
}