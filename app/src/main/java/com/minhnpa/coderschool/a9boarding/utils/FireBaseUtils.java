package com.minhnpa.coderschool.a9boarding.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by baohq110 on 11/11/2016.
 */

public class FireBaseUtils {

    public static boolean isAuth() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {
            return false;
        }
        return true;
    }
}
