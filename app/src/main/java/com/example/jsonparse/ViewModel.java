package com.example.jsonparse;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.services.DownloadService;
import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;
import com.example.jsonparse.services.ResReceiver;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Item>> liveFlickr;

    MutableLiveData<Boolean> isLoading;

    public ViewModel(@NonNull Application application, ResReceiver receiver) {
        super(application);
        this.repository = new Repository(application);
        this.liveFlickr = getLiveFlickr();
        refresh(application.getApplicationContext(), receiver);
    }

    public void refresh(Context context, ResReceiver receiver){
        DownloadService.enqueue(context, receiver);
    }

    public LiveData<List<Item>> getLiveFlickr() {
        return repository.getAllFlickrEnt();
    }

    public MutableLiveData<Boolean> getDownloadResult() {
        return repository.getDownloadResult();
    }

    public void insert(Context context, Flickr flickr) {
        repository.insert(context, flickr);
    }

    public void update(Context context, Flickr flickr) {
        repository.update(context, flickr);
    }

    public void delete(Context context, Flickr flickr) {
        repository.delete(context, flickr);
    }

}
