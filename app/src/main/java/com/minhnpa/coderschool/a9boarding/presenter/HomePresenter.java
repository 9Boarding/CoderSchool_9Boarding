package com.minhnpa.coderschool.a9boarding.presenter;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhnpa.coderschool.a9boarding.adapter.ItemPostAdapter;
import com.minhnpa.coderschool.a9boarding.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DangF on 11/08/16.
 */

public class HomePresenter {
    public static final String TAG = HomePresenter.class.getSimpleName();

    private DatabaseReference mDatabaseReference;
    private Context mContext;
    private ItemPostAdapter adapter;
    private List<Post> postList = new ArrayList<>();
    private Listener listener;

    public interface Listener{
        void onLoadDone(boolean isDone);
        void onClickPost(Post post);
    }

    public void setListener(final Listener listener){
        this.listener = listener;
        adapter.setListener(new ItemPostAdapter.Listener() {
            @Override
            public void onClickPost(Post post) {
                listener.onClickPost(post);
            }
        });
    }

    public HomePresenter(Context context) {
        mContext = context;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new ItemPostAdapter(context, postList);
    }

    public void setUpFirebaseAdapter() {
        mDatabaseReference.child("posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postList.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot dataChild: dataSnapshot.getChildren()) {
                    Post post = Post.newInstance(dataChild);
                    postList.add(0, post);
                    adapter.notifyItemInserted(0);
                }

                listener.onLoadDone(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    public ItemPostAdapter getAdapter(){
        return this.adapter;
    }
}