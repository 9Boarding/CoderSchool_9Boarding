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

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.adapter.ItemImageChoseAdapter;
import com.minhnpa.coderschool.a9boarding.utils.camera.CameraCaptureCallback;
import com.minhnpa.coderschool.a9boarding.utils.camera.CameraHelper;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryHelper;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryPickCallback;
import com.minhnpa.coderschool.a9boarding.utils.gallery.GalleryPickURICallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public class CreatePostPresenter {

    FragmentActivity context;
    public CameraHelper cameraHelper;
    public GalleryHelper galleryHelper;

    ItemImageChoseAdapter adapter;
    List<Object> photos;

    public CreatePostPresenter(FragmentActivity context) {
        this.context = context;
        cameraHelper = new CameraHelper();
        galleryHelper = new GalleryHelper();
        photos = new ArrayList<>();
        adapter = new ItemImageChoseAdapter(photos);
    }

    public ItemImageChoseAdapter getAdapter(){
        return this.adapter;
    }

    public void choosePhoto(){
        final Dialog dia = new Dialog(context.getApplicationContext());
        LayoutInflater inf = ((LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
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
}
