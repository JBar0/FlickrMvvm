package com.example.jsonparse.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.network.JsonHolderApi;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadService extends JobIntentService {
    private static final String TAG = "DownloadService";
    public static final String BASE_URL = "https://www.flickr.com/";
    public static final String RECEIVER = "receiver";
    public static final String SEND_TEXT = "data";
    public static final int GOOD_RESULT = 1;
    public static final int BAD_RESULT = 2;

    private ResReceiver resReceiver;

    private MutableLiveData<Boolean> mElapsedTime = new MutableLiveData<>();


    public static void enqueue(Context context, ResReceiver resReceiver) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(RECEIVER, resReceiver);
        enqueueWork(context, DownloadService.class, 12, intent);
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static JsonHolderApi getJsonHolderApi() {
        return getRetrofitInstance().create(JsonHolderApi.class);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        JsonHolderApi jsonHolderApi = getJsonHolderApi();
        Call<Flickr> response = jsonHolderApi.getFlickrRec("json", 1);
        resReceiver = intent.getParcelableExtra(RECEIVER);
        Bundle bundle = new Bundle();

        try {
            Flickr flickr = response.execute().body();
            InsertDbService.enqueue(this, flickr);
            mElapsedTime.setValue(true);
            bundle.putString(SEND_TEXT, "Good");
            resReceiver.send(GOOD_RESULT, bundle);
        } catch (IOException e) {
            Log.e(TAG, "onHandleWork: " + e.getMessage());
            mElapsedTime.setValue(false);
            bundle.putString(SEND_TEXT, "Good");
            resReceiver.send(BAD_RESULT, bundle);
        }
    }

    public MutableLiveData<Boolean> getmElapsedTime() {
        return mElapsedTime;
    }
}
