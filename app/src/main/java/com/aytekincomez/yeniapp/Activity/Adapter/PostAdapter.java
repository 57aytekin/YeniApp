package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aytekincomez.yeniapp.Activity.Holder.PostHolder;
import com.aytekincomez.yeniapp.Activity.Interfaces.ClickListener;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {
    private List<Post> postList;
    private final ClickListener listener;
    private final static String likeUpdateURL = "http://aytekincomez.webutu.com/yeni/update_like_count.php";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    private WeakReference<ClickListener> listenerRef;


    public PostAdapter(List<Post> postList, Context context, ClickListener listener) {
        this.postList = postList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext()).inflate(R.layout.fragment_home_satir_gorunumu, null);
        PostHolder holder = new PostHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostHolder postHolder, final int position) {
        final Post post = postList.get(position);

        int comment_count = post.getComment_count();

        postHolder.tvUserName.setText(post.getUser_name());
        postHolder.postText.setText(post.getPost_text());
        postHolder.postTarih.setText(post.getTarih());

        postHolder.commentCount.setText(comment_count+" Yorum");
        postHolder.likeCount.setText(post.getLike_count()+" Beğeni");

        SharedPreferences sharedPrefs = context.getSharedPreferences("lol", Context.MODE_PRIVATE);
        Boolean a = sharedPrefs.getBoolean("abc"+position , false);
        if (a){
            postHolder.btnLike.setChecked(true);
        }else {
            postHolder.btnLike.setChecked(false);
        }


        postHolder.btnLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int like_count = post.getLike_count();

                if(isChecked){
                    SharedPreferences.Editor editor = context.getSharedPreferences("lol", Context.MODE_PRIVATE).edit();
                    like_count++;
                    editor.putBoolean("abc"+position, true);
                    editor.commit();
                }else{
                    SharedPreferences.Editor editor = context.getSharedPreferences("lol", Context.MODE_PRIVATE).edit();
                    like_count--;
                    editor.putBoolean("abc"+position, false);
                    editor.commit();
                }
                String likeC = String.valueOf(like_count);
                String post_id = String.valueOf(post.getPost_id());
                postHolder.likeCount.setText(like_count+" Beğeni");
                updateLikeCount(post_id,likeC, context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void updateLikeCount(final String post_id, final String likeC, Context getContext){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                likeUpdateURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("like_count",likeC);
                params.put("post_id",post_id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext);
        queue.add(stringRequest);
    }
}
