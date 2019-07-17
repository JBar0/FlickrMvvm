package com.example.jsonparse.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Flickr implements Parcelable {


    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("modified")
    @Expose
    private String modified;

    @SerializedName("generator")
    @Expose
    private String generator;

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    protected Flickr(Parcel in) {
        title = in.readString();
        link = in.readString();
        description = in.readString();
        modified = in.readString();
        generator = in.readString();
        items = new ArrayList<>();
        in.readList(items, Item.class.getClassLoader());
    }

    public static final Creator<Flickr> CREATOR = new Creator<Flickr>() {
        @Override
        public Flickr createFromParcel(Parcel in) {
            return new Flickr(in);
        }

        @Override
        public Flickr[] newArray(int size) {
            return new Flickr[size];
        }
    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(description);
        dest.writeString(modified);
        dest.writeString(generator);
        dest.writeList(items);
    }
}
