package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Comment;
import com.aytekincomez.yeniapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private List<Comment> comments;
    private Context context;

    public CommentAdapter(Context context, List<Comment> comments){
        this.comments = comments;
        this.context = context;
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
        String user_name = user.get(sessionManager.NAME);

        if((comments.get(i).getName()).equals(user_name)){
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
            if(isChecked){
                myViewHolder.btnLike.setBackgroundResource(R.drawable.ic_favorite_orange);
                comments.get(i).setBegeniDurum(1);
            }else{
                myViewHolder.btnLike.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                comments.get(i).setBegeniDurum(0);
            }
        });

        String commentNname = comments.get(i).getComment();
        String[] dizi = commentNname.split("-/-");
        String name = dizi[0];
        if(name.equals(user_name)){
            myViewHolder.btnLike.setVisibility(View.GONE);
        }
        String comment = dizi[1];
        String bold = "<b>"+name+"</b>"+" "+comment;
        myViewHolder.tvCommnet.setText(Html.fromHtml(bold));
        myViewHolder.tvTarih.setText(comments.get(i).getTarih());
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
}
