package com.minhnpa.coderschool.a9boarding.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.minhnpa.coderschool.a9boarding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by baohq110 on 19/11/2016.
 */

public class GenderDialogFragment extends DialogFragment{
	@BindView(R.id.rb_male)
	RadioButton rbMale;

	@BindView(R.id.rb_female)
	RadioButton rbFemale;

	@BindView(R.id.rb_others)
	RadioButton rbOthers;

	public interface GenderDialogResultBack{
		public void onGenderData(String gender);
	}
	public static GenderDialogFragment newInstance() {

		Bundle args = new Bundle();

		GenderDialogFragment fragment = new GenderDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_fragment_gender, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getDialog().setTitle("Gender");
		ButterKnife.bind(this, view);
	}

	@OnClick(R.id.rb_male)
	public void onMaleClick(){
		dataBack(getString(R.string.male));
		dismiss();
	}

	@OnClick(R.id.rb_female)
	public void onFemaleClick(){
		dataBack(getString(R.string.female));
		dismiss();
	}

	@OnClick(R.id.rb_others)
	public void onOthersClick(){
		dataBack(getString(R.string.others));
		dismiss();
	}

	private void dataBack(String gender){
		GenderDialogResultBack listener = (GenderDialogResultBack) getActivity();
		listener.onGenderData(gender);
	}

}
