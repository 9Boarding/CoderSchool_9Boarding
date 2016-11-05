package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.fragment.LoginFragment;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {

	/**
	 * Use this method to create the Intent of this activity
	 *
	 * @param context
	 * @return The Intent instance
	 */
	public static Intent newIntent(Context context){
		return new Intent(context, LoginActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		FragmentManager fm = getSupportFragmentManager();

		// Add fragment to activity
		Fragment fragment = fm.findFragmentById(R.id.container);
		if (fragment == null){
			fragment = LoginFragment.newInstance();
			fm.beginTransaction()
					.replace(R.id.container, fragment)
					.commit();
		}
	}
}
