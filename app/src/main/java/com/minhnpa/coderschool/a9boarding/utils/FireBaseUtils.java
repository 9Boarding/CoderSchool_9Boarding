package com.minhnpa.coderschool.a9boarding.utils;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by baohq110 on 11/11/2016.
 */

public class FireBaseUtils {

	public static boolean isAuth(){
		FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

		if (firebaseUser == null){
			return  false;
		}
		return true;
	}

	public static void updateUserDisplay(String name, String photoUrl){
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

		UserProfileChangeRequest profileChangeRequest =
				new UserProfileChangeRequest.Builder()
						.setDisplayName(name)
						.setPhotoUri(Uri.parse(photoUrl))
						.build();

		user.updateProfile(profileChangeRequest)
			.addOnCompleteListener(new OnCompleteListener<Void>() {
				@Override
				public void onComplete(@NonNull Task<Void> task) {
					if (task.isSuccessful()){
						Log.d("BaoBao", "okkkkkkkkkkkkkkkkkkkkkkkkkk");
					}
				}
			});
	}
}
