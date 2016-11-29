package com.minhnpa.coderschool.a9boarding.utils.gallery;

import java.io.File;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public interface GalleryPickCallback {
    void onSuccess(File b);
    void onFailure(Exception e);
}
