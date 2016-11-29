package com.minhnpa.coderschool.a9boarding.utils;

import java.io.File;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public interface OnWriteURIToCacheListener {
    void onSuccess(File b);
    void onFailure(Exception e);
}
