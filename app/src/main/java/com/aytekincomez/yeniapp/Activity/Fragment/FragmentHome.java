package com.aytekincomez.yeniapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aytekincomez.yeniapp.Activity.Adapter.PostAdapter;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<Post> postList;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_home, container, false);

        postList = new ArrayList<>();
        listele();

        recyclerView = view.findViewById(R.id.recycler_home);
        postAdapter = new PostAdapter(postList);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void listele(){
        postList.add(new Post(1,1,"Bu içerik kısmı",5,5,"01.01.01"));
        postList.add(new Post(1,1,"Bu içerik kısmı",5,5,"01.01.01"));
        postList.add(new Post(1,1,"Bu içerik kısmı",5,5,"01.01.01"));
        postList.add(new Post(1,1,"Bu içerik kısmı",5,5,"01.01.01"));
        postList.add(new Post(1,1,"Bu içerik kısmı",5,5,"01.01.01"));
    }

}
