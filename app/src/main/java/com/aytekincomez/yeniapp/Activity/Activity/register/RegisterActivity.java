package com.aytekincomez.yeniapp.Activity.Activity.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aytekincomez.yeniapp.Activity.Activity.login.LoginActivity;
import com.aytekincomez.yeniapp.Activity.Activity.main.MainActivity;

import com.aytekincomez.yeniapp.R;
import com.google.firebase.iid.FirebaseInstanceId;


public class RegisterActivity extends AppCompatActivity implements RegisterView{

    private EditText etUserName, etEmail, etPassword, etPasswordTry;
    private Button btnRegister;
    private ProgressDialog progressDialog;
    private TextView tvLogScreen;
    private int durum = 0;

    RegisterPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uiElement();
        btnRegister.setOnClickListener(v -> {
            String name = etUserName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String passwordTry = etPasswordTry.getText().toString().trim();
            String token = FirebaseInstanceId.getInstance().getToken();

            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            etEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(email.matches(emailPattern)){
                        durum = 1;
                    }else{
                        durum = 0;
                    }
                }
            });

            if(name.isEmpty() || name.length() <5 ){
                etUserName.setError("İsim alanı 5 karakterden küçük olamaz");
            }else if(email.isEmpty() || durum == 0){
                etEmail.setError("Lütfen geçerli bir e-mail adresi giriniz");
            }else if(password.isEmpty() || password.length() < 9){
                etPassword.setError("Şifreniz 9 karakterden küçük olamaz");
            }else if(passwordTry.isEmpty() ||passwordTry.length() < 9){
                etPasswordTry.setError("Şifreniz 9 karakterden küçük olamaz");
            }else if(!password.equals(passwordTry)){
                etPassword.setError("Eşleşmiyor");
                etPasswordTry.setError("Eşleşmiyor");
            }else {
                presenter.saveUser(name, email, password, token);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        tvLogScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void uiElement() {
        etUserName = findViewById(R.id.etRegisterUserName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        etPasswordTry = findViewById(R.id.etRegisterPasswordTry);
        btnRegister = findViewById(R.id.btnRegisterSignUp);
        tvLogScreen = findViewById(R.id.tvRegisterLog);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Lütfen Bekleyin");

        presenter = new RegisterPresenter(this);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
