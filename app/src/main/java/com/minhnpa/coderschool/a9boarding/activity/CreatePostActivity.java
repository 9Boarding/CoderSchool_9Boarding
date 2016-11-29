package com.minhnpa.coderschool.a9boarding.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class CreatePostActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreatePostActivity.class);
        return intent;
    }

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
    EditText edtDescription;
    @BindView(R.id.rvImageChose)
    RecyclerView rvImageChose;

    private DatabaseReference mDatabaseReference;
    private String mStoredImageFile;
    private Post mPost = new Post();

    CreatePostPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new CreatePostPresenter(this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImageChose.setLayoutManager(manager);
        rvImageChose.setItemAnimator(new DefaultItemAnimator());
        rvImageChose.setAdapter(presenter.getAdapter());

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
//        setupListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.action_next:
                if (validateInput()) {
                    FirebaseDbApi.newPost(mDatabaseReference, getInputToModel());
                    onBackPressed();

                } else {
                    Toast.makeText(this, "Some of field is emply", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Post getInputToModel() {
        Calendar calendar = Calendar.getInstance();
        User user = User.fromFirebaseUser(FirebaseAuth.getInstance().getCurrentUser());
        PostInformation postInformation = new PostInformation();

        postInformation.setAddress(edtAddress.getText().toString());
        //postInformation.setArea(etArea.getText().toString());
        postInformation.setDescription(edtDescription.getText().toString());
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
//
//    /**
//     * This method to setup the listener for view or widget
//     */
//    private void setupListener() {
//        ivCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.choosePhoto();
//            }
//        });
//
//    }
//
//    private void takeImage() {
//        Bitmap rotatedBitmap = FileUtils.rotateBitmapOrientation(mStoredImageFile);
//        Bitmap resizedBitmap = BitmapScaler.scaleToFitWidth(rotatedBitmap, 500);
//        try {
//            FileUtils.store(resizedBitmap, mStoredImageFile);
//        } catch (IOException e) {
//            Toast.makeText(CreatePostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        ivCamera.setImageBitmap(resizedBitmap);
//    }

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
                        Toast.makeText(CreatePostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
