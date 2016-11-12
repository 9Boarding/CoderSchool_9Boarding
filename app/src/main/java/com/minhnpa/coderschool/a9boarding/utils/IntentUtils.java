package com.minhnpa.coderschool.a9boarding.utils;

import android.content.Context;
import android.content.Intent;

import com.minhnpa.coderschool.a9boarding.activity.CreatePostActivity;
import com.minhnpa.coderschool.a9boarding.activity.LoginActivity;

/**
 * Created by baohq110 on 11/11/2016.
 */

public class IntentUtils {

	public static void askLogin(Context context){
		Intent intent;
			intent = LoginActivity.newIntent(context);
			context.startActivity(intent);


	}

	public static void startCreatePostActivity(Context context){
		if (FireBaseUtils.isAuth()){
			context.startActivity(new CreatePostActivity().newIntent(context));
			return;
		}
		askLogin(context);
	}
}
