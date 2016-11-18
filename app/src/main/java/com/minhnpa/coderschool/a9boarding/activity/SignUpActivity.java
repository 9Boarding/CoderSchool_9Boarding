package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.minhnpa.coderschool.a9boarding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 04/11/2016.
 */

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.til_re_password)
    TextInputLayout tilReTypePassword;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_re_password)
    EditText etReTypePassword;
    @BindView(R.id.btn_signup)
    Button btnSignUp;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    private FirebaseAuth mFirebaseAuth;

    public static Intent newIntent(Context context) {

        return new Intent(context, SignUpActivity.class);
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        setupListener();
        setupVariable();
    }

    private void setupVariable() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     * This method to setup the listener for view and widget
     */
    private void setupListener() {

        // Listener for button signup
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    pbLoading.setVisibility(View.VISIBLE);
                    mFirebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString(),
                            etPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pbLoading.setVisibility(View.GONE);
                                    // In failed case
                                    if (!task.isSuccessful()) {
                                        showText("Unsuccessful! Email may already sign up");
                                        return;
                                    }
                                    onBackPressed();
                                }
                            });


                }
            }
        });

        // For each EditText
        etEmail.addTextChangedListener(new MyTextWatcher(etEmail));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));
        etReTypePassword.addTextChangedListener(new MyTextWatcher(etReTypePassword));
    }

    /**
     * This method to validate the input
     */
    private boolean validateInput() {
        if (!validateEmail()) return false;
        if (!validatePassword()) return false;
        if (!validateRePassword()) {
            return false;
        }

        return true;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private boolean validateEmail() {
        if (!isValidEmail(etEmail.getText().toString().trim())) {
            tilEmail.setError(getString(R.string.err_email_invalid));
            requestFocus(etEmail);
            return false;
        }
        tilEmail.setErrorEnabled(false);
        return true;
    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().length() < 6) {
            tilPassword.setError(getString(R.string.err_pass_leght));
            requestFocus(etPassword);
            return false;
        }
        tilPassword.setErrorEnabled(false);
        return true;
    }

    private boolean validateRePassword() {
        if (!etPassword.getText().toString().equals(etReTypePassword.getText().toString())) {

            tilReTypePassword.setError(getString(R.string.err_pass_not_match));
            requestFocus(etReTypePassword);
            return false;
        }
        tilReTypePassword.setErrorEnabled(false);
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private void showText(String text) {
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     */
    private class MyTextWatcher implements TextWatcher {
        private View mView;

        public MyTextWatcher(View view) {
            mView = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            switch (mView.getId()) {
                case R.id.et_email:
                    tilEmail.setErrorEnabled(false);
                    break;
                case R.id.et_password:
                    tilPassword.setErrorEnabled(false);
                    break;
                case R.id.et_re_password:
                    tilReTypePassword.setErrorEnabled(false);
                    break;
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}