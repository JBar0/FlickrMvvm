package com.example.jsonparse;

import android.app.Application;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;
import com.example.jsonparse.network.JsonHolderApi;
import com.example.jsonparse.room.FlickrDao;
import com.example.jsonparse.room.FlickrDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String TAG = "Repository";
    public static final String BASE_URL = "https://www.flickr.com/";
//    private LiveData<List<FlickrEntity>> allFlickrEnt;
    private FlickrDao flickrDao;

    public Repository(Application application) {
        FlickrDatabase database = FlickrDatabase.getInstance(application);
        flickrDao = database.flickrDao();
//        allFlickrEnt = flickrDao.getAllFlickrEnt();
    }

    public void insert(List<Item> items) {
        new InsertFlickrEntAsyncTask(flickrDao).execute(items);
    }

    public void update(List<Item> items) {
        new UpdateFlickrEntAsyncTask(flickrDao).execute(items);
    }

    public void delete(List<Item> items) {
        new DeleteFlickrEntAsyncTask(flickrDao).execute(items);
    }

    public LiveData<List<Item>> getAllFlickrEnt() {
        return flickrDao.getAllFlickrEnt();
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static JsonHolderApi getJsonHolderApi() {
        return getRetrofitInstance().create(JsonHolderApi.class);
    }


    public void getFlickr() {
        JsonHolderApi jsonHolderApi = getJsonHolderApi();
        jsonHolderApi.getFlickrRec("json",1).enqueue(new Callback<Flickr>() {
            @Override
            public void onResponse(Call<Flickr> call, Response<Flickr> response) {
                if (response.isSuccessful()) {
                    Flickr flickr = response.body();
                    insert(flickr.getItems());
                } else {
                    Log.e(TAG, "onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Flickr> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    private static class InsertFlickrEntAsyncTask extends AsyncTask<List<Item>, Void, Void> {
        private FlickrDao flickrDao;

        private InsertFlickrEntAsyncTask(FlickrDao flickrDao) {
            this.flickrDao = flickrDao;
        }

        @Override
        protected Void doInBackground(List<Item>... lists) {
            flickrDao.insert(lists[0]);
            return null;
        }
    }

    private static class UpdateFlickrEntAsyncTask extends AsyncTask<List<Item>, Void, Void> {
        private FlickrDao flickrDao;

        private UpdateFlickrEntAsyncTask(FlickrDao flickrDao) {
            this.flickrDao = flickrDao;
        }

        @Override
        protected Void doInBackground(List<Item>... lists) {
            flickrDao.insert(lists[0]);
            return null;
        }
    }

    private static class DeleteFlickrEntAsyncTask extends AsyncTask<List<Item>, Void, Void> {
        private FlickrDao flickrDao;

        private DeleteFlickrEntAsyncTask(FlickrDao flickrDao) {
            this.flickrDao = flickrDao;
        }

        @Override
        protected Void doInBackground(List<Item>... lists) {
            flickrDao.insert(lists[0]);
            return null;
        }
    }
}
