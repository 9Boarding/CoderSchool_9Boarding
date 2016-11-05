package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.minhnpa.coderschool.a9boarding.R;

/**
 * Created by baohq110 on 05/11/2016.
 */

public class TimeLineActivity extends AppCompatActivity {

	public static Intent newIntent(Context context){
		return new Intent(context, TimeLineActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
	}
}
