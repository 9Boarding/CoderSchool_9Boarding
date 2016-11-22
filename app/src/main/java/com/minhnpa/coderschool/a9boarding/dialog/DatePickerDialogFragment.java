package com.minhnpa.coderschool.a9boarding.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.minhnpa.coderschool.a9boarding.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 20/11/2016.
 */

public class DatePickerDialogFragment extends DialogFragment{
	@BindView(R.id.tv_ok)
	TextView tvOk;
	@BindView(R.id.tv_cancel)
	TextView tvCancel;
	@BindView(R.id.dp_birth_date)
	DatePicker dpBirthDate;

	public static DatePickerDialogFragment newInstance() {

		Bundle args = new Bundle();

		DatePickerDialogFragment fragment = new DatePickerDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_date_picker, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);

		// set listener
		tvOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				OnDatePickerListener listener = (OnDatePickerListener) getActivity();
				listener.onDateSet(dpBirthDate.getYear(), dpBirthDate.getMonth() + 1, dpBirthDate.getDayOfMonth());
				dismiss();
			}
		});

		tvCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR) - 20;

		// set data
		dpBirthDate.init(year, 1, 1, null);
	}

	public interface OnDatePickerListener{
		public void onDateSet(int year, int month, int dayOfMonth);
	}
}
