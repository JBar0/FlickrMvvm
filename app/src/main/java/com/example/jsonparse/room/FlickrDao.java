package com.example.jsonparse.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jsonparse.models.Flickr;

import java.util.List;

@androidx.room.Dao
public interface FlickrDao {

    @Insert
    void insert(Flickr flickr);

    @Update
    void update(Flickr flickr);

    @Delete
    void delete(Flickr flickr);

    @Query("SELECT * FROM flickr_table")
    LiveData<Flickr> getAllFlickrEnt();


}
