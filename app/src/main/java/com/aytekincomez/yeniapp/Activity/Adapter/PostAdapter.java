package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aytekincomez.yeniapp.Activity.Activity.comment.CommentActivity;
import com.aytekincomez.yeniapp.Activity.Holder.PostHolder;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;
import com.aytekincomez.yeniapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostHolder>{

    private List<Post> postList;
    private Context context;
    private ItemClickListener itemClickListener;

    public PostAdapter(List<Post> postList, Context context, ItemClickListener itemClickListener) {
        this.postList = postList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext()).inflate(R.layout.fragment_home_satir_gorunumu, viewGroup, false);

        PostHolder holder = new PostHolder(itemView, itemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostHolder postHolder, final int position) {
        final Post post = postList.get(position);

        //Profil fragmentine veritabanından gelen image path yolluyoruz.

        String path = post.getImage();

        String imageUrl = "http://aytekincomezz.000webhostapp.com/YeniApp/"+path;
        Picasso.with(context).load(imageUrl).into(postHolder.ivProfileImage);

        postHolder.btnLike.setOnCheckedChangeListener(null);

        postHolder.tvUserName.setText(post.getUser_name());
        postHolder.postText.setText(post.getPost_text());


        //Veritabanından gelen tarih ile güncel tarihi çıkararak aradaki farkı buluyoruz
        String tarih = post.getTarih();
        tarihFarkiHesapla(tarih, postHolder.postTarih);


        //Yorum yap butonuna tıklayonca CommentActiviye geçiyor ve değerler intenle aktarılıyor.
        postHolder.btnComment.setOnClickListener(v -> {
            Intent i = new Intent(context.getApplicationContext(), CommentActivity.class);
            String user_id =String.valueOf(post.getUser_id());
            String post_id = String.valueOf(post.getPost_id());
            String user_name = post.getUser_name();
            i.putExtra("user_id",user_id);
            i.putExtra("post_id",post_id);
            i.putExtra("user_name",user_name);
            context.startActivity(i);
        });


        int comment_count = post.getComment_count();
        postHolder.commentCount.setText(comment_count+" Yorum");
        postHolder.commentCount.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), CommentActivity.class);
            String user_id =String.valueOf(post.getUser_id());
            String post_id = String.valueOf(post.getPost_id());
            String user_name = post.getUser_name();
            intent.putExtra("user_id",user_id);
            intent.putExtra("post_id",post_id);
            intent.putExtra("user_name",user_name);
            context.startActivity(intent);
        });

        int like_counts = post.getLike_count();
        postHolder.likeCount.setText(like_counts+" Beğeni");

        SharedPreferences sharedPrefs = context.getSharedPreferences("lol", Context.MODE_PRIVATE);
        Boolean a = sharedPrefs.getBoolean("abc"+position , false);
        if (a){
            postHolder.btnLike.setChecked(true);
        }else {
            postHolder.btnLike.setChecked(false);
        }


        postHolder.btnLike.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int like_count = post.getLike_count();

            if(isChecked){
                SharedPreferences.Editor editor = context.getSharedPreferences("lol", Context.MODE_PRIVATE).edit();
                editor.putBoolean("abc"+position, true);
                editor.apply();

                like_count ++;
                String likeC = String.valueOf(like_count);
                String post_id = String.valueOf(post.getPost_id());
                postHolder.likeCount.setText(like_count+" Beğeni");
                System.out.println("POST_ID ="+post_id+" LİKE COUNT => "+likeC);
                updateLikeCount(post_id,likeC);


            }else{
                SharedPreferences.Editor editor = context.getSharedPreferences("lol", Context.MODE_PRIVATE).edit();
                editor.putBoolean("abc"+position, false);
                editor.apply();

                String likeC = String.valueOf(like_count);
                String post_id = String.valueOf(post.getPost_id());
                postHolder.likeCount.setText(like_count+" Beğeni");
                updateLikeCount(post_id,likeC);
            }

        });

    }
    @Override
    public int getItemCount() {
        return postList.size();
    }

    private void updateLikeCount(final String post_id, final String likeC){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Post> call = apiInterface.updateLikeCount(likeC, post_id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    public void tarihFarkiHesapla(String tarih, TextView tv){

        SimpleDateFormat bicim = new SimpleDateFormat("dd.M.yyyy hh:mm:ss");

        try {
            Date date1 = bicim.parse(tarih);
            Date bugun = new Date();
            bicim.format(bugun);
            long difference = Math.abs(date1.getTime() - bugun.getTime());
            long dakika = difference / (1000*60);
            long saat = difference / (1000*60*60);
            long gun = difference / (1000*60*60*24);

            Long l = new Long(gun);
            int ay = l.intValue() /30;

            if(dakika == 0){
                tv.setText("Az önce");
            }else if(ay == 0 && gun==0 && saat == 0){
                tv.setText(dakika+" dk önce");
            }else if(ay == 0 && gun == 0){
                tv.setText(saat+ " saat önce");
            }else if(ay == 0){
                tv.setText(gun+" gün önce");
            }else {
                tv.setText(ay+" ay önce");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

}
