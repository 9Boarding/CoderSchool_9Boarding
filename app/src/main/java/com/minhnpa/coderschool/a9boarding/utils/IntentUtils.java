package com.minhnpa.coderschool.a9boarding.utils;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.minhnpa.coderschool.a9boarding.activity.CreatePostActivity;
import com.minhnpa.coderschool.a9boarding.activity.LoginActivity;
import com.minhnpa.coderschool.a9boarding.activity.ProfileActivity;
import com.minhnpa.coderschool.a9boarding.activity.ViewAndEditProfile;

/**
 * Created by baohq110 on 11/11/2016.
 */

public class IntentUtils {
    public static void signin(Context context) {
        Intent intent;
        intent = LoginActivity.newIntent(context);
        context.startActivity(intent);
    }

    public static void signout() {
        FirebaseAuth.getInstance().signOut();
    }

    public static void startProfileActivity(Context context) {
        context.startActivity(ProfileActivity.newIntent(context));
    }

    public static void startCreatePostActivity(Context context) {
        if (FireBaseUtils.isAuth()) {
            context.startActivity(CreatePostActivity.newIntent(context));
            return;
        }
        signin(context);
    }

    public static void startViewAndEditActivity(Context context) {
        context.startActivity(ViewAndEditProfile.newIntent(context));
    }


}