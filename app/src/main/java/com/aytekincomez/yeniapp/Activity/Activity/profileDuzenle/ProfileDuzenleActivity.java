package com.aytekincomez.yeniapp.Activity.Activity.profileDuzenle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.User;
import com.aytekincomez.yeniapp.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDuzenleActivity extends AppCompatActivity implements View.OnClickListener, ProfilDuzenleView {
    private CircleImageView ivPhoto;
    private EditText etUserName, etBiyografi;
    private TextView tvText;
    SessionManager sessionManager;
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;
    ProfilDuzenlePresenter presenter;
    private ProgressDialog progressDialog;
    int nameDurum, bioDurum, photoDurum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_duzenle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uiElement();
        ivPhoto.setOnClickListener(this);
        tvText.setOnClickListener(this);


        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && etUserName != s){
                    System.out.println("Name => "+s);
                    System.out.println("Name degisti");
                    nameDurum = 1;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etBiyografi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    bioDurum = 1;
                    System.out.println("Biyografi degisti");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        HashMap<String, String> map = sessionManager.userDetail();
        int id = Integer.parseInt(map.get(sessionManager.USERID));
        presenter.get_image(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivDuzenlePhoto:
                selectImage();
                photoDurum = 1;
                break;
            case R.id.tvDuzenleText:
                selectImage();
                photoDurum = 1;
                break;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_ok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.accept){
            HashMap<String, String> user = sessionManager.userDetail();
            int id = Integer.parseInt(user.get(sessionManager.USERID));
            String name = user.get(sessionManager.NAME);


            ProfilDuzenlePresenter presenter = new ProfilDuzenlePresenter(this);
            //presenter.uploadImage(id, name, image);

            if(nameDurum == 1){
                presenter.updateName(id,etUserName.getText().toString().trim());
                nameDurum = 0;

            }if(bioDurum == 1){
                presenter.updateBio(id, etBiyografi.getText().toString().trim());
                bioDurum = 0;

            }if(photoDurum == 1){
                String image = imageToString();
                presenter.uploadImage(id,name, image);
                photoDurum = 0;
            }

        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                ivPhoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void uiElement(){
        ivPhoto = findViewById(R.id.ivDuzenlePhoto);
        etUserName = findViewById(R.id.etDuzenleKullaniciAd);
        etBiyografi = findViewById(R.id.etDuzenleBiyografi);
        tvText = findViewById(R.id.tvDuzenleText);
        presenter = new ProfilDuzenlePresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Lütfen bekleyiniz..");

        sessionManager = new SessionManager(this);

        final Drawable backArrow = getResources().getDrawable(R.drawable.ic_close_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
    }


    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<User> user) {
        String name = user.get(0).getName();
        String path = user.get(0).getPaths();

        String imageUrl = "http://aytekincomezz.000webhostapp.com/YeniApp/"+path;
        etUserName.setText(name);
        Picasso.with(getApplicationContext()).load(imageUrl).into(ivPhoto);

    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }
}
