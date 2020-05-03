package com.example.musicplayer.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.musicplayer.bean.RecommendationBean;
import com.example.musicplayer.mvp.model.FlameRecommendationModel;
import com.example.musicplayer.mvp.model.LibraryModel;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

public class LibraryPresenter implements IBasePresenter {
   LibraryModel libraryModel;

    public RecommendationBean getBean(int position) {
        JSONObject jsonObject=libraryModel.getBean(position);
        if(jsonObject==null){
            return null;
        }
        try {
            return new Json().fromJson(jsonObject.toString(),RecommendationBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createModel(Activity activity) {
        libraryModel= new LibraryModel(activity);
    }

    @Override
    public void sendRequestToModel(Context context) {
        libraryModel.setAddress("/user/playlist");
        libraryModel.sendRequestToServer(context);
    }

    @Override
    public void dealData() {

    }
}
