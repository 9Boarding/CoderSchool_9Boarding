package com.minhnpa.coderschool.a9boarding.utils.gallery;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;

import com.minhnpa.coderschool.a9boarding.utils.OnWriteURIToCacheListener;
import com.minhnpa.coderschool.a9boarding.utils.WriteURIToCache;

import java.io.File;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public class GalleryHelper implements GalleryPickProtocol {

    public static final int PICK_IMAGE_REQUEST = 989;

    public GalleryPickCallback cb;
    public GalleryPickURICallback cbu;
    public Fragment frg;
    public Activity at;

    public void pickPicture(Fragment frg, GalleryPickCallback cb) {
        this.cb = cb;
        this.cbu = null;
        this.frg = frg;
        startGallery();
    }

    public void pickPicture(Activity at, GalleryPickCallback cb) {
        this.cb = cb;
        this.cbu = null;
        this.at = at;
        startGallery();
    }

    public void pickPicture(Fragment at, GalleryPickURICallback cb) {
        this.cbu = cb;
        this.cb = null;
        this.frg = at;
        startGallery();
    }

    public void pickPicture(Activity at, GalleryPickURICallback cb) {
        this.cb = null;
        this.cbu = cb;
        this.at = at;
        startGallery();
    }

    public Activity getActivity() {
        return frg == null ? at : frg.getActivity();
    }

    public void startActivityForResult(Intent intent, int code) {
        if (frg == null) {
            at.startActivityForResult(intent, code);
        } else {
            frg.startActivityForResult(intent, code);
        }
    }

    private void startGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onSuccess(Intent data) {
        Uri uri = data.getData();
        if (cb != null) {
            new WriteURIToCache(getActivity(), new OnWriteURIToCacheListener() {
                @Override
                public void onSuccess(File b) {
                    cb.onSuccess(b);
                }

                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                }
            }).execute(uri);
        } else {
            cbu.onSuccess(uri);
        }
    }

    @Override
    public void onFailure(Exception e) {
        if (cb != null) {
            cb.onFailure(e);
        } else {
            cbu.onFailure(e);
        }
    }
}
