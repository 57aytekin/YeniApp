package com.aytekincomez.yeniapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aytekincomez.yeniapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
