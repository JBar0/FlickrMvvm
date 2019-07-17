package com.example.jsonparse.services;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.room.FlickrDao;

public class DeleteDbService extends JobIntentService {
    private static final String TAG = "InsertDbService";
    public static final String RECEIVER = "receiver";
    private static final String ACTION_DELETE = "action.DELETE";

    private FlickrDao flickrDao;

    public DeleteDbService(FlickrDao flickrDao) {
        this.flickrDao = flickrDao;
    }

    public static void enqueue(Context context, Flickr flickr) {
        Intent intent = new Intent(context, DeleteDbService.class);
        intent.putExtra("flickr", flickr);
        intent.setAction(ACTION_DELETE);
        enqueueWork(context, DeleteDbService.class, 1, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Flickr flickr = intent.getParcelableExtra("flickr");
        if (intent.getAction() != null) {
            flickrDao.delete(flickr.getItems());
        }
    }
}
