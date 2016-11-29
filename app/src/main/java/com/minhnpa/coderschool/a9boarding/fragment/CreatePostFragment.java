package com.minhnpa.coderschool.a9boarding.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.api.ImgurApi;
import com.minhnpa.coderschool.a9boarding.db.FirebaseDbApi;
import com.minhnpa.coderschool.a9boarding.model.ImageResponse;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.model.PostInformation;
import com.minhnpa.coderschool.a9boarding.model.User;
import com.minhnpa.coderschool.a9boarding.presenter.CreatePostPresenter;
import com.minhnpa.coderschool.a9boarding.utils.BitmapScaler;
import com.minhnpa.coderschool.a9boarding.utils.FileUtils;
import com.minhnpa.coderschool.a9boarding.utils.PermissionUtils;
import com.minhnpa.coderschool.a9boarding.utils.RetrofitUtils;
import com.minhnpa.coderschool.a9boarding.utils.camera.CameraHelper;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryHelper;

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

public class CreatePostFragment extends Fragment {
    private static final int TAKE_PICTURE = 1000;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edtPrice)
    EditText edtPrice;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtArea)
    EditText edtArea;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtDescription)
//    EditText edtDescription;
//    @BindView(R.id.ivCamera)
    ImageView ivCamera;
    @BindView(R.id.rvImageChose)
    RecyclerView rvImageChose;

    private DatabaseReference mDatabaseReference;
    private String mStoredImageFile;
    private Post mPost;

    CreatePostPresenter presenter;


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
        View view = inflater.inflate(R.layout.activity_create_post, container, false);

        ButterKnife.bind(this, view);

        // Setup toolbar
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        presenter = new CreatePostPresenter(this.getActivity());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvImageChose.setLayoutManager(manager);


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

        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.action_next:
                if (validateInput()) {
                    FirebaseDbApi.newPost(mDatabaseReference, getInputToModel());
                    getActivity().onBackPressed();

                } else {
                    Toast.makeText(getActivity(), "Some of field is emply", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == getActivity().RESULT_OK) {
//            if (requestCode == TAKE_PICTURE) {
//                takeImage();
//                uploadImage();
//            }
//        }
//
//    }

    private Post getInputToModel() {
        Calendar calendar = Calendar.getInstance();
        User user = User.fromFirebaseUser(FirebaseAuth.getInstance().getCurrentUser());
        PostInformation postInformation = new PostInformation();

        postInformation.setAddress(edtAddress.getText().toString());
        //postInformation.setArea(etArea.getText().toString());
//        postInformation.setDescription(edtDescription.getText().toString());
        postInformation.setPhone(edtPhone.getText().toString()); // Change to spinner - select phone number list from user
        postInformation.setPrice(edtPrice.getText().toString());
        mPost.setPostInformation(postInformation);
        mPost.setPostAt(calendar.getTime().toString());
        mPost.setUser(user);

        return mPost;
    }

    private boolean validateInput() {
        if (edtPrice.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (edtAddress.getText().toString().isEmpty()) {
            return false;
        }
        if (edtPhone.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (edtArea.getText().toString().trim().isEmpty()) {
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
//                launchCamera();
//                presenter.choosePhoto();
            }
        });
    }

    private void launchCamera() {
        if (PermissionUtils.checkExternal(getContext())) {
            mPost = new Post();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = FileUtils.createPhotoFile(getContext());
            mStoredImageFile = file.getAbsolutePath();

            Log.d("BaoBao File", mStoredImageFile);

            Uri uri = FileUtils.getPhotoFileUri(this.getContext(), mStoredImageFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(intent, TAKE_PICTURE);
            }
        } else {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE){
            if(resultCode == Activity.RESULT_OK)
                presenter.cameraHelper.onSuccess(data);
            else
                presenter.cameraHelper.onFailure(new Exception(new Throwable("Camera capture fail, check CameraHelper class please")));

        }
        else if(requestCode == GalleryHelper.PICK_IMAGE_REQUEST){
            if(resultCode == Activity.RESULT_OK)
                presenter.galleryHelper.onSuccess(data);
            else
                presenter.galleryHelper.onFailure(new Exception(new Throwable("Camera capture fail, check GalleryHelper class please")));
        }
    }
}