package com.aytekincomez.yeniapp.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aytekincomez.yeniapp.Activity.Adapter.ClickListener;
import com.aytekincomez.yeniapp.Activity.Adapter.PostAdapter;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentHome extends Fragment{
    Toolbar toolbar;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<Post> postList;
    RecyclerView.LayoutManager layoutManager;
    RequestQueue requestQueue;
    private static final String URL_GETPOST = "http://aytekincomez.webutu.com/yeni/getpost.php";
    private static final String URL_USERNAME = "http://aytekincomez.webutu.com/yeni/getusername.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_fragment_home, container, false);

        postList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(view.getContext());
        listele();

        recyclerView = view.findViewById(R.id.recycler_home);
        postAdapter = new PostAdapter(postList, view.getContext(), new ClickListener() {
            @Override
            public void onPositionClicked(int position) {

            }
        });
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void listele(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_GETPOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject item = jsonArray.optJSONObject(i);

                                final int id = Integer.parseInt(item.getString("id"));
                                final int user_id = Integer.parseInt(item.getString("user_id"));
                                final String share_post = item.getString("share_post");
                                final int like_count = Integer.parseInt(item.getString("like_count"));
                                final int comment_count = Integer.parseInt(item.getString("comment_count"));
                                final String tarih = item.getString("tarih");

                                getUserName(id,user_id,share_post,like_count,comment_count,tarih);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getUserName(final int id, final int user_id, final String share_post, final int like_count, final int comment_count, final String tarih){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                URL_USERNAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("USER",response);
                        final String getUserName = response.toString();
                        postList.add(new Post(
                                id, user_id,getUserName, share_post, like_count, comment_count, tarih
                        ));
                        PostAdapter adapter = new PostAdapter(postList, getView().getContext(), new ClickListener() {
                            @Override
                            public void onPositionClicked(int position) {

                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String userid = String.valueOf(user_id);
                params.put("user_id",userid);
                return params;
            }
        };
        requestQueue.add(request);

    }
}
