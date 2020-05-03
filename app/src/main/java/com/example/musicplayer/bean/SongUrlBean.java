package com.example.musicplayer.bean;

import android.util.JsonReader;

import org.json.JSONObject;

import java.util.List;

public class SongUrlBean {
    List<JSONObject> data;

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }
}
