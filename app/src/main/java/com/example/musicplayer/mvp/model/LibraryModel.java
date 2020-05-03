package com.example.musicplayer.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.musicplayer.bean.LibrarySongsBean;
import com.example.musicplayer.bean.RecommendationsBean;
import com.example.musicplayer.utils.http.HttpUtil;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

public class LibraryModel implements IBaseModel {
    Json json=new Json();
    HttpUtil httpUtil=new HttpUtil();
    LibrarySongsBean librarySongsBean;
    int uid;
    String method;
    String address;
    int limit=20;

    public LibraryModel(Activity activity){
        SharedPreferences dataBase = activity.getSharedPreferences("data", Activity.MODE_PRIVATE);
        uid=dataBase.getInt("uid",1348032807);
    }

    public JSONObject getBean(int position) {
        if(librarySongsBean.getPlaylist().size()<=position){
            return null;
        }
        return librarySongsBean.getPlaylist().get(position);
    }

    @Override
    public void sendRequestToServer(Context context) {
        httpUtil.setHttpUtil(address,context);
        String result=httpUtil.sendGet("uid="+uid+"&limit="+limit);
        try {
            librarySongsBean =json.fromJson(result,LibrarySongsBean.class);
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

