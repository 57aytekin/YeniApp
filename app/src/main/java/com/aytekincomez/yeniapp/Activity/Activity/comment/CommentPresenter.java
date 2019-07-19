package com.aytekincomez.yeniapp.Activity.Activity.comment;

import com.aytekincomez.yeniapp.Activity.Model.Comment;
import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.Activity.api.ApiClient;
import com.aytekincomez.yeniapp.Activity.api.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CommentPresenter {

    private CommentView view;
    private ArrayList<Comment> comments;


    CommentPresenter(CommentView view) {
        this.view = view;
    }

    void saveComment(int user_id, int post_id, String comment, int begeniDurum){
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Comment> call = apiInterface.saveComment(user_id, post_id, comment, begeniDurum);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().isSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
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

    void getComment(int post_id){
        view.showLoading();
        comments = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Comment>> call = apiInterface.getComment();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null){
                    for (int i=0; i<response.body().size(); i++){
                        if(response.body().get(i).getPost_id() == post_id){
                            /*Log.d("POST",response.body().get(i).getComment());*/
                            view.hideLoading();
                            comments.add(response.body().get(i));
                            view.onGetResult(comments);
                        }
                    }
                    int yorumSayisi = comments.size();
                    updateCommentCount(String.valueOf(post_id),String.valueOf(yorumSayisi));
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                view.hideLoading();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    private void updateCommentCount(final String post_id, final String commentC){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Post> call = apiInterface.updateCommentCount(commentC, post_id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

}
