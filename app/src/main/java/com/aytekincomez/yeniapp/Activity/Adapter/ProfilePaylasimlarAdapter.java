package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aytekincomez.yeniapp.Activity.Activity.comment.CommentActivity;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProfilePaylasimlarAdapter extends RecyclerView.Adapter<ProfilePaylasimlarAdapter.MyViewHolder> {

    List<Post> postList;
    Context context;

    public ProfilePaylasimlarAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfilePaylasimlarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_paylasimlar_satir_gorunumu, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePaylasimlarAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.post_text.setText(postList.get(i).getPost_text());

        String tarih = postList.get(i).getTarih();
        tarihFarkiHesapla(tarih, myViewHolder.tarih);

        int like_counts = postList.get(i).getLike_count();
        myViewHolder.like_count.setText(like_counts+" Beğeni");


        int comment_count = postList.get(i).getComment_count();
        myViewHolder.comment_count.setText(comment_count+" Yorum");
        myViewHolder.comment_count.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), CommentActivity.class);
            String user_id =String.valueOf(postList.get(i).getUser_id());
            String post_id = String.valueOf(postList.get(i).getPost_id());
            String user_name = postList.get(i).getUser_name();
            intent.putExtra("user_id",user_id);
            intent.putExtra("post_id",post_id);
            intent.putExtra("user_name",user_name);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tarih, post_text, like_count, comment_count;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarih = itemView.findViewById(R.id.profile_paylasim_tarih);
            post_text = itemView.findViewById(R.id.profile_paylasim_post_text);
            like_count = itemView.findViewById(R.id.profile_paylasim_like_count);
            comment_count = itemView.findViewById(R.id.profile_paylasim_comment_count);
        }
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
}
