package com.example.jsonparse.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Flickr {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    @SerializedName("title")
    @Expose
    private String title;

    @Ignore
    @SerializedName("link")
    @Expose
    private String link;

    @Ignore
    @SerializedName("description")
    @Expose
    private String description;

    @Ignore
    @SerializedName("modified")
    @Expose
    private String modified;

    @Ignore
    @SerializedName("generator")
    @Expose
    private String generator;

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Flickr{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", modified='" + modified + '\'' +
                ", generator='" + generator + '\'' +
                ", items=" + items +
                '}';
    }
}
