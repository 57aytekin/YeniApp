package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Comment;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;
import com.aytekincomez.yeniapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private List<Comment> comments;
    private Context context;
    private String post_username;

    public CommentAdapter(Context context, List<Comment> comments, String post_username){
        this.comments = comments;
        this.context = context;
        this.post_username = post_username;
    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_satir_gorunumu, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder myViewHolder, int i) {
        SessionManager sessionManager = new SessionManager(context);
        HashMap<String, String> user = sessionManager.userDetail();
        String user_name = user.get(SessionManager.NAME);
        int user_id = Integer.parseInt(user.get(SessionManager.USERID));

        if((!(comments.get(i).getName()).equals(user_name) && (post_username.equals(user_name)))){
            myViewHolder.btnLike.setVisibility(View.VISIBLE);
            if (comments.get(i).getBegeniDurum() == 1){
                myViewHolder.btnLike.setBackgroundResource(R.drawable.ic_favorite_orange);
                myViewHolder.btnLike.setChecked(true);
            }else{
                myViewHolder.btnLike.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                myViewHolder.btnLike.setChecked(false);
            }
        }else{
            myViewHolder.btnLike.setVisibility(View.GONE);
        }




        myViewHolder.btnLike.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int durum = -1;
            if(isChecked){
                durum = 0;
                myViewHolder.btnLike.setBackgroundResource(R.drawable.ic_favorite_orange);
                updateCommentLike(comments.get(i).getId(), 1);
                saveLikes(user_id, comments.get(i).getUser_id(), comments.get(i).getId());
                pushNotification(post_username, comments.get(i).getName(), durum);

            }else{
                myViewHolder.btnLike.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                updateCommentLike(comments.get(i).getId(), 0);
            }
        });

        String commentUserName = comments.get(i).getName();
        String comment = comments.get(i).getComment();

        /*if(post_username.equals(comments.get(i).getName())){
            myViewHolder.btnLike.setVisibility(View.GONE);
        }*/
        String bold = "<b>"+commentUserName+"</b>"+" "+comment;
        myViewHolder.tvCommnet.setText(Html.fromHtml(bold));

        //Veritabanından gelen tarih ile güncel tarihi çıkararak aradaki farkı buluyoruz
        String tarih = comments.get(i).getTarih();
        tarihFarkiHesapla(tarih, myViewHolder.tvTarih);

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView ivResim;
        private ToggleButton btnLike;
        private TextView tvCommnet, tvTarih;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivResim = itemView.findViewById(R.id.iv_comment_photo);
            btnLike = itemView.findViewById(R.id.toggle);
            tvCommnet = itemView.findViewById(R.id.tvCommentAd);
            tvTarih = itemView.findViewById(R.id.tv_comment_tarih);
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

    public void updateCommentLike(int comment_id, int begeniDurum){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.update_comment_like(comment_id, begeniDurum);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveLikes(int post_sahibi_id, int comment_sahibi_id, int comment_id){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.saveLikes(post_sahibi_id, comment_sahibi_id, comment_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("LİKES",response.toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("LİKES", t.getLocalizedMessage());
            }
        });
    }

    void pushNotification(String name, String comment_name, int durum){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.push(name, comment_name, durum);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
