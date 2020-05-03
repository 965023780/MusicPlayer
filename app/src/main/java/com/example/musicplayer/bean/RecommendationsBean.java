package com.example.musicplayer.bean;

import org.json.JSONObject;

import java.util.List;

public class RecommendationsBean {
    public List<JSONObject> playlists;

    public void setPlaylists(List<JSONObject> playlists) {
        this.playlists = playlists;
    }

    public List<JSONObject> getPlaylists() {
        return playlists;
    }

}
