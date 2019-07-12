package com.example.jsonparse.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;

import java.util.List;

@Dao
public interface FlickrDao {

    @Insert
    void insert(Item item);

    @Insert
    void insert(List<Item> items);

    @Update
    void update(List<Item> items);

    @Delete
    void delete(List<Item> items);

    @Query("SELECT * FROM Item ORDER BY id DESC")
    LiveData<List<Item>> getAllFlickrEnt();
}
