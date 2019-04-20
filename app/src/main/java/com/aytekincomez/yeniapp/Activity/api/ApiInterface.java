package com.aytekincomez.yeniapp.Activity.api;

import com.aytekincomez.yeniapp.Activity.Model.Comment;
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

    @FormUrlEncoded
    @POST("save_comment.php")
    Call<Comment> saveComment(
      @Field("post_id") int post_id,
      @Field("comment") String comment,
      @Field("begeniDurum") int begeniDurum
    );

    @FormUrlEncoded
    @POST("update_like_count.php")
    Call<Post> updateLikeCount(
      @Field("like_count") String like_count,
      @Field("post_id") String post_id
    );

    @FormUrlEncoded
    @POST("update_comment.php")
    Call<Post> updateCommentCount(
            @Field("comment_count") String comment_count,
            @Field("post_id") String post_id
    );


    @GET("getpost.php")
    Call<List<Post>> getPost();

    @GET("get_comment.php")
    Call<List<Comment>> getComment();

}
