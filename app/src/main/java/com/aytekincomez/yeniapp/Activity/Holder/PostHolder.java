package com.aytekincomez.yeniapp.Activity.Holder;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.aytekincomez.yeniapp.Activity.Adapter.PostAdapter;
import com.aytekincomez.yeniapp.R;

import java.lang.ref.WeakReference;

public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView ivProfileImage;
    public TextView tvUserName, postText,postTarih, likeCount, commentCount;
    public Button btnComment;
    CardView cardView;
    public ToggleButton btnLike;
    SharedPreferences sharedPreferences;
    PostAdapter.ItemClickListener itemClickListener;

    public PostHolder(@NonNull View itemView, PostAdapter.ItemClickListener itemClickListener) {
        super(itemView);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());

        ivProfileImage = itemView.findViewById(R.id.post_profile_image);
        tvUserName = itemView.findViewById(R.id.post_username);
        postText = itemView.findViewById(R.id.post_text);
        postTarih = itemView.findViewById(R.id.post_tarih);
        likeCount = itemView.findViewById(R.id.post_like_count);
        commentCount = itemView.findViewById(R.id.post_comment_count);
        btnLike = itemView.findViewById(R.id.post_btn_like);
        btnComment = itemView.findViewById(R.id.post_btn_comment);
        cardView = itemView.findViewById(R.id.cardItem);

        this.itemClickListener = itemClickListener;
        cardView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClick(v, getAdapterPosition());
    }
}
