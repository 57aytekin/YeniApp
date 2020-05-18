package com.aytekincomez.yeniapp.Activity.Activity.mesajlasma;

import com.aytekincomez.yeniapp.Activity.Model.MessageList;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesajlasmaPresenter {

    MesajlasmaView view;

    public MesajlasmaPresenter(MesajlasmaView view) {
        this.view = view;
    }

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

    void save_message_list(int gonderen_id, int alici_id, String alici_name, String alici_photo, String message){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.saveMessageList(gonderen_id, alici_id, alici_name, alici_photo, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    /*void getMessageList(int gonderen_id, int alici_id, String alici_name, String alici_photo, String message){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<MessageList>> call = apiInterface.getmessage_list();
        call.enqueue(new Callback<List<MessageList>>() {
            @Override
            public void onResponse(Call<List<MessageList>> call, Response<List<MessageList>> response) {

                if(response.isSuccessful() && response.body() != null){
                    for(int i=0; i<response.body().size(); i++){
                        if (response.body().get(i).getAlici_id() == gonderen_id && response.body().get(i).getGonderen_id() == alici_id
                                || response.body().get(i).getGonderen_id() == gonderen_id && response.body().get(i).getAlici_id() == alici_id){
                            updateMessageList(response.body().get(i).getId(),message);
                            break;
                        }else {
                            save_message_list(gonderen_id, alici_id, alici_name, alici_photo,message);

                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<MessageList>> call, Throwable t) {

            }
        });
    }*/

    void getMessage(int gonderen_id, int alici_id, String alici_name, String alici_photo, String message){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<MessageList>> call = apiInterface.getMessage(gonderen_id, alici_id);
        call.enqueue(new Callback<List<MessageList>>() {
            @Override
            public void onResponse(Call<List<MessageList>> call, Response<List<MessageList>> response) {
                if(response.body() != null && response.body().size() > 0 ){

                    updateMessageList(response.body().get(0).getId(), message);
                }else{
                    save_message_list(gonderen_id, alici_id, alici_name, alici_photo, message);
                }
            }

            @Override
            public void onFailure(Call<List<MessageList>> call, Throwable t) {

            }
        });
    }
    private void updateMessageList(int id, String message){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<MessageList> call = apiInterface.update_messageList(id, message);
        call.enqueue(new Callback<MessageList>() {
            @Override
            public void onResponse(Call<MessageList> call, Response<MessageList> response) {

            }

            @Override
            public void onFailure(Call<MessageList> call, Throwable t) {

            }
        });
    }
    private void deleteMessageList(int id){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Void> call = apiInterface.delete_messageList(id);
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
