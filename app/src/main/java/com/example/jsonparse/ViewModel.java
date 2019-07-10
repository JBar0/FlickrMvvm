package com.example.jsonparse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.models.Flickr;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Flickr> liveFlickr;

    MutableLiveData<Boolean> isLoading;

    public ViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.liveFlickr = getLiveFlickr();
        refresh();
    }

    public void refresh(){
        this.repository.getFlickr();
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
