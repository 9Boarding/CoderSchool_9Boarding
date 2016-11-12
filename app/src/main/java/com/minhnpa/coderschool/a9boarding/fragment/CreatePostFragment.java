package com.minhnpa.coderschool.a9boarding.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.api.ImgurApi;
import com.minhnpa.coderschool.a9boarding.db.FirebaseDbApi;
import com.minhnpa.coderschool.a9boarding.model.ImageResponse;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.model.User;
import com.minhnpa.coderschool.a9boarding.utils.BitmapScaler;
import com.minhnpa.coderschool.a9boarding.utils.FileUtils;
import com.minhnpa.coderschool.a9boarding.utils.PermissionUtils;
import com.minhnpa.coderschool.a9boarding.utils.RetrofitUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by baohq110 on 05/11/2016.
 */

public class CreatePostFragment extends Fragment{
	private static final int TAKE_PICTURE = 1000;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.et_price) EditText etPrice;
	@BindView(R.id.et_address) EditText etAddress;
	@BindView(R.id.et_num_m2) EditText etNumM2;
	@BindView(R.id.et_phone) EditText etPhone;
	@BindView(R.id.et_description) EditText etDescription;
	@BindView(R.id.iv_place) ImageView ivPlace;
	@BindView(R.id.iv_camera) ImageView ivCamera;

	private DatabaseReference mDatabaseReference;
	private String mStoredImageFile;
	private Post mPost;


	
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode == getActivity().RESULT_OK){
			if(requestCode == TAKE_PICTURE){
				takeImage();
				uploadImage();
			}
		}

	}

	private Post getInputToModel(){
		Calendar calendar = Calendar.getInstance();
		mPost.setPhoneNumber(etPhone.getText().toString().trim());
		mPost.setAddress(etAddress.getText().toString().trim());
		mPost.setPrice(etPrice.getText().toString().trim());
		mPost.setCreated_at(calendar.getTime().toString());
		mPost.setDescription(etDescription.getText().toString().trim());
		mPost.setUser(User.fromFirebaseUser(FirebaseAuth.getInstance().getCurrentUser()));
		return mPost;
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
		ivCamera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				launchCamera();
			}
		});
	}

	private void launchCamera(){
		if (PermissionUtils.checkExternal(getContext())){
			mPost = new Post();
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File file  = FileUtils.createPhotoFile(getContext());
			mStoredImageFile = file.getAbsolutePath();

			Log.d("BaoBao File", mStoredImageFile);

			Uri uri = FileUtils.getPhotoFileUri(this.getContext(), mStoredImageFile);

			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			if (intent.resolveActivity(getActivity().getPackageManager()) != null){
				startActivityForResult(intent, TAKE_PICTURE);
			}
		}else {
			PermissionUtils.requestExternal(getActivity());
		}
	}

	private void takeImage() {
		Bitmap rotatedBitmap = FileUtils.rotateBitmapOrientation(mStoredImageFile);
		Bitmap resizedBitmap = BitmapScaler.scaleToFitWidth(rotatedBitmap, 500);
		try {
			FileUtils.store(resizedBitmap, mStoredImageFile);
		} catch (IOException e) {
			Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		ivCamera.setImageBitmap(resizedBitmap);
	}

	private void uploadImage() {
		File file = new File(mStoredImageFile);
		RetrofitUtils.get(getString(R.string.IGMUR_CLIENT_ID))
				.create(ImgurApi.class)
				.create(FileUtils.partFromFile(file), FileUtils.requestBodyFromFile(file))
				.enqueue(new Callback<ImageResponse>() {
					@Override
					public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
						ImageResponse imageResponse = response.body();

						mPost.addImages(imageResponse.getData().getLink());
					}

					@Override
					public void onFailure(Call<ImageResponse> call, Throwable t) {
						Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});
	}



}
