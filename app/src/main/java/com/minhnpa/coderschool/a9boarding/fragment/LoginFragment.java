package com.minhnpa.coderschool.a9boarding.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.minhnpa.coderschool.a9boarding.R;

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
	@BindView(R.id.tv_sigb_up) TextView tbSignup;

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
	}
}
