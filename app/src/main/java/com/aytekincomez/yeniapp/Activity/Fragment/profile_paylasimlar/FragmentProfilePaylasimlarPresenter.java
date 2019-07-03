package com.aytekincomez.yeniapp.Activity.Fragment.profile_paylasimlar;

import com.aytekincomez.yeniapp.Activity.Fragment.home.FragmenHomeView;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfilePaylasimlarPresenter {

    private FragmentProfilePaylasimlarView view;

    public FragmentProfilePaylasimlarPresenter(FragmentProfilePaylasimlarView view) {
        this.view = view;
    }

    public void getProfilePost(int user_id){
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Post>> call = apiInterface.getPaylasimPost(user_id);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.hideLoading();
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
