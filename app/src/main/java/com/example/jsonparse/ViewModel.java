package com.example.jsonparse;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Item>> liveFlickr;

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

    public LiveData<List<Item>> getLiveFlickr() {
        return repository.getAllFlickrEnt();
    }

    public void insert(List<Item> items) {
        repository.insert(items);
    }

    public void update(List<Item> items) {
        repository.delete(items);
    }

    public void delete(List<Item> items) {
        repository.delete(items);
    }

}
