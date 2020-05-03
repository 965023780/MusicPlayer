package com.example.musicplayer.mvp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.mvp.view.IBaseView;
import com.example.musicplayer.mvp.view.adapter.PlayListRecyclerAdapter;
import com.example.musicplayer.utils.imageLoader.ImageLoader;

public class PlayList extends AppCompatActivity implements IBaseView {
    private ImageLoader imageLoader = new ImageLoader();
    private RecyclerView recyclerView;
    private AppCompatImageView background;
    private AppCompatTextView name;
    private AppCompatTextView singer;
    private AppCompatImageView back;
    PlayListRecyclerAdapter playListRecyclerAdapter;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        init();
        final MyHandler handler = new MyHandler();
        new Thread() {


            @Override
            public void run() {
                playListRecyclerAdapter = new PlayListRecyclerAdapter(PlayList.this, PlayList.this);
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
                msg.what = 1003;
                msg.arg1 = i;
                handler.sendMessage(msg);
            }
        }.start();

        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlayList.this,Home.class);
                startActivity(intent);
            }
        });
    }


    void init() {
        back=findViewById(R.id.playlist_back);
        background = findViewById(R.id.playlist_pic);
        name = findViewById(R.id.playlist_name);
        singer = findViewById(R.id.playlist_singer);
        recyclerView = findViewById(R.id.playlist_rc);
        SharedPreferences sp = getSharedPreferences("data", Activity.MODE_PRIVATE);
        name.setText(sp.getString("name", ""));
        singer.setText(sp.getString("singer", ""));
        String url = sp.getString("url", null);
        if (url != null) {
            imageLoader.loadImage(url, background);
        }
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
                case 1003:
                    recyclerView.setAdapter(playListRecyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PlayList.this, LinearLayoutManager.VERTICAL,false));
            }
        }
    }
}
