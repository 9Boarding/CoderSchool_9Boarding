package com.minhnpa.coderschool.a9boarding.utils.camera;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Config;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public class CameraHelper implements CameraCaptureProtocol {
    public static final String TAG = "CameraHelper";
    public static final int REQUEST_IMAGE_CAPTURE = 1;

    private CameraCaptureCallback cb;
    public Fragment frg;
    public Activity at;

    public void takeAPicture(Fragment frg, CameraCaptureCallback cb) {
        this.frg = frg;
        this.cb = cb;
        startCamera();
    }

    public void takeAPicture(Activity at, CameraCaptureCallback cb) {
        this.at = at;
        this.cb = cb;
        startCamera();
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

    private void startCamera() {
        Intent startCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (startCam.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (Exception e) {
                cb.onFailure(e);
            }
            if (photoFile != null) {
                currentPhotoFile = photoFile;
                startCam.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(startCam, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            Throwable t = new OperationApplicationException("Can't start camera, check CameraHelper class please!");
            cb.onFailure(new Exception(t));
        }
    }
    @Override
    public void onSuccess(Intent data) {
        if (currentPhotoFile != null) {

//            if(caculateImageSize(currentPhotoFile)>4096) {
//                new ResizeImageToSize(4096, new OnResizeImageListener() {
//                    @Override
//                    public void onSuccess(File f) {
//                        cb.onSuccess(Uri.fromFile(f));
//                        Log.d(TAG, "onSuccess: "+f.getAbsolutePath());
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//                        cb.onFailure(e);
//                    }
//                },100).execute(currentPhotoFile.getAbsoluteFile());
//            } else {
//                cb.onSuccess(Uri.fromFile(currentPhotoFile));
//            }
            cb.onSuccess(Uri.fromFile(currentPhotoFile));

            saveCurrentPicToGallery(getActivity());
        } else {
            Throwable t = new OperationApplicationException("Can't start camera, check CameraHelper class please!");
            cb.onFailure(new Exception(t));
        }
    }

    @Override
    public void onFailure(Exception e) {
        cb.onFailure(e);
    }

    String currentPhotoPath;
    File currentPhotoFile;

    private void saveCurrentPicToGallery(Activity at) {
        if (currentPhotoPath == null) return;
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        at.sendBroadcast(mediaScanIntent);
    }

    /**
     * check more information in https://developer.android.com/training/camera/photobasics.html#TaskPath
     *
     * @return Image file where camera can save their photo in
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File storageDirWithAppName = new File(storageDir,"9House");
        if (storageDirWithAppName.exists() == false) {
            storageDirWithAppName.mkdir();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDirWithAppName      /* directory */
        );
        currentPhotoPath ="file:"+image.getAbsolutePath();
        // Save a file: path for use with ACTION_VIEW intents
//        Log.d(TAG, "createImageFile: " + "file:" + image.getAbsolutePath());
        return image;
    }
    private int caculateImageSize(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);
        if (bitmap != null)
            return bitmap.getHeight()<bitmap.getWidth() ? bitmap.getWidth():bitmap.getHeight();

        return -1;
    }
}
