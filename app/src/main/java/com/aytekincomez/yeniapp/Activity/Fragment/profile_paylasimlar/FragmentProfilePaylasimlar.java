package com.aytekincomez.yeniapp.Activity.Fragment.profile_paylasimlar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aytekincomez.yeniapp.Activity.Adapter.ProfilePaylasimlarAdapter;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentProfilePaylasimlar extends Fragment implements FragmentProfilePaylasimlarView {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FragmentProfilePaylasimlarPresenter presenter;
    ProfilePaylasimlarAdapter adapter;
    SessionManager sessionManager;

    List<Post> post;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_paylasimlar, container,false);
        swipeRefreshLayout = view.findViewById(R.id.profile_swipe);
        recyclerView = view.findViewById(R.id.profile_paylasimlar_recycler);
        sessionManager = new SessionManager(view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        HashMap<String, String> user = sessionManager.userDetail();
        String userid = user.get(sessionManager.USERID);
        int user_id = Integer.parseInt(userid);

        post = new ArrayList<>();
        presenter = new FragmentProfilePaylasimlarPresenter(this);
        presenter.getProfilePost(user_id);

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.getProfilePost(user_id));

        return view;
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Post> list) {
        adapter = new ProfilePaylasimlarAdapter(list, getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        post = list;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
