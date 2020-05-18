package com.aytekincomez.yeniapp.Activity.Fragment.message;

import com.aytekincomez.yeniapp.Activity.Model.MessageList;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMessagePresenter {
     FragmentMessageView view;
     private ArrayList<MessageList> messageLists;

    public FragmentMessagePresenter(FragmentMessageView view) {
        this.view = view;
    }

    public FragmentMessagePresenter() {
    }

    void getMessage_list(int gonderen_id){
        messageLists = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<MessageList>> call = apiInterface.getmessage_list();
        call.enqueue(new Callback<List<MessageList>>() {
            @Override
            public void onResponse(Call<List<MessageList>> call, Response<List<MessageList>> response) {
                if(response.isSuccessful() && response.body() != null){
                    for(int i=0; i<response.body().size(); i++){
                        if(response.body().get(i).getGonderen_id() == gonderen_id ||
                        response.body().get(i).getAlici_id() == gonderen_id){
                            messageLists.add(response.body().get(i));
                            view.onGetResult(messageLists);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MessageList>> call, Throwable t) {

            }
        });
    }
}
