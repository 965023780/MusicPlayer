package com.example.musicplayer.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.musicplayer.bean.AlbumBean;
import com.example.musicplayer.bean.AlbumsBean;
import com.example.musicplayer.bean.ProfileBean;
import com.example.musicplayer.utils.http.HttpUtil;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FlameAlbumModel implements IBaseModel {
    Json json=new Json();
    HttpUtil httpUtil=new HttpUtil();
    AlbumsBean albumsBean;
    int uid;
    String method;
    String address;

    public FlameAlbumModel(Activity activity){
        SharedPreferences dataBase = activity.getSharedPreferences("data", Activity.MODE_PRIVATE);
        uid=dataBase.getInt("uid",1348032807);
    }

    public JSONObject getBean(int position) {
        return albumsBean.getAlbums().get(position);
    }

    @Override
    public void sendRequestToServer(Context context) {
        httpUtil.setHttpUtil(address,context);
        String result=httpUtil.sendGet("");
        try {
            albumsBean =json.fromJson(result,AlbumsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelRequest() {

    }

    @Override
    public void setRequestMethod(String method) {
        this.method=method;
    }

    @Override
    public void setAddress(String address) {
        this.address=address;
    }
}

