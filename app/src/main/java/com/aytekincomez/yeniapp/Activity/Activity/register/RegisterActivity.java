package com.aytekincomez.yeniapp.Activity.Activity.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aytekincomez.yeniapp.Activity.Activity.login.LoginActivity;
import com.aytekincomez.yeniapp.Activity.Activity.MainActivity;

import com.aytekincomez.yeniapp.R;


public class RegisterActivity extends AppCompatActivity implements RegisterView{

    private EditText etUserName, etEmail, etPassword, etPasswordTry;
    private Button btnRegister;
    private ProgressDialog progressDialog;
    private TextView tvLogScreen;

    RegisterPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uiElement();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUserName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String passwordTry = etPasswordTry.getText().toString().trim();

                if(name.isEmpty()){
                    etUserName.setError("Lütfen Doldurunuz");
                }else if(email.isEmpty()){
                    etEmail.setError("Lütfen Doldurunuz");
                }else if(password.isEmpty()){
                    etPassword.setError("Lütfen Doldurunuz");
                }else if(passwordTry.isEmpty()){
                    etPasswordTry.setError("Lütfen Doldurunuz");
                }else if(!password.equals(passwordTry)){
                    etPassword.setError("Eşleşmiyor");
                    etPasswordTry.setError("Eşleşmiyor");
                }else {
                    presenter.saveUser(name, email, password);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
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
