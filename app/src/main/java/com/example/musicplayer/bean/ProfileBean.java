package com.example.musicplayer.bean;

public class ProfileBean {
    public String nickname;
    public int city;
    public String backgroundUrl;
    public int followeds;
    public int follows;
    public int eventCount;

    public int getCity() {
        return city;
    }

    public int getFolloweds() {
        return followeds;
    }

    public int getFollows() {
        return follows;
    }

    public int getEventCount() {
        return eventCount;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public void setFolloweds(int followeds) {
        this.followeds = followeds;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

}
