package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.fragment.CreatePostFragment;

/**
 * Created by baohq110 on 05/11/2016.
 */

public class CreatePostActivity extends AppCompatActivity{

	public static Intent newIntent(Context context){
		Intent intent = new Intent(context, CreatePostActivity.class);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);

		if (fragment == null){
			fragment = CreatePostFragment.newInstance();
			fm.beginTransaction()
					.replace(R.id.fragment_container, fragment).commit();
		}

	}
}
