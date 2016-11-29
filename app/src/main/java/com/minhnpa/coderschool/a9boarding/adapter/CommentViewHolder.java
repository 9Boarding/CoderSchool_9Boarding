package com.minhnpa.coderschool.a9boarding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.model.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.civProfile)
    CircleImageView civProfile;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvComment)
    TextView tvComment;

    public View itemView;

    public CommentViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(itemView);
    }

    public void bindComment(Comment comment) {
        if (null != comment.getUser().getProfilePicUrl()) {
            Glide.with(itemView.getContext())
                    .load(comment.getUser().getProfilePicUrl())
                    .into(civProfile);
        }
        tvUserName.setText(comment.getUser().getName());
        tvComment.setText(comment.getContent());
    }
}
