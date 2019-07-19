package com.aytekincomez.yeniapp.Activity.Activity.login;

import android.util.Log;

import com.aytekincomez.yeniapp.Activity.Model.User;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    void updateToken(int id, String token){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.update_token(id, token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().isSuccess();
                    if(success){
                        Log.d("Success", response.message());
                    }else{
                        Log.d("Success", response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
