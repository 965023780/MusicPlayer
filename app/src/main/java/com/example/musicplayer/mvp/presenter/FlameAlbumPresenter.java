package com.example.musicplayer.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.musicplayer.bean.AlbumBean;
import com.example.musicplayer.bean.AlbumsBean;
import com.example.musicplayer.bean.ProfileBean;
import com.example.musicplayer.mvp.model.AccountModel;
import com.example.musicplayer.mvp.model.FlameAlbumModel;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

public class FlameAlbumPresenter implements IBasePresenter {
    FlameAlbumModel flameAlbumModel;

    public AlbumBean getBean(int position) {
        JSONObject jsonObject=flameAlbumModel.getBean(position);
        try {
            return new Json().fromJson(jsonObject.toString(),AlbumBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createModel(Activity activity) {
        flameAlbumModel = new FlameAlbumModel(activity);
    }

    @Override
    public void sendRequestToModel(Context context) {
        flameAlbumModel.setAddress("/album/newest");
        flameAlbumModel.sendRequestToServer(context);
    }

    @Override
    public void dealData() {

    }
}
