package com.example.jsonparse.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flickr_table")
public class FlickrEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String link;

    public FlickrEntity(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
