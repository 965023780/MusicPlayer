package com.example.musicplayer.bean;

public class SongBean {

    public String name;
    public int fee;
    public long id;
    public long dt;

    public String getName() {
        return name;
    }

    public int getFee() {
        return fee;
    }

    public long getDt() {
        return dt;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public void setFee(int fee) {
        this.fee = fee;

    }
}
