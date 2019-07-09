package com.example.jsonparse;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.network.JsonHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String TAG = "Repository";
    public static final String BASE_URL = "https://www.flickr.com/";
    private LiveData<Flickr> flickrLiveData;


    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static JsonHolderApi getJsonHolderApi() {
        return getRetrofitInstance().create(JsonHolderApi.class);
    }

    public LiveData<Flickr> getFlickr() {
        final MutableLiveData<Flickr> mutableLiveData = new MutableLiveData<>();
        JsonHolderApi jsonHolderApi = getJsonHolderApi();
        jsonHolderApi.getFlickrRec("json",1).enqueue(new Callback<Flickr>() {
            @Override
            public void onResponse(Call<Flickr> call, Response<Flickr> response) {
                Flickr flickr = response.body();
                mutableLiveData.setValue(flickr);
            }

            @Override
            public void onFailure(Call<Flickr> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

//    public Flickr getFlickr() {
//        final Flickr flickr = new Flickr();
//        JsonHolderApi jsonHolderApi = getJsonHolderApi();
//
//        jsonHolderApi.getFlickrRec("json", 1).enqueue(new Callback<Flickr>() {
//            @Override
//            public void onResponse(Call<Flickr> call, Response<Flickr> response) {
//                Flickr flickr1 = response.body();
//                flickr.setDescription(flickr1.getDescription());
//                flickr.setGenerator(flickr1.getGenerator());
//                flickr.setItems(flickr1.getItems());
//                flickr.setLink(flickr1.getLink());
//                flickr.setModified(flickr1.getModified());
//                flickr.setTitle(flickr1.getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<Flickr> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage());
//            }
//        });
//
//        return flickr;
//    }

}
