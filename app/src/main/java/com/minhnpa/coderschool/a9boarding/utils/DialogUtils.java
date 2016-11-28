package com.minhnpa.coderschool.a9boarding.utils;

import android.support.v4.app.FragmentManager;

import com.minhnpa.coderschool.a9boarding.dialog.DatePickerDialogFragment;
import com.minhnpa.coderschool.a9boarding.dialog.GenderDialogFragment;
import com.minhnpa.coderschool.a9boarding.dialog.PhoneDialogFragment;

/**
 * Created by baohq110 on 22/11/2016.
 */

public class DialogUtils {

	/**
	 * Show up the gender dialog
	 */
	public static void showGenderDialog(FragmentManager fragmentManager){
		GenderDialogFragment fragment = GenderDialogFragment.newInstance();
		fragment.show(fragmentManager, "gender");
	}

	public static void showDatePickerDialog(FragmentManager fragmentManager){
		DatePickerDialogFragment dialog = DatePickerDialogFragment.newInstance();
		dialog.show(fragmentManager, "datePicker");
	}

	public static void showPhoneDialog(FragmentManager fragmentManager){
		PhoneDialogFragment.newInstance().show(fragmentManager, "Phone picker");
	}

}
