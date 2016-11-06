package com.minhnpa.coderschool.a9boarding.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.minhnpa.coderschool.a9boarding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 05/11/2016.
 */

public class CreatePostFragment extends Fragment{
	@BindView(R.id.toolbar) Toolbar toolbar;
	
	public static CreatePostFragment newInstance() {

		Bundle args = new Bundle();
		
		CreatePostFragment fragment = new CreatePostFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create_post, container, false);

		ButterKnife.bind(this, view);

		// Setup toolbar
		AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
		appCompatActivity.setSupportActionBar(toolbar);
		appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// set new toolbar title
//		appCompatActivity.getSupportActionBar().setTitle(R.string.new_post);

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_new_post, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){
			case android.R.id.home:
				getActivity().onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
