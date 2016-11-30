package com.minhnpa.coderschool.a9boarding.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.activity.CreatePostActivity;
import com.minhnpa.coderschool.a9boarding.adapter.ItemImageChoseAdapter;
import com.minhnpa.coderschool.a9boarding.api.ImgurApi;
import com.minhnpa.coderschool.a9boarding.model.ImageResponse;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.utils.FileUtils;
import com.minhnpa.coderschool.a9boarding.utils.RetrofitUtils;
import com.minhnpa.coderschool.a9boarding.utils.camera.CameraCaptureCallback;
import com.minhnpa.coderschool.a9boarding.utils.camera.CameraHelper;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryHelper;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryPickCallback;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryPickURICallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public class CreatePostPresenter {

    Activity context;
    public CameraHelper cameraHelper;
    public GalleryHelper galleryHelper;

    ItemImageChoseAdapter adapter;
    List<Object> photos;

    Post newPost = new Post();
    int countPhotos = 0;

    public CreatePostPresenter(Activity context) {
        this.context = context;
        cameraHelper = new CameraHelper();
        galleryHelper = new GalleryHelper();
        photos = new ArrayList<>();
        adapter = new ItemImageChoseAdapter(photos);
        setListener();
    }

    private void setListener() {
        adapter.setListener(new ItemImageChoseAdapter.Listener() {
            @Override
            public void onClickAdd() {
                choosePhoto();
            }

            @Override
            public void onClickViewPhoto(int position) {
                //View Photo
            }
        });
    }


    public ItemImageChoseAdapter getAdapter(){
        return this.adapter;
    }

    public void choosePhoto(){
        final Dialog dia = new Dialog(context);
        LayoutInflater inf = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        View view = inf.inflate(R.layout.dialog_upload_image_chooser, null);
        dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dia.setContentView(view);

        View fromCam = view.findViewById(R.id.dia_take_photo);
        View fromLib = view.findViewById(R.id.dia_library);
        TextView nevBt = ((TextView) view.findViewById(R.id.dia_nev_bt));
        // remove all cap (uppercase) text button
        nevBt.setTransformationMethod(null);

        fromCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
                dia.dismiss();
            }
        });

        fromLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
                dia.dismiss();
            }
        });

        nevBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });

        dia.show();
    }

    public void takePhoto(){
        cameraHelper.takeAPicture(context, new CameraCaptureCallback() {
            @Override
            public void onSuccess(Uri b) {
                photos.add(b);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void pickPhoto(){
        galleryHelper.pickPicture(context, new GalleryPickURICallback() {
            @Override
            public void onSuccess(Uri uri) {
                photos.add(uri);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void uploadPhoto(){
        countPhotos = 0;

        String[] img = {"http://i.imgur.com/dcXe09b.jpg",
                "http://i.imgur.com/YI2uTy4.jpg",
                "http://i.imgur.com/UzYV5xc.jpg",
                "http://i.imgur.com/FFYJlFa.jpg"
        };


        for (int i=0; i<photos.size();i++) {
            newPost.addImages(img[i]);
        }

        ((CreatePostActivity) context).createPost(newPost);

//            File file = new File(String.valueOf(mStoredImageFile));
//            RetrofitUtils.get(context.getResources().getString(R.string.IGMUR_CLIENT_ID))
//                    .create(ImgurApi.class)
//                    .create(FileUtils.partFromFile(file), FileUtils.requestBodyFromFile(file))
//                    .enqueue(new Callback<ImageResponse>() {
//                        @Override
//                        public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
//                            ImageResponse imageResponse = response.body();
//
//                            newPost.addImages(imageResponse.getData().getLink());
//
//                            countPhotos += 1;
//                            if(countPhotos == photos.size()){
//                                ((CreatePostActivity) context).createPost(newPost);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ImageResponse> call, Throwable t) {
////                            Toast.makeText(CreatePostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
    }

    public void createPost(Post post){

    }
}
