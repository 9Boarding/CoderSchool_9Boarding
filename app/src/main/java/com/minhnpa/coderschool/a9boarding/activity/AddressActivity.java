package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.*;
import com.minhnpa.coderschool.a9boarding.adapter.AddressAdapter;
import com.minhnpa.coderschool.a9boarding.db.DbConstant;
import com.minhnpa.coderschool.a9boarding.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 22/11/2016.
 */

public class AddressActivity extends AppCompatActivity{
	private static final String EXTRA_USER = "extra_user";
	@BindView(R.id.ll_add_address)
	LinearLayout llAddAddress;
	@BindView(R.id.tv_add)
	TextView tvAdd;
	@BindView(R.id.edtAddress)
	EditText etAddress;

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.rv_list_address)
	RecyclerView rvListAddress;

	private AddressAdapter mAddressAdapter;
	private User mUser;
	private String mUserId;
	private DatabaseReference mDatabaseReference;
	private boolean isAdd = false;

	public static Intent newIntent(Context context, User user){
		Intent intent = new Intent(context, AddressActivity.class);

		intent.putExtra(EXTRA_USER, user);
		return intent;
	}
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);

		ButterKnife.bind(this);

		mUser = (User) getIntent().getSerializableExtra(EXTRA_USER);

		setSupportActionBar(toolbar);

		mAddressAdapter = new AddressAdapter();
		mAddressAdapter.setAddresses(mUser.getUserInformation().getAddresses());
		rvListAddress.setLayoutManager(new LinearLayoutManager(this));
		rvListAddress.setAdapter(mAddressAdapter);

		tvAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String address = etAddress.getText().toString().trim();
				if (address.isEmpty()){
					Toast.makeText(AddressActivity.this,
							"Address should not empty",
							Toast.LENGTH_SHORT).show();
				}else{
					isAdd = true;
					mAddressAdapter.addAddress(etAddress.getText().toString().trim());
					// clear it
					etAddress.setText("");
					// disappear it
					llAddAddress.setVisibility(View.GONE);
				}
			}
		});

		isAdd = false;
		// for Database
		mDatabaseReference = FirebaseDatabase.getInstance().getReference();
		mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_activity_address, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){
			case R.id.action_add:
				llAddAddress.setVisibility(View.VISIBLE);
				break;
			case R.id.action_done:
				onBackPressed();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		if(isAdd){
			mUser.getUserInformation().setAddresses(mAddressAdapter.getAddresses());
			mDatabaseReference.child(DbConstant.CHILD_USER)
					.child(mUserId)
					.setValue(mUser);
		}
		super.onBackPressed();
	}
}
