package com.minhnpa.coderschool.a9boarding.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.db.DbConstant;
import com.minhnpa.coderschool.a9boarding.model.User;
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
    @BindView(R.id.civProfile)
    CircleImageView civProfile;
    @BindView(R.id.tvProfileName)
    TextView tvProfileName;
    @BindView(R.id.tvEditProfile)
    TextView tvEditProfile;
    @BindView(R.id.tvSettings)
    TextView tvSettings;
    @BindView(R.id.tvFeedback)
    TextView tvFeedback;
    @BindView(R.id.tvSignInSignOut)
    TextView tvSignInSignOut;

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser mFirebaseUser;
    private String mUserID;
    private User mUser;

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

//		setupDatabase();
        setupFirebase();
//		setupUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    @OnClick(R.id.tvProfileName)
    public void viewAndEditProfileOnClick() {
        IntentUtils.startViewAndEditActivity(getContext());
    }

    @OnClick(R.id.tvSignInSignOut)
    public void signInOutOnClick() {
        if (FireBaseUtils.isAuth()) {
            showSignoutDialog();
        } else {
            IntentUtils.signin(getContext());
        }
        setupUI();
    }

    /**
     * Show an alert dialog to ask user if they want to signout
     */
    private void showSignoutDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.sign_out_question)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        IntentUtils.signout();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     *
     */
    private void setupDatabase() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseReference.child(DbConstant.CHILD_USER)
                .child(mUserID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mUser = dataSnapshot.getValue(User.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    /**
     *
     */
    private void setupFirebase() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                setupUI();
            }
        };
    }

    /**
     *
     */
    private void setupUI() {
        if (FireBaseUtils.isAuth()) {
            // User is already login
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            tvSignInSignOut.setText(R.string.signout);
            tvProfileName.setText(user.getDisplayName());
            tvEditProfile.setText(R.string.view_and_edit_profile);

            if (user.getPhotoUrl() != null) {
                Glide.with(getContext())
                        .load(user.getPhotoUrl())
                        .into(civProfile);
            } else {
                civProfile.setImageDrawable(
                        ActivityCompat.getDrawable(getContext(), R.drawable.no_photo));
            }
        } else {
            // User is not login
            tvSignInSignOut.setText(R.string.signin);
            civProfile.setImageDrawable(
                    ActivityCompat.getDrawable(getContext(), R.drawable.no_photo));
            tvProfileName.setText("");
            tvEditProfile.setText("");
        }
    }
}
