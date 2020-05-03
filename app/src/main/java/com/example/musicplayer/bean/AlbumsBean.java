package com.example.musicplayer.bean;

import org.json.JSONObject;

import java.util.List;

public class AlbumsBean {
    public List<JSONObject> albums;

    public List<JSONObject> getAlbums() {
        return albums;
    }

    public void setAlbums(List<JSONObject> albums) {
        this.albums = albums;
    }
}
