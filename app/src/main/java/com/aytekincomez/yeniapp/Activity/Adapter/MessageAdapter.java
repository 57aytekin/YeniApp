package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aytekincomez.yeniapp.Activity.Model.Chat;
import com.aytekincomez.yeniapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageView> {
    private Context context;
    private List<Chat> list;

    public MessageAdapter(Context context, List<Chat> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MessageView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_item, viewGroup, false);
        return new MessageView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageView messageView, int i) {
        Picasso.with(context).load(list.get(i).getPhoto()).into(messageView.ivPhoto);
        messageView.tvUsername.setText(list.get(i).getAliciName());
        messageView.tvMessage.setText(list.get(i).getMessage());

        String tarih = list.get(i).getTarih();
        tarihFarkiHesapla(tarih, messageView.tvDate);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageView extends RecyclerView.ViewHolder {
        private TextView tvUsername, tvMessage, tvDate;
        private CircleImageView ivPhoto;

        public MessageView(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.messageItemUserPhoto);
            tvUsername = itemView.findViewById(R.id.messageItemUsername);
            tvMessage = itemView.findViewById(R.id.messageItemMesaj);
            tvDate = itemView.findViewById(R.id.messageItemDate);
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
