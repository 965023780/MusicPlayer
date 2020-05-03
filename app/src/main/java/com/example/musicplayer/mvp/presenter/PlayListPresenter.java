package com.example.musicplayer.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.musicplayer.bean.SongBean;
import com.example.musicplayer.mvp.model.PlayListModel;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayListPresenter implements IBasePresenter {
    PlayListModel playListModel;

    public SongBean getBean(int position) {
        JSONObject jsonObject=playListModel.getBean(position);
        if(jsonObject==null){
            return null;
        }
        try{
            return new Json().fromJson(jsonObject.toString(),SongBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getSongUrlFromModel(long id,Context context){

        return playListModel.getSongUrl(id,context);
    }

    @Override
    public void createModel(Activity activity) {
        playListModel =new PlayListModel(activity);
    }

    @Override
    public void sendRequestToModel(Context context) {
        playListModel.setAddress("/playlist/detail");
        playListModel.sendRequestToServer(context);
    }

    @Override
    public void dealData() {

    }
}
