package com.minhnpa.coderschool.a9boarding.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public class WriteURIToCache extends AsyncTask<Uri, Void, File> {
    Context mContext;
    OnWriteURIToCacheListener ls;

    public WriteURIToCache(Context c, OnWriteURIToCacheListener ls) {
        mContext = c;
        this.ls = ls;
    }

    @Override
    protected File doInBackground(Uri... params) {
        Uri uri = params[0];
        Bitmap bitmap = null;
        File cache = null;
        try {
            cache = FileUtils.getCacheFile();
            bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            FileUtils.writeBitmapToFile(bitmap, cache,100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    @Override
    protected void onPostExecute(File bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) ls.onSuccess(bitmap);
        else ls.onFailure(new Exception(new Throwable("Error convert from uri to cache")));
    }
}
