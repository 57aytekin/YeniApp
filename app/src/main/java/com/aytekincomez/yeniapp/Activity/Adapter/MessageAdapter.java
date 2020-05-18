package com.aytekincomez.yeniapp.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aytekincomez.yeniapp.Activity.Activity.mesajlasma.MesajlasmaActivity;
import com.aytekincomez.yeniapp.Activity.Fragment.message.FragmentMessagePresenter;
import com.aytekincomez.yeniapp.Activity.Fragment.message.RecyclerItemClickListener;
import com.aytekincomez.yeniapp.Activity.Model.MessageList;
import com.aytekincomez.yeniapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageView> {
    private Context context;
    private List<MessageList> list;
    private String username;
    private FragmentMessagePresenter presenter;
    private RecyclerView recyclerView;

    public MessageAdapter(Context context, List<MessageList> list, String username, RecyclerView recyclerView){
        this.context = context;
        this.list = list;
        this.username = username;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public MessageView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_item, viewGroup, false);
        presenter = new FragmentMessagePresenter();
        return new MessageView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageView messageView, int i) {
        if(username.equals(list.get(i).getAlici_name())){
            String photo = list.get(i).getPaths();
            String photoUrl = "http://aytekincomezz.000webhostapp.com/YeniApp/"+photo;
            Picasso.with(context).load(photoUrl).into(messageView.ivPhoto);
            messageView.tvUsername.setText(list.get(i).getName());

        }else if(username.equals(list.get(i).getName())){
            Picasso.with(context).load(list.get(i).getAlici_photo()).into(messageView.ivPhoto);
            messageView.tvUsername.setText(list.get(i).getAlici_name());
        }
        messageView.tvMessage.setText(list.get(i).getMessage());
        String tarih = list.get(i).getTarih();
        tarihFarkiHesapla(tarih, messageView.tvDate);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, MesajlasmaActivity.class);

                if (username.equals(list.get(position).getAlici_name())){
                    String photo = list.get(position).getPaths();
                    String photoUrl = "http://aytekincomezz.000webhostapp.com/YeniApp/"+photo;
                    intent.putExtra("photo", photoUrl);
                    intent.putExtra("post_paylasan",list.get(position).getName());
                    String post_sahibi_id = String.valueOf(list.get(position).getGonderen_id());
                    intent.putExtra("post_sahibi_id", post_sahibi_id);
                }else{
                    intent.putExtra("photo", list.get(position).getAlici_photo());
                    intent.putExtra("post_paylasan", list.get(position).getAlici_name());
                    String post_sahibi_id = String.valueOf(list.get(position).getAlici_id());
                    intent.putExtra("post_sahibi_id", post_sahibi_id);
                }

                context.startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
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
