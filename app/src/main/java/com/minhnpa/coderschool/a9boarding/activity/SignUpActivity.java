package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.minhnpa.coderschool.a9boarding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 04/11/2016.
 */

public class SignUpActivity extends AppCompatActivity {
	@BindView(R.id.btn_signup) Button btnSignUp;
	
	public static Intent newIntent(Context context){

		return new Intent(context, SignUpActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		ButterKnife.bind(this);

		setupListener();
	}

	private void setupListener() {

		btnSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
}
