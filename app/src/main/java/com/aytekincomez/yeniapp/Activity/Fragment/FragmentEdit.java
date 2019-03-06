package com.aytekincomez.yeniapp.Activity.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aytekincomez.yeniapp.Activity.Manager.SessionManager;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentEdit extends Fragment {

    TextView username, tvShare;
    EditText etText;
    Post post = new Post();
    SessionManager sessionManager;
    public static final String URL_POST = "http://aytekincomez.webutu.com/yeni/sharepost.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_fragment_edit, container, false);
        username = view.findViewById(R.id.fragment_edit_username);
        tvShare = view.findViewById(R.id.tvShare);
        etText = view.findViewById(R.id.fragment_edit_etText);

        sessionManager = new SessionManager(view.getContext());
        HashMap<String, String> user = sessionManager.userDetail();
        String mUser = user.get(sessionManager.NAME);
        username.setText(mUser);

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPost(view.getContext());
            }
        });
        return view;
    }

    public void addPost(final Context context){
        HashMap<String, String> user = sessionManager.userDetail();
        final String user_id = user.get(sessionManager.USERID);
        final String share_post = etText.getText().toString();
        final String tarih = "01.01.01";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            Log.d("RESPONSE",success);

                            if(success.equals("1")){
                                Toast.makeText(context, "Paylaşım yapıldı", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Paylaşım yapılırken hata", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params  = new HashMap<>();
                params.put("user_id",user_id);
                params.put("share_post",share_post);
                params.put("tarih",tarih);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }
}
