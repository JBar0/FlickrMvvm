package com.example.jsonparse.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jsonparse.models.Flickr;

@Dao
public interface FlickrDao {

    @Insert
    long insert(Flickr flickr);

    @Update
    void update(Flickr flickr);

    @Delete
    void delete(Flickr flickr);

    @Query("SELECT * FROM Flickr")
    LiveData<Flickr> getAllFlickrEnt();
}
