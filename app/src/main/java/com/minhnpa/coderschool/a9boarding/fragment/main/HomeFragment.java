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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.adapter.PostViewHolder;
import com.minhnpa.coderschool.a9boarding.model.Post;
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

    FirebaseRecyclerAdapter<Post, PostViewHolder> adapter;

//    private OnFragmentInteractionListener mListener;

    private DatabaseReference mDatabaseReference;

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
            rvMain.setHasFixedSize(true);
            rvMain.setLayoutManager(manager);

            adapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class,
                    R.layout.item_post, PostViewHolder.class,
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("posts")) {
                @Override
                protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {
                    Log.e(TAG, "populateViewHolder: " + model);
                }
            };

            FirebaseRecyclerAdapter adapter = presenter.getAdapter();
            rvMain.setAdapter(adapter);
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
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setupListener() {

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startCreatePostActivity(getContext());
            }
        });
    }
}
