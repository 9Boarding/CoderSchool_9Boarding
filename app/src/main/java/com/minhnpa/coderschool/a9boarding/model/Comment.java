package com.minhnpa.coderschool.a9boarding.model;

/**
 * Created by baohq110 on 08/11/2016.
 */

public class Comment {
	private String comment;
	private User mUser;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return mUser;
	}

	public void setUser(User user) {
		mUser = user;
	}
}
