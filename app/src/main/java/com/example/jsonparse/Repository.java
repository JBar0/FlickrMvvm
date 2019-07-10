package com.example.jsonparse;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.network.JsonHolderApi;
import com.example.jsonparse.room.FlickrDao;
import com.example.jsonparse.room.FlickrDatabase;
import com.example.jsonparse.room.FlickrEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String TAG = "Repository";
    public static final String BASE_URL = "https://www.flickr.com/";
    private LiveData<List<FlickrEntity>> allFlickrEnt;
    private FlickrDao flickrDao;

    public Repository(Application application) {
        FlickrDatabase database = FlickrDatabase.getInstance(application);
        flickrDao = database.flickrDao();
        allFlickrEnt = flickrDao.getAllFlickrEnt();
    }

    public void insert(FlickrEntity flickrEntity) {
        new InsertFlickrEntAsyncTask(flickrDao).execute(flickrEntity);
    }

    public void update(FlickrEntity flickrEntity) {
        new UpdateFlickrEntAsyncTask(flickrDao).execute(flickrEntity);
    }

    public void delete(FlickrEntity flickrEntity) {
        new DeleteFlickrEntAsyncTask(flickrDao).execute(flickrEntity);
    }

    public LiveData<List<FlickrEntity>> getAllFlickrEnt() {
        return allFlickrEnt;
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static JsonHolderApi getJsonHolderApi() {
        return getRetrofitInstance().create(JsonHolderApi.class);
    }


    public LiveData<Flickr> getFlickr() {
        final MutableLiveData<Flickr> mutableLiveData = new MutableLiveData<>();
        JsonHolderApi jsonHolderApi = getJsonHolderApi();
        jsonHolderApi.getFlickrRec("json",1).enqueue(new Callback<Flickr>() {
            @Override
            public void onResponse(Call<Flickr> call, Response<Flickr> response) {
                Flickr flickr = response.body();
                mutableLiveData.setValue(flickr);
            }

            @Override
            public void onFailure(Call<Flickr> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }


    private static class InsertFlickrEntAsyncTask extends AsyncTask<FlickrEntity, Void, Void> {
        private FlickrDao flickrDao;

        private InsertFlickrEntAsyncTask(FlickrDao flickrDao) {
            this.flickrDao = flickrDao;
        }


        @Override
        protected Void doInBackground(FlickrEntity... flickrEntities) {
            flickrDao.insert(flickrEntities[0]);
            return null;
        }
    }

    private static class UpdateFlickrEntAsyncTask extends AsyncTask<FlickrEntity, Void, Void> {
        private FlickrDao flickrDao;

        private UpdateFlickrEntAsyncTask(FlickrDao flickrDao) {
            this.flickrDao = flickrDao;
        }

        @Override
        protected Void doInBackground(FlickrEntity... flickrEntities) {
            flickrDao.update(flickrEntities[0]);
            return null;
        }
    }

    private static class DeleteFlickrEntAsyncTask extends AsyncTask<FlickrEntity, Void, Void> {
        private FlickrDao flickrDao;

        private DeleteFlickrEntAsyncTask(FlickrDao flickrDao) {
            this.flickrDao = flickrDao;
        }

        @Override
        protected Void doInBackground(FlickrEntity... flickrEntities) {
            flickrDao.delete(flickrEntities[0]);
            return null;
        }
    }
}
