package com.minhnpa.coderschool.a9boarding.db;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.minhnpa.coderschool.a9boarding.model.Bookmark;
import com.minhnpa.coderschool.a9boarding.model.Comment;
import com.minhnpa.coderschool.a9boarding.model.Post;

/**
 * Created by baohq110 on 06/11/2016.
 */

public class FirebaseDbApi {

	public static void newPost(DatabaseReference databaseReference, Post post){
		String postKey = databaseReference.child(DbConstant.CHILD_POST)
				.push().getKey();
		post.setPost_id(postKey);
		databaseReference.child(DbConstant.CHILD_POST)
				.child(postKey)
				.setValue(post);
	}

	public static void saveBookmark(DatabaseReference databaseReference, Post post){
		String firebaseUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

		databaseReference.child(DbConstant.CHILD_BOOKMARK).child(firebaseUserId)
		.setValue(post);
	}

	public static void saveComment(DatabaseReference databaseReference, String postId, Comment comment){
		databaseReference.child(DbConstant.CHILD_COMMENT)
				.child(postId)
				.push()
				.setValue(comment);

	}

}
