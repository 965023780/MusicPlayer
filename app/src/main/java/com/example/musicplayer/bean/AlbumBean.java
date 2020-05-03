package com.example.musicplayer.bean;

public class AlbumBean {
    public long id;
    public String picUrl;
    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPicUrl() {
        return picUrl;
    }


}
