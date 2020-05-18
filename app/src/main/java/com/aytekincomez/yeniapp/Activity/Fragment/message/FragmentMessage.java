package com.aytekincomez.yeniapp.Activity.Fragment.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aytekincomez.yeniapp.Activity.Adapter.MessageAdapter;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.MessageList;
import com.aytekincomez.yeniapp.R;

import java.util.HashMap;
import java.util.List;

public class FragmentMessage extends Fragment implements FragmentMessageView {
    private MessageAdapter adapter;
    private RecyclerView recyclerView;
    private FragmentMessagePresenter presenter;
    private SessionManager sessionManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_message, container, false);
        recyclerView = view.findViewById(R.id.messageRecycler);
        presenter = new FragmentMessagePresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> map = sessionManager.userDetail();
        String user_id = map.get(sessionManager.USERID);

        presenter.getMessage_list(Integer.parseInt(user_id));

        return view;
    }


    @Override
    public void successMessage(String message) {

    }

    @Override
    public void errorMessage(String message) {

    }

    @Override
    public void onGetResult(List<MessageList> messageLists) {

        HashMap<String, String> map = sessionManager.userDetail();
        String username = map.get(sessionManager.NAME);
        String user_id = map.get(sessionManager.USERID);
        adapter = new MessageAdapter(getContext(), messageLists, username, recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}
