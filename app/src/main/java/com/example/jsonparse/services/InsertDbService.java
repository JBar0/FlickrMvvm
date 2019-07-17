package com.example.jsonparse.services;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.room.FlickrDao;
import com.example.jsonparse.room.FlickrDatabase;

public class InsertDbService extends JobIntentService {
    private static final String TAG = "InsertDbService";
    public static final String RECEIVER = "receiver";
    private static final String ACTION_INSERT = "action.INSERT";
    private static final String ACTION_UPDATE = "action.UPDATE";
    private static final String ACTION_DELETE = "action.DELETE";

    public static void enqueue(Context context, Flickr flickr) {
        Intent intent = new Intent(context, InsertDbService.class);
        intent.putExtra("flickr", flickr);
        intent.setAction(ACTION_INSERT);
        enqueueWork(context, InsertDbService.class, 1, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        FlickrDao flickrDao = FlickrDatabase.getInstance(this).flickrDao();

        Flickr flickr = intent.getParcelableExtra("flickr");
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_INSERT:
                    flickrDao.insert(flickr.getItems());
                    break;
                case ACTION_UPDATE:
                    flickrDao.update(flickr.getItems());
                    break;
                case ACTION_DELETE:
                    flickrDao.delete(flickr.getItems());
                    break;
                default:
            }
        }
    }
}
