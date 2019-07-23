package com.aytekincomez.yeniapp.Activity.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aytekincomez.yeniapp.Activity.Adapter.ChatAdapter;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Chat;
import com.aytekincomez.yeniapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MesajlasmaActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivMesajlasmaBack;
    CircleImageView ivMesajlasmaPhoto;
    TextView tvUsername;
    RecyclerView recyclerView;
    EditText etMesaj;
    Button btnMesaj;
    SessionManager sessionManager;

    ChatAdapter chatAdapter;
    List<Chat> chats;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlasma);
        uiElement();
        ivMesajlasmaBack.setOnClickListener(this);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> map = sessionManager.userDetail();
        String userid = map.get(sessionManager.USERID);

        String name = getIntent().getStringExtra("post_paylasan");
        String photo = getIntent().getStringExtra("photo");
        String post_paylasan_id = getIntent().getStringExtra("post_sahibi_id");

        btnMesaj.setOnClickListener(v -> {
            if(!etMesaj.equals("")){
                sendMessage(userid, post_paylasan_id, etMesaj.getText().toString(), "121212");
            }else{
                etMesaj.setError("Herhangi bir mesaj girmediniz");
            }
            etMesaj.setText("");
            System.out.println("USER ID=> "+userid+" PAYLASAN ID=> "+post_paylasan_id);
            readMessage(userid, post_paylasan_id);
        });

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readMessage(userid, post_paylasan_id);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tvUsername.setText(name);
        Picasso.with(getApplicationContext()).load(photo).into(ivMesajlasmaPhoto);
    }

    public void uiElement(){
        ivMesajlasmaBack = findViewById(R.id.mesajlasmaBack);
        ivMesajlasmaPhoto = findViewById(R.id.ivMesajlasmaPhoto);
        tvUsername = findViewById(R.id.tvMesajlasmaUsername);
        etMesaj = findViewById(R.id.etMesaj);
        btnMesaj = findViewById(R.id.btnMesajlasma);
        //recylerView
        recyclerView = findViewById(R.id.recyclerMesajlasma);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mesajlasmaBack:
                finish();
        }

    }

    private void sendMessage(String gonderen, String alici, String message, String tarih){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, String> map = new HashMap<>();
        map.put("gonderen", gonderen);
        map.put("alici", alici);
        map.put("message",message);
        map.put("tarih",tarih);

        reference.child("Chats").push().setValue(map);
    }

    private void readMessage(String gondere, String alici){
        chats = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chats.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    System.out.println("GONDEREN => "+chat.getGonderen()+" ALICI => "+chat.getAlici()+" USEr=>"+gondere);
                    if(chat.getAlici().equals(alici) && chat.getGonderen().equals(gondere)
                            || chat.getAlici().equals(gondere) && chat.getGonderen().equals(alici)){
                        chats.add(chat);
                    }

                    System.out.println("GONDEREN => "+chat.getGonderen());
                    chatAdapter = new ChatAdapter(getApplicationContext(), chats);
                    recyclerView.setAdapter(chatAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
