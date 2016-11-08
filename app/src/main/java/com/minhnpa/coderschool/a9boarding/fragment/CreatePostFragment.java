package com.minhnpa.coderschool.a9boarding.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.db.FirebaseDbApi;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.model.User;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 05/11/2016.
 */

public class CreatePostFragment extends Fragment{
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.et_price) EditText etPrice;
	@BindView(R.id.et_address) EditText etAddress;
	@BindView(R.id.et_num_m2) EditText etNumM2;
	@BindView(R.id.et_phone) EditText etPhone;
	@BindView(R.id.et_description) EditText etDescription;
	@BindView(R.id.iv_place) ImageView ivPlace;

	private DatabaseReference mDatabaseReference;


	
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
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mDatabaseReference = FirebaseDatabase.getInstance().getReference();
		setupListener();
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
			case R.id.action_next:
				if (validateInput()){
					FirebaseDbApi.newPost(mDatabaseReference, getInputToModel());
					getActivity().onBackPressed();

				}else {
					Toast.makeText(getActivity(), "Some of field is emply", Toast.LENGTH_SHORT).show();
				}
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void launchCamera(){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	}
	private Post getInputToModel(){
		Post post = new Post();
		Calendar calendar = Calendar.getInstance();
		post.setPhoneNumber(etPhone.getText().toString().trim());
		post.setAddress(etAddress.getText().toString().trim());
		post.setPrice(etPrice.getText().toString().trim());
		post.setCreated_at(calendar.getTime().toString());
		post.setDescription(etDescription.getText().toString().trim());
		post.setUser(User.fromFirebaseUser(FirebaseAuth.getInstance().getCurrentUser()));
		return post;
	}

	private boolean validateInput(){
		if(etPrice.getText().toString().trim().isEmpty()) {
			return  false;
		}
		if (etAddress.getText().toString().toString().isEmpty()){
			return false;
		}
		if (etPhone.getText().toString().trim().isEmpty()){
			return false;
		}
		if(etNumM2.getText().toString().trim().isEmpty()){
			return false;
		}
		return true;
	}

	/**
	 * This method to setup the listener for view or widget
	 */
	private void setupListener() {

	}
}
