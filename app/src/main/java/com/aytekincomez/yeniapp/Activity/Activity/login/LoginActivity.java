package com.aytekincomez.yeniapp.Activity.Activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aytekincomez.yeniapp.Activity.Activity.main.MainActivity;
import com.aytekincomez.yeniapp.Activity.Activity.register.RegisterActivity;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.R;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{
    EditText etEmail, etPassword;
    Button btnSignIn;
    TextView tvRegister;
    ImageView ivFace, ivGoogle;
    ProgressBar loading;
    private static String Login_URL = "http://aytekincomez.webutu.com/yeni/login.php";
    SessionManager sessionManager;
    LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uiElement();
        presenter = new LoginPresenter();
        sessionManager = new SessionManager(this);

        btnSignIn.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (email.isEmpty()){
                etEmail.setError("Email adresinizi giriniz");

            }else if (pass.isEmpty()){
                etPassword.setError("Şifrenizi giriniz");
            }else{
                login(email, pass);
                loading.setVisibility(View.VISIBLE);
                btnSignIn.setVisibility(View.GONE);
                tvRegister.setVisibility(View.GONE);
            }
        });

        tvRegister.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
    }

    public void uiElement(){
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnSignIn = findViewById(R.id.btnRegisterSignUp);
        tvRegister = findViewById(R.id.tvLoginRegister);
        ivFace = findViewById(R.id.ivWithFace);
        ivGoogle = findViewById(R.id.ivWithGoogle);
        loading = findViewById(R.id.loginProgress);
    }

    public void login(final String email, final String password){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Login_URL,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("login");
                        Log.d("RESPONSE",response);
                        String token = FirebaseInstanceId.getInstance().getToken();

                        if (success.equals("1")){

                            for (int i = 0; i<jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String name = object.getString("name");
                                String email1 = object.getString("email");
                                int id = object.getInt("id");

                                sessionManager.createSession(name, email1, ""+id);
                                presenter.updateToken(id, token);

                                Toast.makeText(LoginActivity.this, "Giriş Başarılı Hoşgeldin: "+id, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }

                            loading.setVisibility(View.GONE);

                        }else {
                            Toast.makeText(LoginActivity.this, "Kullanıcı adı veya şifre hatalı", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btnSignIn.setVisibility(View.VISIBLE);
                            tvRegister.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        loading.setVisibility(View.GONE);
                        btnSignIn.setVisibility(View.VISIBLE);
                        tvRegister.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "ERROR"+e.toString(), Toast.LENGTH_SHORT).show();

                    }
                },
                error -> {
                    loading.setVisibility(View.GONE);
                    btnSignIn.setVisibility(View.VISIBLE);
                    tvRegister.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "ERROR"+error.toString(), Toast.LENGTH_SHORT).show();
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email",email);
                map.put("password",password);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
