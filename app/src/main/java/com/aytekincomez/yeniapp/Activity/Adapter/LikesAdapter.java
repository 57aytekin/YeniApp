package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aytekincomez.yeniapp.Activity.Activity.mesajlasma.MesajlasmaActivity;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Likes;
import com.aytekincomez.yeniapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.MyViewHolder> {

    private Context context;
    private List<Likes> likes;

    public LikesAdapter(Context context, List<Likes> likes) {
        this.context = context;
        this.likes = likes;
    }

    @NonNull
    @Override
    public LikesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_likes_satir_gorunumu,viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesAdapter.MyViewHolder myViewHolder, int i) {
        SessionManager sessionManager = new SessionManager(context);
        HashMap<String, String> user = sessionManager.userDetail();
        int user_id = Integer.parseInt(user.get(SessionManager.USERID));

        if(user_id == likes.get(i).getComment_sahibi_id()){
            String yazi =" adlı kullanıcı yorumunu beğendi artık mesaj atıp konuşabilirsin.";
            String post_paylasan ="<b>"+ likes.get(i).getName()+"</b> "+yazi;
            myViewHolder.tvUserName.setText(Html.fromHtml(post_paylasan));
            myViewHolder.ivPhoto.setVisibility(View.VISIBLE);

            //Bildiri photo larını çekiyoruz
            String photo_link = "http://aytekincomezz.000webhostapp.com/YeniApp/"+likes.get(i).getPaths();
            Picasso.with(context).load(photo_link).into(myViewHolder.ivPhoto);

            //İsme tıklanınca mesaj ekranı acılacak
            myViewHolder.tvUserName.setOnClickListener(v -> {
                Intent intent = new Intent(context, MesajlasmaActivity.class);
                intent.putExtra("post_paylasan",likes.get(i).getName());
                String post_sahibi_id = String.valueOf(likes.get(i).getPost_sahibi_id());
                intent.putExtra("post_sahibi_id", post_sahibi_id);
                intent.putExtra("photo", photo_link);
                context.startActivity(intent);
            });
        }else{
            //bildiri yoksa ekranan yaz
            myViewHolder.tvUserName.setText("Henüz bildiriminiz yok.");
            //Bildiri yoksa resmi kaldır
            myViewHolder.ivPhoto.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return likes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName;
        private CircleImageView ivPhoto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.likes_UserName);
            ivPhoto = itemView.findViewById(R.id.iv_likes_satir_photo);
        }
    }
}
