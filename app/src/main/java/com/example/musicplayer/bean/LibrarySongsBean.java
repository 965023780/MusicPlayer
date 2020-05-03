package com.example.musicplayer.bean;

import org.json.JSONObject;

import java.util.List;

public class LibrarySongsBean {
    public List<JSONObject> playlist;

    public void setPlaylist(List<JSONObject> playlist) {
        this.playlist = playlist;
    }

    public List<JSONObject> getPlaylist() {
        return playlist;
    }

}
