package com.minhnpa.coderschool.a9boarding.utils.camera;

import android.net.Uri;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public interface CameraCaptureCallback {
    void onSuccess(Uri b);
    void onFailure(Exception e);
}
