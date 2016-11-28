package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.minhnpa.coderschool.a9boarding.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baohq110 on 23/11/2016.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressViewHolder>{
	// This object helps you save/restore the open/close state of each view
	private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
	private List<String> mAddresses;
	private String mAddress;

	public AddressAdapter() {
		mAddresses = new ArrayList<>();
//		mAddresses.add("ly thuong kiet, tp hcm");
//		mAddresses.add("ly thuong kiet, tp hcm");
//		mAddresses.add("ly thuong kiet, tp hcm");
		// uncomment the line below if you want to open only one row at a time
		 viewBinderHelper.setOpenOnlyOne(true);
	}

	public void setAddresses(List<String> addresses){
		mAddresses.clear();
		mAddresses.addAll(addresses);
		notifyDataSetChanged();
	}

	public void addAddress(String address){
		int start = mAddresses.size();
		mAddresses.add(address);
		notifyItemRangeChanged(start, 1);
	}

	public List<String> getAddresses() {
		return mAddresses;
	}

	@Override
	public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_address_activity, parent, false);

		return new AddressViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final AddressViewHolder holder, final int position) {
		viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(position));
		holder.tvAddress.setText(mAddresses.get(position));
//		holder.tvDelete.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mAddresses.remove(position);
//				notifyItemRemoved(position);
//			}
//		});

		holder.swipeRevealLayout.setSwipeListener(new SwipeRevealLayout.SimpleSwipeListener(){
			@Override
			public void onOpened(SwipeRevealLayout view) {
				mAddress = mAddresses.get(position);
				mAddresses.remove(position);
				notifyItemRemoved(position);
				Snackbar.make(view, "Deleted", Snackbar.LENGTH_LONG)
						.setAction("UNDO", new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								mAddresses.add(position, mAddress);
								notifyItemInserted(position);
								holder.swipeRevealLayout.close(true);
							}
						}).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return mAddresses.size();
	}
}
