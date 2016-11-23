package com.minhnpa.coderschool.a9boarding.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.adapter.CommentViewHolder;
import com.minhnpa.coderschool.a9boarding.model.Comment;
import com.minhnpa.coderschool.a9boarding.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommentPresenter {

    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Comment, CommentViewHolder> mFirebaseAdapter;
    private Context mContext;

    public CommentPresenter(Context context) {
        mContext = context;

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        List<Comment> commentList = new ArrayList<>();
        Comment c1 = new Comment("Tue Nov 08 23:37:56 GMT+07:00 2016", "This is a comment", new User());
        Comment c2 = new Comment("Tue Nov 08 23:38:56 GMT+07:00 2016", "This is a comment 2", new User());
        Comment c3 = new Comment("Tue Nov 08 23:39:56 GMT+07:00 2016", "This is a comment 2", new User());

        commentList.add(c1);
        commentList.add(c2);
        commentList.add(c3);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(Comment.class, R.layout.item_comment,
                CommentViewHolder.class, (DatabaseReference) commentList) {
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment comment, int position) {
                viewHolder.bindComment(comment);
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }
        });
    }

    public FirebaseRecyclerAdapter getAdapter() {
        return this.mFirebaseAdapter;
    }
}
