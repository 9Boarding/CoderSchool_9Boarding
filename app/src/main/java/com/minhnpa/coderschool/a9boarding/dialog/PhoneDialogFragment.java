package com.minhnpa.coderschool.a9boarding.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.minhnpa.coderschool.a9boarding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by baohq110 on 22/11/2016.
 */

public class PhoneDialogFragment extends DialogFragment {
    @BindView(R.id.edtPhone)
    EditText etPhone;
    @BindView(R.id.tv_ok)
    TextView tvOK;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    public static PhoneDialogFragment newInstance() {

        Bundle args = new Bundle();

        PhoneDialogFragment fragment = new PhoneDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_phone_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.tv_ok)
    public void onTvOkClick() {
        String phone = etPhone.getText().toString().trim();
        if (phone.isEmpty()) {
            Toast.makeText(getContext(), "Phone count not empty", Toast.LENGTH_SHORT).show();
        } else {
            OnPhoneSet listener = (OnPhoneSet) getActivity();
            listener.onPhoneResult(phone);
            dismiss();
        }
    }

    @OnClick(R.id.tv_cancel)
    public void onTvCancelClick() {
        dismiss();
    }

    public interface OnPhoneSet {
        public void onPhoneResult(String phone);
    }
}
