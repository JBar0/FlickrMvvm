package com.example.jsonparse.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jsonparse.models.Flickr;

@Database(entities = {Flickr.class}, version = 1)
public abstract class FlickrDatabase extends RoomDatabase {
    private static FlickrDatabase instance;

    public abstract FlickrDao flickrDao();

    public static synchronized FlickrDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FlickrDatabase.class, "flickr_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
