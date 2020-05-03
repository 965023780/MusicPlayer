package com.example.musicplayer.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.musicplayer.bean.AlbumBean;
import com.example.musicplayer.bean.RecommendationBean;
import com.example.musicplayer.bean.RecommendationsBean;
import com.example.musicplayer.mvp.model.FlameAlbumModel;
import com.example.musicplayer.mvp.model.FlameRecommendationModel;
import com.example.musicplayer.utils.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

public class FlameRecommendationPresenter implements IBasePresenter {
    FlameRecommendationModel flameRecommendationModel;

    public RecommendationBean getBean(int position) {
        JSONObject jsonObject=flameRecommendationModel.getBean(position);
        try {
            return new Json().fromJson(jsonObject.toString(),RecommendationBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createModel(Activity activity) {
        flameRecommendationModel= new FlameRecommendationModel(activity);
    }

    @Override
    public void sendRequestToModel(Context context) {
        flameRecommendationModel.setAddress("/top/playlist");
        flameRecommendationModel.sendRequestToServer(context);
    }

    @Override
    public void dealData() {

    }
}
