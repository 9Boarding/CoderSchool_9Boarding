package com.minhnpa.coderschool.a9boarding.activity;

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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.db.DbConstant;
import com.minhnpa.coderschool.a9boarding.dialog.GenderDialogFragment;
import com.minhnpa.coderschool.a9boarding.model.User;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by baohq110 on 18/11/2016.
 */

public class ViewAndEditProfile extends AppCompatActivity implements GenderDialogFragment.GenderDialogResultBack{
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.collapsing_tool_bar)
	CollapsingToolbarLayout collapsingToolbar;
	@BindView(R.id.tv_gender)
	TextView tvGender;
	@BindView(R.id.tv_birth_date)
	TextView tvBirthDate;
	@BindView(R.id.tv_phone)
	TextView tvPhone;
	@BindView(R.id.tv_address1)
	TextView tvAddress1;

	private DatabaseReference mDatabaseReference;
	private User mUser;
	private String mUserID;

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
		tvAddress1.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_chevron_right,0);

		setOnClick();
		setupDatabase();
		setupUI();
	}

	@Override
	public void onBackPressed() {
//		updateProfile();
		super.onBackPressed();
	}

	@Override
	public void onGenderData(String gender) {
		tvGender.setText(gender);
		tvGender.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
	}

	/**
	 * Setup onClick function for all widgets
	 */
	private void setOnClick() {
		// for TextView Gender
		tvGender.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showGenderDialog();
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
		}
	}

	/**
	 * Read the data and update if any changed
	 */
	private void updateProfile() {
		String gender = tvGender.getText().toString();

		Log.d("BaoBao", mUserID);

		if (mUser == null){ // User is not existed
			mUser = new User();
			mUser.setGender(gender);
			mUser.getUserInformation().addEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());

		}

		mDatabaseReference.child(DbConstant.CHILD_USER)
				.child(mUserID)
				.setValue(mUser);

	}

	/**
	 * Show up the gender dialog
	 */
	private void showGenderDialog(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		GenderDialogFragment  fragment = GenderDialogFragment.newInstance();

		fragment.show(fragmentManager, "gender");
	}

	private void setText(TextView view, String text){
		if (!((text == null) || text.isEmpty())){
			view.setText(text);
			view.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
		}
	}
}

