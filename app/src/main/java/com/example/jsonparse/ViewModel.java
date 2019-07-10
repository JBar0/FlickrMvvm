package com.example.jsonparse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jsonparse.models.Flickr;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Flickr> liveFlickr;

    public ViewModel(@NonNull Application application) {
        super(application);
        new Repository(getApplication()).getFlickr();
        this.liveFlickr = getLiveFlickr();
        this.repository = new Repository(application);
    }

    public LiveData<Flickr> getLiveFlickr() {
        return repository.getAllFlickrEnt();
    }

    public void insert(Flickr flickrEntity) {
        repository.insert(flickrEntity);
    }

    public void update(Flickr flickrEntity) {
        repository.delete(flickrEntity);
    }

    public void delete(Flickr flickrEntity) {
        repository.delete(flickrEntity);
    }

}
