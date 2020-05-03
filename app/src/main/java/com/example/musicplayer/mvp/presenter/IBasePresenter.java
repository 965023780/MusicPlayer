package com.example.musicplayer.mvp.presenter;

import android.app.Activity;
import android.content.Context;

public interface IBasePresenter {
    void createModel(Activity activity);
    void sendRequestToModel(Context context);
    void dealData();
}
