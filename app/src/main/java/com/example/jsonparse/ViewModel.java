package com.example.jsonparse;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.models.Item;
import com.example.jsonparse.room.FlickrDatabase;
import com.example.jsonparse.services.DownloadService;
import com.example.jsonparse.services.ResReceiver;

import java.util.List;


public class ViewModel extends AndroidViewModel {
    private LiveData<List<Item>> liveFlickr = FlickrDatabase.getInstance(getApplication()).flickrDao().getAllFlickrEnt();

    private MutableLiveData<Boolean> isLoading;

    private ResReceiver.Receiver receiver = new ResReceiver.Receiver() {
        @Override
        public void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData != null) {
                switch (resultCode) {
                    case ResReceiver.IDLE:
                        isLoading.postValue(false);
                        break;
                    case ResReceiver.RUNNING:
                        isLoading.postValue(true);
                        break;
                    case ResReceiver.ERROR:
                        isLoading.postValue(false);
                        Log.d("ERROR", "onReceiveResult: " + resultData.getString(DownloadService.SEND_TEXT));
                        break;
                }
            }
        }
    };

    public ViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        DownloadService.enqueue(getApplication(), new ResReceiver(receiver));
    }

    public LiveData<List<Item>> getLiveFlickr() {
        return liveFlickr;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoading == null) {
            isLoading = new MutableLiveData<>();
            isLoading.postValue(false);
        }
        return isLoading;
    }
}
