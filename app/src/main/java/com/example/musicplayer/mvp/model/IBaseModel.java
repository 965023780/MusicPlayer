package com.example.musicplayer.mvp.model;

import android.content.Context;

public interface IBaseModel {
    void sendRequestToServer(Context context);
    void cancelRequest();
    void setRequestMethod(String method);
    void setAddress(String address);
}
