package com.aytekincomez.yeniapp.Activity.Fragment.profile;

import com.aytekincomez.yeniapp.Activity.Model.User;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfilePresenter {

    FragmentProfileView view;

    public FragmentProfilePresenter(FragmentProfileView view) {
        this.view = view;
    }

    public void getImage(int id){
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getImage(id);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful() && response.body() != null){
                    HashMap<String,String> map = new HashMap<>();
                    map.put("Name",response.body().get(0).getName());
                    map.put("Paths", response.body().get(0).getPaths());
                    map.put("Biyografi", response.body().get(0).getBiyografi());
                    view.onGetResult(map);
                    view.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
