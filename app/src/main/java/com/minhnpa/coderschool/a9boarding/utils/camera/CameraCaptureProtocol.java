package com.minhnpa.coderschool.a9boarding.utils.camera;

import android.content.Intent;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public interface CameraCaptureProtocol {
    void onSuccess(Intent data);
    void onFailure(Exception e);
}
