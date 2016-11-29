package com.minhnpa.coderschool.a9boarding.utils.gallery;

import android.content.Intent;

/**
 * Created by dangfiztssi on 29/11/2016.
 */

public interface GalleryPickProtocol {
    void onSuccess(Intent data);
    void onFailure(Exception e);
}
