package com.aytekincomez.yeniapp.Activity.api;

import com.aytekincomez.yeniapp.Activity.Model.Comment;
import com.aytekincomez.yeniapp.Activity.Model.Likes;
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
            @Field("password") String password,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("save_comment.php")
    Call<Comment> saveComment(
            @Field("user_id") int user_id,
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
    @POST("update_comment_like.php")
    Call<Void> update_comment_like(
            @Field("comment_id") int comment_id,
            @Field("begeniDurum") int begeniDurum
    );

    @FormUrlEncoded
    @POST("update_comment.php")
    Call<Post> updateCommentCount(
            @Field("comment_count") String comment_count,
            @Field("post_id") String post_id
    );

    @FormUrlEncoded
    @POST("get_paylasim_post.php")
    Call<List<Post>> getPaylasimPost(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("uploadimage.php")
    Call<User> uploadImage(
            @Field("id") int id,
            @Field("name") String name,
            @Field("image") String image
    );
    @FormUrlEncoded
    @POST("update_name.php")
    Call<User> update_name(
            @Field("id") int id,
            @Field("name") String name
    );
    @FormUrlEncoded
    @POST("update_biyografi.php")
    Call<User> update_biyografi(
            @Field("id") int id,
            @Field("biyografi") String biyografi
    );

    @FormUrlEncoded
    @POST("update_token.php")
    Call<User> update_token(
            @Field("id") int id,
            @Field("token") String token
    );

    @GET("getpost.php")
    Call<List<Post>> getPost();

    @GET("get_comment.php")
    Call<List<Comment>> getComment();

    @FormUrlEncoded
    @POST("get_image.php")
    Call<List<User>> getImage(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("save_likes.php")
    Call<Void> saveLikes(
            @Field("post_sahibi_id") int post_sahibi_id,
            @Field("comment_sahibi_id") int comment_sahibi_id,
            @Field("comment_id") int comment_id
    );

    @GET("get_likes.php")
    Call<List<Likes>> getLikes();

    @FormUrlEncoded
    @POST("push_notification.php")
    Call<Void> push(
            @Field("name") String name,
            @Field("comment_name") String comment_name,
            @Field("durum") int durum
    );

}
