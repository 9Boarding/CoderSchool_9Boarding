package com.minhnpa.coderschool.a9boarding.presenter;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.adapter.CommentViewHolder;
import com.minhnpa.coderschool.a9boarding.model.Comment;

public class CommentPresenter {

    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Comment, CommentViewHolder> mFirebaseAdapter;

    public CommentPresenter(Context context) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(Comment.class, R.layout.item_comment,
                CommentViewHolder.class, mDatabaseReference.child("comments")) {
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment comment, int position) {
                viewHolder.bindComment(comment);
            }
        };
    }

    public FirebaseRecyclerAdapter getAdapter() {
        return this.mFirebaseAdapter;
    }
}
