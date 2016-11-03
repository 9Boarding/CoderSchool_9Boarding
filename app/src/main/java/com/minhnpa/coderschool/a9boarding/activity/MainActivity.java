package com.minhnpa.coderschool.a9boarding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minhnpa.coderschool.a9boarding.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(LoginActivity.newIntent(this));

    }
}
