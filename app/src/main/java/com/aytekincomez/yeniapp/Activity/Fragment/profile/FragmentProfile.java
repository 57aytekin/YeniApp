package com.aytekincomez.yeniapp.Activity.Fragment.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.aytekincomez.yeniapp.Activity.Activity.profileDuzenle.ProfileDuzenleActivity;
import com.aytekincomez.yeniapp.Activity.Fragment.FragmentProfileYakinda;
import com.aytekincomez.yeniapp.Activity.Fragment.profile_paylasimlar.FragmentProfilePaylasimlar;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.R;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentProfile extends Fragment implements FragmentProfileView {

    CircleImageView circleImageView;
    TextView tvUserName;
    Button btnDüzenle;
    TextView tvLogout, tvProfileBiografi;
    SessionManager sessionManager;
    private TabLayout tabLayout;
    Fragment fragment = null;

    ProgressDialog progressDialog;
    FragmentProfilePresenter presenter;

    HashMap<String, String> maps;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_profile, container, false);
        sessionManager = new SessionManager(view.getContext());
        sessionManager.checkLogin();

        tvLogout = view.findViewById(R.id.profile_logout);
        tabLayout = view.findViewById(R.id.profiletabLayout);
        circleImageView = view.findViewById(R.id.ivProfilePhoto);
        tvUserName = view.findViewById(R.id.tvProfileUserName);
        tvProfileBiografi = view.findViewById(R.id.tvProfileBiografi);
        btnDüzenle = view.findViewById(R.id.btnProfileDuzenle);


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Lütfen bekleyiniz");

        presenter = new FragmentProfilePresenter(this);

        HashMap<String, String> user = sessionManager.userDetail();
        int id = Integer.parseInt(user.get(sessionManager.USERID));
        presenter.getImage(id);



        btnDüzenle.setOnClickListener(v -> startActivity(new Intent(view.getContext(), ProfileDuzenleActivity.class)));

        tabLayout.addTab(tabLayout.newTab().setText("Paylaşımlar"));
        tabLayout.addTab(tabLayout.newTab().setText("Yakında..."));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentGecis(R.id.frameLayout, new FragmentProfilePaylasimlar());

        tabSelected();
        tvLogout.setOnClickListener(v -> sessionManager.logout());


        return view;
    }
    public void tabSelected(){
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragment = new FragmentProfilePaylasimlar();
                        break;
                    case 1:
                        fragment = new FragmentProfileYakinda();
                        break;

                }
                fragmentGecis(R.id.frameLayout, fragment);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void fragmentGecis(int i, Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(i,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onRequestSucces(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(HashMap<String, String> map) {
        String name = map.get("Name");
        String path = map.get("Paths");
        String biyografi = map.get("Biyografi");
        String imageUrl = "http://aytekincomez.webutu.com/yeni/"+path;

        maps = map;

        tvUserName.setText(name);
        tvProfileBiografi.setText(biyografi);
        Picasso.with(getContext()).load(imageUrl).into(circleImageView);
    }

}
