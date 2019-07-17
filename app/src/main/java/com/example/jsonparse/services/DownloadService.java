package com.example.jsonparse.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.network.Controller;
import com.example.jsonparse.network.JsonHolderApi;
import com.example.jsonparse.room.FlickrDatabase;

import java.io.IOException;

import retrofit2.Call;

public class DownloadService extends JobIntentService {
    private static final String TAG = "DownloadService";
    public static final String RECEIVER = "receiver";
    public static final String SEND_TEXT = "data";

    private ResultReceiver resReceiver;

    public static void enqueue(Context context, ResReceiver resReceiver) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(RECEIVER, resReceiver);
        enqueueWork(context, DownloadService.class, 12, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        JsonHolderApi jsonHolderApi = Controller.getJsonHolderApi();
        Call<Flickr> response = jsonHolderApi.getFlickrRec("json", 1);
        resReceiver = intent.getParcelableExtra(RECEIVER);
        resReceiver.send(ResReceiver.RUNNING, new Bundle());

        try {
            Flickr flickr = response.execute().body();
            FlickrDatabase.getInstance(this).flickrDao().insert(flickr.getItems());
//            InsertDbService.enqueue(this, flickr);
            resReceiver.send(ResReceiver.IDLE, new Bundle());
        } catch (IOException e) {
            Bundle bundle = new Bundle();
            bundle.putString(SEND_TEXT, e.getLocalizedMessage());
            resReceiver.send(ResReceiver.ERROR, bundle);
        }
    }
}
