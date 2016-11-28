package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.minhnpa.coderschool.a9boarding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baohq110 on 23/11/2016.
 */

public class AddressViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.swipe_reveal_layout)
	SwipeRevealLayout swipeRevealLayout;

	@BindView(R.id.tv_delete)
	TextView tvDelete;
	@BindView(R.id.tvAddress)
	TextView tvAddress;

	public AddressViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
