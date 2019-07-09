package com.example.jsonparse.network;

import com.example.jsonparse.models.Flickr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonHolderApi {

    @GET("services/feeds/photos_public.gne")
    Call<Flickr> getFlickrRec(@Query("format") String form,
                              @Query("nojsoncallback") int one);
}
