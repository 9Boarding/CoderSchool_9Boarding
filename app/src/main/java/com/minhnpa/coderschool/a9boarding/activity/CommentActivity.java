package com.minhnpa.coderschool.a9boarding.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.presenter.CommentPresenter;

import butterknife.BindView;

public class CommentActivity extends AppCompatActivity {
    @BindView(R.id.rvComment)
    RecyclerView rvComment;

    CommentPresenter commentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        commentPresenter = new CommentPresenter(this);
        if (rvComment != null) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rvComment.setLayoutManager(layoutManager);
            rvComment.setAdapter(commentPresenter.getAdapter());
        }
    }
}
