package com.minhnpa.coderschool.a9boarding.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.presenter.CommentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends AppCompatActivity {
    @BindView(R.id.rvComment)
    RecyclerView rvComment;

    CommentPresenter commentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        commentPresenter = new CommentPresenter(this);
        if (rvComment != null) {
            rvComment.setHasFixedSize(true);
            rvComment.setLayoutManager(new LinearLayoutManager(this));
            rvComment.setAdapter(commentPresenter.getAdapter());
        }
    }
}
