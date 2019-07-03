package com.aytekincomez.yeniapp.Activity.Activity.profileDuzenle;

import android.util.Log;
import android.widget.Toast;

import com.aytekincomez.yeniapp.Activity.Model.User;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilDuzenlePresenter {

    private ProfilDuzenleView view;

    ProfilDuzenlePresenter(ProfilDuzenleView view) {
        this.view = view;
    }

    void uploadImage(int id, String name, String image){
        ApiInterface apiInterface = ApiClient.getApiPhotoClient().create(ApiInterface.class);
        Call<User> call = apiInterface.uploadImage(id, name, image);
        view.showLoading();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("SONUC",response.body().getMessage());
                if(response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().isSuccess();
                    if(success){
                        view.hideLoading();
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.toString());
                    }
                }else{
                    view.onRequestError(response.body().getMessage());
                    view.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.onRequestError(t.getLocalizedMessage());
                view.hideLoading();
            }
        });
    }

    void get_image(int id){
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getImage(id);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful() && response.body() != null){
                    view.hideLoading();
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }


    void updateName(int id, String name) {
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.update_name(id, name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().isSuccess();
                    if(success){
                        view.onRequestSuccess(response.message());
                    }else{
                        view.onRequestError(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateBio(int id, String biyografi){
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.update_biyografi(id, biyografi);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().isSuccess();
                    if(success){
                        view.onRequestSuccess(response.message());
                    }else{
                        view.onRequestError(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
