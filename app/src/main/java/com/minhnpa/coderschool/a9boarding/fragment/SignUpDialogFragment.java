package com.minhnpa.coderschool.a9boarding.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minhnpa.coderschool.a9boarding.R;

/**
 * Created by baohq110 on 05/11/2016.
 */

public class SignUpDialogFragment extends DialogFragment {

    public SignUpDialogFragment() {

    }

    public static SignUpDialogFragment newInstance() {
        Bundle args = new Bundle();

        SignUpDialogFragment fragment = new SignUpDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_signup, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
