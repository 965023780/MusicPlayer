package com.example.musicplayer.bean;

import org.json.JSONObject;

import java.util.List;

public class SongsBean {
    public List<JSONObject> tracks;

    public List<JSONObject> getTracks() {
        return tracks;
    }

    public void setTracks(List<JSONObject> songs) {
        this.tracks = songs;
    }

}
