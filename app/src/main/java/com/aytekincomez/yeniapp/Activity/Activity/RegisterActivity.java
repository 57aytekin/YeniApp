package com.aytekincomez.yeniapp.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aytekincomez.yeniapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {
    private EditText etUserName, etEmail, etPassword, etPasswordTry;
    private Button btnRegister;
    private ProgressBar loading;
    private TextView tvLogScreen;
    private static String URL_REGIST = "http://aytekincomez.webutu.com/yeni/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uiElement();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });
        tvLogScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    public void uiElement(){
        etUserName = findViewById(R.id.etRegisterUserName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        etPasswordTry = findViewById(R.id.etRegisterPasswordTry);
        btnRegister = findViewById(R.id.btnRegisterSignUp);
        loading = findViewById(R.id.loading);
        tvLogScreen = findViewById(R.id.tvRegisterLog);
    }
    public void regist(){
        loading.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.GONE);

        final String name = this.etUserName.getText().toString().trim();
        final String email = this.etEmail.getText().toString().trim();
        final String password = this.etPassword.getText().toString().trim();
        final String password_confirm = this.etPasswordTry.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(RegisterActivity.this, "Kayıt Başarılı", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Hata :"+e.toString(), Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                            btnRegister.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "ERROR"+ error.toString(), Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                        btnRegister.setVisibility(View.VISIBLE);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
