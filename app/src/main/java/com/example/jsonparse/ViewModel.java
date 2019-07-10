package com.example.jsonparse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.room.FlickrEntity;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<FlickrEntity>> allFlickrEnt;
    private LiveData<Flickr> liveFlickr;

    public ViewModel(@NonNull Application application) {
        super(application);
        this.liveFlickr = getLiveFlickr();
        this.repository = new Repository(application);
        this.allFlickrEnt = repository.getAllFlickrEnt();
    }

    public LiveData<Flickr> getLiveFlickr() {
        return new Repository(getApplication()).getFlickr();
    }

    public void insert(FlickrEntity flickrEntity) {
        repository.insert(flickrEntity);
    }

    public void update(FlickrEntity flickrEntity) {
        repository.delete(flickrEntity);
    }

    public void delete(FlickrEntity flickrEntity) {
        repository.delete(flickrEntity);
    }

    public LiveData<List<FlickrEntity>> getAllNotes() {
        return allFlickrEnt;
    }
}
