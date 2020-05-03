package com.example.musicplayer.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.musicplayer.bean.ProfileBean;
import com.example.musicplayer.utils.http.HttpUtil;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountModel implements IBaseModel {
    Json json=new Json();
    HttpUtil httpUtil=new HttpUtil();
    ProfileBean profileBean;
    int uid;
    String method;
    String address;

    public AccountModel(Activity activity){
        SharedPreferences dataBase = activity.getSharedPreferences("data", Activity.MODE_PRIVATE);
        uid=dataBase.getInt("uid",1348032807);
    }

    public ProfileBean getProfileBean(){
        return profileBean;
    }

    @Override
    public void sendRequestToServer(Context context) {
        httpUtil.setHttpUtil(address,context);
        String result=httpUtil.sendGet("uid="+uid);
        try {
            profileBean=(ProfileBean)json.fromJson(new JSONObject(result).getString("profile").toString(),ProfileBean.class);
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
