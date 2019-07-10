package com.example.jsonparse.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface FlickrDao {

    @Insert
    void insert(FlickrEntity flickrEntity);

    @Update
    void update(FlickrEntity flickrEntity);

    @Delete
    void delete(FlickrEntity flickrEntity);

    @Query("SELECT * FROM flickr_table")
    LiveData<List<FlickrEntity>> getAllFlickrEnt();


}
