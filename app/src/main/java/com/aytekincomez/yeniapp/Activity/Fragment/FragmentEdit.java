package com.aytekincomez.yeniapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import java.util.HashMap;

public class FragmentEdit extends Fragment {

    TextView username;
    EditText etText;
    Post post = new Post();
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_edit, container, false);
        username = view.findViewById(R.id.fragment_edit_username);
        etText = view.findViewById(R.id.fragment_edit_etText);

        sessionManager = new SessionManager(view.getContext());
        HashMap<String, String> user = sessionManager.userDetail();
        String mUser = user.get(sessionManager.NAME);
        String userid = user.get(sessionManager.USERID);

        username.setText(mUser);

        return view;
    }
}
