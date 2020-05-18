package com.aytekincomez.yeniapp.Activity.Activity.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aytekincomez.yeniapp.Activity.Fragment.edit.FragmentEdit;
import com.aytekincomez.yeniapp.Activity.Fragment.home.FragmentHome;
import com.aytekincomez.yeniapp.Activity.Fragment.likes.FragmentLikes;
import com.aytekincomez.yeniapp.Activity.Fragment.message.FragmentMessage;
import com.aytekincomez.yeniapp.Activity.Fragment.profile.FragmentProfile;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.R;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FloatingActionButton fabEdit;
    SessionManager sessionManager;
    Fragment fragment = null;
    int tabCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uiElement();
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new FragmentHome());
        fragmentTransaction.commit();

        tabSeleceted();
        fabEdit.setOnClickListener(v -> {
            FragmentManager fm1 = getSupportFragmentManager();
            FragmentTransaction ft1 = fm1.beginTransaction();
            fragment = new FragmentEdit();
            ft1.replace(R.id.container, fragment);
            ft1.commit();
        });


    }

    public void tabSeleceted(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabCount = 0;
                        fragment = new FragmentHome();
                        tab.setIcon(R.drawable.ic_home_black_24dp);
                        break;

                    case 1:
                        tabCount = 1;
                        fragment = new FragmentLikes();
                        tab.setIcon(R.drawable.ic_likes_black_24dp);
                        break;
                    case 2:
                        tabCount = 2;
                        fragment = new FragmentMessage();
                        tab.setIcon(R.drawable.ic_message_24dp);
                        break;
                    case 3:
                        tabCount = 3;
                        fragment = new FragmentProfile();
                        tab.setIcon(R.drawable.ic_profile_black_24dp);
                        break;
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.drawable.ic_home_icon);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_likes_icon);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_message_icon);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.ic_profile_icon);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabCount = 0;
                        fragment = new FragmentHome();
                        tab.setIcon(R.drawable.ic_home_black_24dp);
                        break;

                    case 1:
                        tabCount = 1;
                        fragment = new FragmentLikes();
                        tab.setIcon(R.drawable.ic_likes_black_24dp);
                        break;
                    case 2:
                        tabCount = 2;
                        fragment = new FragmentMessage();
                        tab.setIcon(R.drawable.ic_message_24dp);
                        break;
                    case 3:
                        tabCount = 3;
                        fragment = new FragmentProfile();
                        tab.setIcon(R.drawable.ic_profile_black_24dp);
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.commit();
            }
        });
    }

    public void uiElement(){
        tabLayout = findViewById(R.id.tabLayout);
        fabEdit = findViewById(R.id.fabEdit);
    }
}
