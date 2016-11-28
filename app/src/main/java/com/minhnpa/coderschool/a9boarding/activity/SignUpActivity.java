package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;
    @BindView(R.id.btnSignUp)
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
                    mFirebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),
                            edtPassword.getText().toString())
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
        if (!isValidEmail(edtEmail.getText().toString().trim())) {
            edtEmail.setError(getString(R.string.err_email_invalid));
            requestFocus(edtEmail);
            return false;
        }

        return true;
    }

    private boolean validatePassword() {
        if (edtPassword.getText().toString().length() < 6) {
            edtPassword.setError(getString(R.string.err_pass_leght));
            requestFocus(edtPassword);
            return false;
        }

        return true;
    }

    private boolean validateRePassword() {
        if (!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
            edtPassword.setError(getString(R.string.err_pass_not_match));
            requestFocus(edtConfirmPassword);
            return false;
        }

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
}