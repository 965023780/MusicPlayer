package com.example.musicplayer.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.musicplayer.bean.ProfileBean;
import com.example.musicplayer.mvp.model.AccountModel;

import java.util.logging.Handler;

public class AccountPresenter implements IBasePresenter {
    AccountModel accountModel;

    public ProfileBean getBean(){
        return accountModel.getProfileBean();
    }
    @Override
    public void createModel(Activity activity) {
        accountModel=new AccountModel(activity);
    }

    @Override
    public void sendRequestToModel(Context context) {
        accountModel.setAddress("/user/detail");
        accountModel.sendRequestToServer(context);
    }

    @Override
    public void dealData() {

    }
}
