package com.minhnpa.coderschool.a9boarding.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.presenter.HomePresenter;
import com.minhnpa.coderschool.a9boarding.utils.IntentUtils;
import com.minhnpa.coderschool.a9boarding.utils.camera.CameraHelper;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class HomeFragment extends Fragment {

    @BindView(R.id.rvMain)
    RecyclerView rvMain;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabPost;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipe;

    HomePresenter presenter;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        presenter = new HomePresenter(getContext());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvMain.setLayoutManager(manager);
        rvMain.setAdapter(presenter.getAdapter());

        presenter.setUpFirebaseAdapter();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListener();
    }

    private void setupListener() {
        presenter.setListener(new HomePresenter.Listener() {
            @Override
            public void onLoadDone(boolean isDone) {
                if (isDone)
                    swipe.setRefreshing(false);
            }
        });

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startCreatePostActivity(getContext());
            }
        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.setUpFirebaseAdapter();
            }
        });
    }


}