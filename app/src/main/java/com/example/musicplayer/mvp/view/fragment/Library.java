package com.example.musicplayer.mvp.view.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer.R;
import com.example.musicplayer.mvp.view.IBaseView;
import com.example.musicplayer.mvp.view.adapter.LibraryRecyclerAdapter;
import com.example.musicplayer.mvp.view.adapter.RecommendationRecyclerAdapter;
import com.example.musicplayer.utils.imageLoader.ImageLoader;

public class Library extends Fragment implements IBaseView {
    private View view;
    private RecyclerView recyclerView;
    LibraryRecyclerAdapter libraryRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_library, container, false);
        recyclerView=view.findViewById(R.id.home_library_rc);
        final MyHandler handler=new MyHandler();
        new Thread() {

            @Override
            public void run() {
                libraryRecyclerAdapter= new LibraryRecyclerAdapter(getContext(), getActivity());
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
                msg.what = 1005;
                msg.arg1 = i;
                handler.sendMessage(msg);
            }
        }.start();
        return view;
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
                case 1005:
                    recyclerView.setAdapter(libraryRecyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }
        }
    }
}
