package com.aytekincomez.yeniapp.Activity.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aytekincomez.yeniapp.Activity.Holder.PostHolder;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import java.util.HashMap;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {
    private List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext()).inflate(R.layout.fragment_home_satir_gorunumu, null);
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostHolder postHolder, final int i) {
        final Post post = postList.get(i);


        int comment_count = post.getComment_count();

        postHolder.tvUserName.setText(post.getUser_name());
        postHolder.postText.setText(post.getPost_text());

        postHolder.commentCount.setText(comment_count+" Yorum");
        postHolder.likeCount.setText(post.getLike_count()+" Beğeni");
        postHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = postHolder.btnLike.isChecked();
                int like_count = post.getLike_count();
                if(on){
                    like_count++;
                    postHolder.likeCount.setText(like_count+" Beğeni");
                }else{
                    postHolder.likeCount.setText(post.getLike_count()+" Beğeni");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
