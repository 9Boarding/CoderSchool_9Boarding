package com.minhnpa.coderschool.a9boarding.utils.gallery;

import android.net.Uri;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public interface GalleryPickURICallback {
    void onSuccess(Uri uri);
    void onFailure(Exception e);
}
