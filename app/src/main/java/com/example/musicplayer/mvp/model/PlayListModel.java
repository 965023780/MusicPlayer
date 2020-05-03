package com.example.musicplayer.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.musicplayer.bean.ProfileBean;
import com.example.musicplayer.bean.SongBean;
import com.example.musicplayer.bean.SongUrlBean;
import com.example.musicplayer.bean.SongsBean;
import com.example.musicplayer.utils.http.HttpUtil;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.ContentValues.TAG;

public class PlayListModel implements IBaseModel {
    Json json=new Json();
    HttpUtil httpUtil=new HttpUtil();
    SongsBean songsBean;
    SongUrlBean songUrlBean;
    String id;
    String method;
    String address;

    public PlayListModel(Activity activity){
        SharedPreferences dataBase = activity.getSharedPreferences("data", Activity.MODE_PRIVATE);
        id=dataBase.getString("id","3100484976");
    }

    public JSONObject getBean(int position){
        if(position>=songsBean.getTracks().size()){
            return null;
        }
        return songsBean.getTracks().get(position);
    }

    public String getSongUrl(long id, Context context){
        httpUtil.setHttpUtil(address,context);
        String result=httpUtil.sendGet("id="+id+"&br=320000");
        try {
            songUrlBean =json.fromJson(result,SongUrlBean.class);
            return songUrlBean.getData().get(0).get("url").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendRequestToServer(Context context) {
        httpUtil.setHttpUtil(address,context);
        String result=httpUtil.sendGet("id="+id);
        try {
            Log.d(TAG, "sendRequestToServer: ");
            songsBean=json.fromJson(new JSONObject(result).getString("playlist").toString(),SongsBean.class);
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
