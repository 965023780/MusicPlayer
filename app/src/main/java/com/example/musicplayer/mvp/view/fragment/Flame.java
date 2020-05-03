package com.example.musicplayer.mvp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer.R;
import com.example.musicplayer.mvp.view.adapter.AlbumRecyclerAdapter;
import com.example.musicplayer.mvp.view.IBaseView;
import com.example.musicplayer.mvp.view.adapter.RecommendationRecyclerAdapter;

public class Flame extends Fragment implements IBaseView {
    private View view;
    private RecyclerView recyclerViewAlbum;
    private RecyclerView recyclerViewRecommendation;
    ViewPager viewPager;
    RecommendationRecyclerAdapter recommendationRecyclerAdapter;
    AlbumRecyclerAdapter alubmRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_flame, container, false);
        recyclerViewAlbum = view.findViewById(R.id.home_flame_rc_album);
        recyclerViewRecommendation = view.findViewById(R.id.home_flame_rc_recommendation);
        init();
        return view;
    }

    void init() {

        final MyHandler handler = new MyHandler();
        Message message = handler.obtainMessage();
        new Thread() {
            @Override
            public void run() {
                alubmRecyclerAdapter = new AlbumRecyclerAdapter(getContext(), getActivity(),viewPager);
                int i = 1;
                while (i < 10) {
                    i++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = Message.obtain();
                msg.what = 1001;
                msg.arg1 = i;
                handler.sendMessage(msg);
            }
        }.start();

        new Thread() {


            @Override
            public void run() {
                recommendationRecyclerAdapter = new RecommendationRecyclerAdapter(getContext(), getActivity());
                int i = 1;
                while (i < 10) {
                    i++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = Message.obtain();
                msg.what = 1002;
                msg.arg1 = i;
                handler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void showProcess() {

    }

    @Override
    public void getData() {

    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1001:
                    recyclerViewAlbum.setAdapter(alubmRecyclerAdapter);
                    recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    break;
                case 1002:
                    recyclerViewRecommendation.setAdapter(recommendationRecyclerAdapter);
                    recyclerViewRecommendation.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    break;
            }
        }
    }

}
