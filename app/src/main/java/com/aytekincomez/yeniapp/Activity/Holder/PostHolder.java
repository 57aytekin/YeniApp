package com.aytekincomez.yeniapp.Activity.Holder;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.aytekincomez.yeniapp.Activity.Adapter.ClickListener;
import com.aytekincomez.yeniapp.R;

import java.lang.ref.WeakReference;

public class PostHolder extends RecyclerView.ViewHolder {

    public ImageView ivProfileImage;
    public TextView tvUserName, postText,postTarih, likeCount, commentCount;
    public Button btnComment;
    public ToggleButton btnLike;
    private WeakReference<ClickListener> listenerRef;
    SharedPreferences sharedPreferences;

    public PostHolder(@NonNull View itemView) {
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


    }

}
