package com.minhnpa.coderschool.a9boarding.fragment.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.presenter.HomePresenter;
import com.minhnpa.coderschool.a9boarding.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class HomeFragment extends Fragment {

    @BindView(R.id.rvMain)
    RecyclerView rvMain;

    @BindView(R.id.fabAdd)
    FloatingActionButton fabPost;

    HomePresenter presenter;

//    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
    }

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

        if (rvMain != null) {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            rvMain.setLayoutManager(manager);
            rvMain.setAdapter(presenter.getAdapter());
        } else {
            Log.e(TAG, "rv null ");
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListener();
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setupListener(){

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startCreatePostActivity(getContext());
            }
        });
    }
}