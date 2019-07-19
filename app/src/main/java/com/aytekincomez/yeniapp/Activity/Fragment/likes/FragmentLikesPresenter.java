package com.aytekincomez.yeniapp.Activity.Fragment.likes;

import com.aytekincomez.yeniapp.Activity.Model.Likes;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLikesPresenter {
    private FragmentLikesView view;
    private ArrayList<Likes> likes;

    public FragmentLikesPresenter(FragmentLikesView view) {
        this.view = view;
    }


    public void getLikes(int user_id){
        view.showProgress();
        likes = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Likes>> call = apiInterface.getLikes();
        call.enqueue(new Callback<List<Likes>>() {
            @Override
            public void onResponse(Call<List<Likes>> call, Response<List<Likes>> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    for(int i =0; i < response.body().size(); i++){
                        if(response.body().get(i).getComment_sahibi_id() == user_id){
                            view.hideProgress();
                            likes.add(response.body().get(i));
                            view.onGetResult(likes);
                        }
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<List<Likes>> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
