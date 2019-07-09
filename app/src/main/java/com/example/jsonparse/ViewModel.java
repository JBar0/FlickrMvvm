package com.example.jsonparse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jsonparse.models.Flickr;

public class ViewModel extends AndroidViewModel {
    private LiveData<Flickr> liveFlickr;

    public ViewModel(@NonNull Application application) {
        super(application);
        this.liveFlickr = getLiveFlickr();
    }

    public LiveData<Flickr> getLiveFlickr() {
        return new Repository().getFlickr();
    }
}
