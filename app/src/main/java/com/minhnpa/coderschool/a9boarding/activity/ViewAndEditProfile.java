package com.minhnpa.coderschool.a9boarding.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.db.DbConstant;
import com.minhnpa.coderschool.a9boarding.dialog.DatePickerDialogFragment;
import com.minhnpa.coderschool.a9boarding.dialog.GenderDialogFragment;
import com.minhnpa.coderschool.a9boarding.dialog.PhoneDialogFragment;
import com.minhnpa.coderschool.a9boarding.model.User;
import com.minhnpa.coderschool.a9boarding.model.UserInformation;
import com.minhnpa.coderschool.a9boarding.utils.DialogUtils;
import com.minhnpa.coderschool.a9boarding.utils.FireBaseUtils;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by baohq110 on 18/11/2016.
 */

public class ViewAndEditProfile extends AppCompatActivity implements GenderDialogFragment.GenderDialogResultBack,
		DatePickerDialogFragment.OnDatePickerListener,
		PhoneDialogFragment.OnPhoneSet{
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.collapsing_tool_bar)
	CollapsingToolbarLayout collapsingToolbar;
	@BindView(R.id.tv_user_name)
	TextView tvUserName;
	@BindView(R.id.tv_gender)
	TextView tvGender;
	@BindView(R.id.tv_birth_date)
	TextView tvBirthDate;
	@BindView(R.id.tv_phone)
	TextView tvPhone;
	@BindView(R.id.tv_address)
	TextView tvAddress;
	@BindView(R.id.iv_cover)
	ImageView ivCover;

	private DatabaseReference mDatabaseReference;
	private User mUser;
	private String mUserID;
	private boolean isChanged = false;

	public static Intent newIntent(Context context){
		Intent intent = new Intent(context, ViewAndEditProfile.class);
		return intent;
	}
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_and_edit_profile);
		ButterKnife.bind(this);

//		setSupportActionBar(toolbar);
		collapsingToolbar.setTitle("Profile");

		tvGender.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_chevron_right,0);
		tvBirthDate.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_chevron_right,0);
		tvPhone.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_chevron_right,0);
		tvAddress.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_chevron_right,0);

		isChanged = false;
		setOnClick();
		setupDatabase();
		setupUI();
	}

	@Override
	public void onBackPressed() {
		if (isChanged){
			updateProfile();
		}
		super.onBackPressed();
	}

	@Override
	public void onGenderData(String gender) {
		tvGender.setText(gender);
		tvGender.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
		isChanged = true;
	}

	/**
	 * Setup onClick function for all widgets
	 */
	private void setOnClick() {
		// for TextView Gender
		tvGender.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogUtils.showGenderDialog(getSupportFragmentManager());
			}
		});

		// For textView birth date
		tvBirthDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogUtils.showDatePickerDialog(getSupportFragmentManager());
			}
		});

		// For textView phone
		tvPhone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogUtils.showPhoneDialog(getSupportFragmentManager());
			}
		});

		// For textview address
		tvAddress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(AddressActivity.newIntent(ViewAndEditProfile.this, mUser));
			}
		});
	}

	private void setupDatabase(){
		mDatabaseReference = FirebaseDatabase.getInstance().getReference();
		mUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
		mDatabaseReference.child(DbConstant.CHILD_USER)
				.child(mUserID)
				.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				mUser = dataSnapshot.getValue(User.class);
				setupUI();
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}

	private void setupUI(){
		if (mUser != null){
			setText(tvGender, mUser.getGender());
			setText(tvBirthDate, mUser.getUserInformation().getBirthDate());
			setText(tvPhone, mUser.getUserInformation().getPhone().get(0));
			Glide.with(this)
					.load(mUser.getProfilePicUrl())
					.into(ivCover);

			if (!mUser.getUserInformation().getAddresses().isEmpty()){
				setText(tvAddress, mUser.getUserInformation().getAddresses().get(0));
			}else {
				setText(tvAddress, "");
			}
		}
	}

	/**
	 * Read the data and update if any changed
	 */
	private void updateProfile() {
		String gender = tvGender.getText().toString();
		String birthdate = tvBirthDate.getText().toString();
		String phone = tvPhone.getText().toString();
		String address = tvAddress.getText().toString();
		String name = tvUserName.getText().toString();

		String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

		UserInformation userInformation = new UserInformation();
		userInformation.addEmail(email);
		userInformation.addPhone(phone);
		userInformation.addAddress(address);
		userInformation.setName(name);
		userInformation.setBirthDate(birthdate);

		if (mUser == null){ // User is not existed
			mUser = new User();
			mUser.setUserId(mUserID);
		}

		mUser.setGender(gender);
		mUser.setUserInformation(userInformation);

		mDatabaseReference.child(DbConstant.CHILD_USER)
				.child(mUserID)
				.setValue(mUser);
		FireBaseUtils.updateUserDisplay(name, mUser.getProfilePicUrl());

	}


	private void setText(TextView view, String text){
		if (!((text == null) || text.isEmpty())){
			view.setText(text);
			view.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
		}
	}

	@Override
	public void onDateSet(int year, int month, int dayOfMonth) {
		String date = Integer.toString(year) + "/" + Integer.toString(month) + "/" +
				      Integer.toString(dayOfMonth);
		tvBirthDate.setText(date);
		isChanged = true;
	}

	@Override
	public void onPhoneResult(String phone) {
		tvPhone.setText(phone);
		isChanged = true;
	}
}

