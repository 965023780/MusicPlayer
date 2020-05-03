package com.example.musicplayer.bean;


import java.util.List;

public class RecommendationBean {
    public String name;
    public long id;
    public String coverImgUrl;
    public CreatorBean creator;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public String getId() {
        return id+"";
    }

    public String getName() {
        return name;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }
}
