package com.aytekincomez.yeniapp.Activity.Fragment.likes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aytekincomez.yeniapp.Activity.Adapter.LikesAdapter;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Likes;
import com.aytekincomez.yeniapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentLikes extends Fragment implements FragmentLikesView {

    //views
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    List<Likes> likesArrayList;
    LikesAdapter adapter;
    FragmentLikesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_likes, container, false);
        //init
        recyclerView = view.findViewById(R.id.recyler_likes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Lütfen Bekleyiniz");

        SessionManager sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.userDetail();
        int user_id = Integer.parseInt(user.get(SessionManager.USERID));

        presenter = new FragmentLikesPresenter(this);
        presenter.getLikes(user_id);

        return view;
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
    public void onGetResult(List<Likes> likes) {
        likesArrayList = new ArrayList<>();
        adapter = new LikesAdapter(getContext(), likes);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        likesArrayList = likes;
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
