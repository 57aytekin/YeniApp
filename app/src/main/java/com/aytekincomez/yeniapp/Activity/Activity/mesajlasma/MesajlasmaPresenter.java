package com.aytekincomez.yeniapp.Activity.Activity.mesajlasma;

import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesajlasmaPresenter {

    void pushNofication(String name, String comment_name, int durum){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.push(name, comment_name, durum);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
