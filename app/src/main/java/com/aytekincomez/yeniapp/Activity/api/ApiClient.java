package com.aytekincomez.yeniapp.Activity.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://aytekincomez.webutu.com/yeni/";
    private static final String PHOTO_URL = "http://aytekincomez.webutu.com/yeni/image/";
    private static Retrofit retrofit;

    public static Retrofit getApiPhotoClient(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(PHOTO_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
