package com.example.musicplayer.mvp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.musicplayer.R;
import com.example.musicplayer.bean.ProfileBean;
import com.example.musicplayer.mvp.presenter.AccountPresenter;
import com.example.musicplayer.mvp.view.IBaseView;
import com.example.musicplayer.utils.imageLoader.ImageLoader;

public class Account extends Fragment implements IBaseView {
    private View view;
    private TextView followers;
    private TextView posts;
    private TextView following;
    private AppCompatTextView pin;
    private AppCompatImageView background;
    private AccountPresenter accountPresenter;
    private ImageLoader imageLoader;
    private AppCompatTextView nickName;

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1000) {
                ProfileBean profileBean = accountPresenter.getBean();
                imageLoader.loadImage(profileBean.getBackgroundUrl(), background);
                pin.setText(profileBean.getCity() + "");
                followers.setText(profileBean.getFolloweds() + "");
                following.setText(profileBean.getFollows() + "");
                posts.setText(profileBean.getEventCount() + "");
                nickName.setText(profileBean.getNickname() + "");
            }
        }
    }

    void init() {
        accountPresenter = new AccountPresenter();
        accountPresenter.createModel(getActivity());
        imageLoader = new ImageLoader();
        followers = view.findViewById(R.id.home_account_followers);
        posts = view.findViewById(R.id.home_account_posts);
        following = view.findViewById(R.id.home_account_following);
        pin = view.findViewById(R.id.home_account_pin);
        background = view.findViewById(R.id.home_account_avator);
        nickName = view.findViewById(R.id.home_account_nickname);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_account, container, false);
        init();
        final MyHandler handler = new MyHandler();
        Message message = handler.obtainMessage();
        new Thread() {
            @Override
            public void run() {
                try {
                    accountPresenter.sendRequestToModel(getContext());
                    int i = 1;
                    while (i < 10) {
                        i++;
                        Thread.sleep(300);
                    }
                    Message msg = Message.obtain();
                    msg.what = 1000;
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return view;
    }


    @Override
    public void showProcess() {

    }

    @Override
    public void getData() {
        accountPresenter.getBean();
    }
}
