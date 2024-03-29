package com.example.jsonparse.room;

import androidx.room.TypeConverter;

import com.example.jsonparse.models.Item;
import com.example.jsonparse.models.Media;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converter {

    private Type listArrayType = new TypeToken<List<Item>>() {}.getType();

    @TypeConverter
    public String fromItemsList(List<Item> list){
        if(list == null || list.isEmpty()){
            return null;
        }else {
            return new Gson().toJson(list);
        }
    }

    @TypeConverter
    public List<Item> toItemsList(String json){
        if(json == null){
            return Collections.emptyList();
        }else {
            return new Gson().fromJson(json, listArrayType);
        }
    }

    @TypeConverter
    public String fromMedia(Media media) {
        if (media == null) {
            return null;
        } else {
            return media.getM();
        }
    }

    @TypeConverter
    public Media toMedia(String m) {
        if (m == null) {
            return null;
        } else {
            return new Media(m);
        }
    }

}
