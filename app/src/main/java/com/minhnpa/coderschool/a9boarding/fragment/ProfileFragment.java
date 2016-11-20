package com.minhnpa.coderschool.a9boarding.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.utils.FireBaseUtils;
import com.minhnpa.coderschool.a9boarding.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by baohq110 on 17/11/2016.
 */

public class ProfileFragment extends Fragment {
	@BindView(R.id.civ_profile_pic) CircleImageView civProfilePic;
	@BindView(R.id.profile_name) LinearLayout profileName;
	@BindView(R.id.tv_profile_name) TextView tvProfileName;
	@BindView(R.id.tv_edit_profile) TextView tvEditProfile;
	@BindView(R.id.tv_settings) TextView tvSettings;
	@BindView(R.id.tv_feedback_us) TextView tvFeedbackus;
	@BindView(R.id.tv_signin_or_signout) TextView tvSigninOut;


	public static ProfileFragment newInstance() {

		Bundle args = new Bundle();

		ProfileFragment fragment = new ProfileFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_profile, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		setupUI();
	}

	@Override
	public void onResume() {
		super.onResume();
		setupUI();
	}

	@OnClick(R.id.profile_name)
	public void viewAndEditProfileOnClick(){
		IntentUtils.startViewAndEditActivity(getContext());
	}

	@OnClick(R.id.tv_signin_or_signout)
	public void signInOutOnClick(){
		if (FireBaseUtils.isAuth()){
			IntentUtils.signout();
		} else {
			IntentUtils.signin(getContext());
		}
		setupUI();
	}
	private void setupUI(){
		if(FireBaseUtils.isAuth()){
			// User is already login
			FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
			tvSigninOut.setText(R.string.signout);
			tvProfileName.setText(user.getDisplayName());
			tvEditProfile.setText(R.string.view_and_edit_profile);
			if (user.getPhotoUrl() != null){
				Glide.with(getContext())
						.load(user.getPhotoUrl())
						.into(civProfilePic);
			}else {
				civProfilePic.setImageDrawable(
						ActivityCompat.getDrawable(getContext(), R.drawable.no_photo));
			}
		}else {
			// User is not login
			tvSigninOut.setText(R.string.signin);
			civProfilePic.setImageDrawable(
					ActivityCompat.getDrawable(getContext(), R.drawable.no_photo));
			tvProfileName.setText("");
			tvEditProfile.setText("");

		}

	}
}
