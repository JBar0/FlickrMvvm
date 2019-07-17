package com.example.jsonparse.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    private Controller() {

    }

    public static final String BASE_URL = "https://www.flickr.com/";

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static JsonHolderApi getJsonHolderApi() {
        return getRetrofitInstance().create(JsonHolderApi.class);
    }

}
