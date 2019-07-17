package com.example.jsonparse;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.services.DownloadService;
import com.example.jsonparse.services.InsertDbService;
import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;
import com.example.jsonparse.room.FlickrDao;
import com.example.jsonparse.room.FlickrDatabase;

import java.util.List;

public class Repository {
    private static final String TAG = "Repository";
    private FlickrDao flickrDao;
    private DownloadService downloadService;

    public Repository(Application application) {
        FlickrDatabase database = FlickrDatabase.getInstance(application);
        flickrDao = database.flickrDao();
        downloadService = new DownloadService();
    }

    public void insert(Context context, Flickr flickr) {
        InsertDbService.enqueue(context, flickr);
    }

    public void update(Context context, Flickr flickr) {
        InsertDbService.enqueue(context, flickr);
    }

    public void delete(Context context, Flickr flickr) {
        InsertDbService.enqueue(context, flickr);
    }

    public LiveData<List<Item>> getAllFlickrEnt() {
        return flickrDao.getAllFlickrEnt();
    }

    public MutableLiveData<Boolean> getDownloadResult() {
        return downloadService.getmElapsedTime();
    }

}
