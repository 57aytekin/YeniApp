package com.aytekincomez.yeniapp.Activity.api;

import com.aytekincomez.yeniapp.Activity.Model.Post;
import com.aytekincomez.yeniapp.Activity.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<User> saveUser(
        @Field("name") String name,
        @Field("email") String email,
        @Field("password") String password
    );

    @GET("getpost.php")
    Call<List<Post>> getPost();

}
