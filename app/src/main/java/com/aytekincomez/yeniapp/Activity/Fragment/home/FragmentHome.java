package com.aytekincomez.yeniapp.Activity.Fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.aytekincomez.yeniapp.Activity.Adapter.PostAdapter;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements FragmenHomeView{

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    FragmentHomePresenter presenter;
    PostAdapter adapter;

    List<Post> post;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_fragment_home, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_reflesh);
        recyclerView = view.findViewById(R.id.recycler_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        post = new ArrayList<>();

        presenter = new FragmentHomePresenter(this);
        presenter.getPost();

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getPost()
        );

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
    public void onGetResult(List<Post> posts) {
        adapter = new PostAdapter(posts, getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        post = posts;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
