package com.aytekincomez.yeniapp.Activity.Activity.comment;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aytekincomez.yeniapp.Activity.Adapter.CommentAdapter;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Comment;
import com.aytekincomez.yeniapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements CommentView {
    RecyclerView recyclerView;
    EditText etCommnet;
    TextView tvBosComment;
    Button btnSend;
    SwipeRefreshLayout swipeRefreshLayout;
    SessionManager sessionManager;
    String post_username;

    CommentAdapter adapter;
    List<Comment> commentList ;
    CommentPresenter presenter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        uiElement();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Drawable backArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);

        String postidd = getIntent().getExtras().getString("post_id");
        post_username = getIntent().getExtras().getString("user_name");
        int int_post_id = Integer.parseInt(postidd);

        presenter.getComment(int_post_id);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getComment(int_post_id);
        });



        btnSend.setOnClickListener(v -> {
            String comment = etCommnet.getText().toString().trim();
            HashMap<String, String> map = sessionManager.userDetail();
            String user_id = map.get(sessionManager.USERID);

            if(comment.isEmpty()){
                etCommnet.setError("Lütfen yorumunuzu giriniz");
            }else {
                presenter.saveComment(Integer.parseInt(user_id),int_post_id,comment,0);
                etCommnet.setText("");
                presenter.getComment(int_post_id);
                //presenter.updateCommentCount(postidd,"5");
            }
        });

    }

    public void uiElement(){
        recyclerView = findViewById(R.id.recyclerviewComment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = findViewById(R.id.swipe);

        etCommnet = findViewById(R.id.etCommentMessage);
        btnSend = findViewById(R.id.btnSend);
        tvBosComment = findViewById(R.id.tvBosComment);
        presenter = new CommentPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Lütfen Bekleyim");
        sessionManager = new SessionManager(getApplicationContext());
        //recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
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
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(List<Comment> list) {
        commentList = new ArrayList<>();
        adapter = new CommentAdapter(getApplicationContext(), list, post_username);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        commentList = list;
    }
}
