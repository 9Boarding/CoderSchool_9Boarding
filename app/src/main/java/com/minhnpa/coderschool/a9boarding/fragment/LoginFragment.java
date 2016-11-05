package com.minhnpa.coderschool.a9boarding.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.activity.SignUpActivity;
import com.minhnpa.coderschool.a9boarding.activity.TimeLineActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 03/11/2016.
 */

public class LoginFragment extends Fragment {
	@BindView(R.id.til_email) TextInputLayout tilEmail;
	@BindView(R.id.et_email) EditText etEmail;
	@BindView(R.id.til_password) TextInputLayout tilPassword;
	@BindView(R.id.et_password) EditText etPassword;
	@BindView(R.id.btn_login) AppCompatButton btnLogin;
	@BindView(R.id.btn_login_with_google) AppCompatButton btnLoginWithGoogle;
	@BindView(R.id.tv_sign_up) TextView tvSignUp;
	@BindView(R.id.pbLoading) ProgressBar pbLoading;

	private FirebaseAuth mFirebaseAuth;

	/**
	 * Use this method to create the instance of this LoginFragment
	 * @return
	 */
	public static LoginFragment newInstance() {

		Bundle args = new Bundle();

		LoginFragment fragment = new LoginFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		setupListener();

		mFirebaseAuth = FirebaseAuth.getInstance();
	}

	private void setupListener() {
		// TextView signnup
		tvSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(SignUpActivity.newIntent(getActivity()));
			}
		});

		// Button Loggin
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pbLoading.setVisibility(View.VISIBLE);
				if(validateInput()){
					mFirebaseAuth.signInWithEmailAndPassword(etEmail.getText().toString().trim(),
							etPassword.getText().toString())
					.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
						@Override
						public void onComplete(@NonNull Task<AuthResult> task) {
							pbLoading.setVisibility(View.GONE);
							if (!task.isSuccessful()){
								showText("Email or Password incorrect");
								return;
							}
							startActivity(TimeLineActivity.newIntent(getActivity()));

						}
					});
				}
			}
		});

	}

	private boolean validateInput() {
		if (!validateEmail()) { return false;}

		return true;
	}


	private static boolean isValidEmail(String email) {
		return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}

	private boolean validateEmail(){
		if (!isValidEmail(etEmail.getText().toString().trim())){
			tilEmail.setError(getString(R.string.err_email_invalid));
			requestFocus(etEmail);
			return false;
		}
		tilEmail.setErrorEnabled(false);
		return true;
	}

	private void requestFocus(View view){
		if (view.requestFocus()){
			getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		}
	}

	private void showText(String text){
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
}
