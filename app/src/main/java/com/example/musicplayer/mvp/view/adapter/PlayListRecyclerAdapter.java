package com.example.musicplayer.mvp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.bean.SongBean;
import com.example.musicplayer.mvp.presenter.PlayListPresenter;
import com.example.musicplayer.mvp.view.IBaseView;

import java.util.List;


public class PlayListRecyclerAdapter extends RecyclerView.Adapter<PlayListRecyclerAdapter.MyInnerViewHolder> implements IBaseView {
    PlayListPresenter playListPresenter = new PlayListPresenter();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private AudioManager audioManager;
    Context context;

    public PlayListRecyclerAdapter(final Context context, Activity activity) {
        playListPresenter.createModel(activity);
        playListPresenter.sendRequestToModel(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PlayListRecyclerAdapter.MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false);
        return new MyInnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayListRecyclerAdapter.MyInnerViewHolder holder, int position) {
        SongBean songBean = playListPresenter.getBean(position);
        if (songBean == null) {
            return;
        }
        holder.name.setText(playListPresenter.getBean(position).getName());
        long seconds = playListPresenter.getBean(position).getDt() / 1000;
        holder.time.setText(seconds / 60 + ":" + seconds % 60);
        holder.songId = playListPresenter.getBean(position).getId();
        holder.fee = playListPresenter.getBean(position).getFee();
        holder.id.setText((position + 1) + "");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void showProcess() {

    }

    @Override
    public void getData() {

    }

    public class MyInnerViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView name;
        private AppCompatTextView time;
        private AppCompatTextView id;
        private long songId;
        private int fee;
        String url = null;
        boolean flag = false;
        MyHandler handler = new MyHandler();
        Message message = handler.obtainMessage();

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.playlist_name);
            time = itemView.findViewById(R.id.playlist_time);
            id = itemView.findViewById(R.id.playlist_id);
            /*itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            url=playListPresenter.getSongUrlFromModel(songId,context);
                            int i = 1;
                            while (i < 10) {
                                i++;
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (url == null) {
                                return;
                            }
                            Message msg = Message.obtain();
                            if (flag) {
                                msg.what = 1007;
                            } else {
                                msg.what = 1006;
                            }
                            msg.arg1 = i;
                            handler.sendMessage(msg);
                        }
                    }.start();
                    try {
                        mediaPlayer.setDataSource(url);
                        mediaPlayer.prepare();
                        mediaPlayer.setLooping(true);
                        mediaPlayer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });*/
        }

        private class MyHandler extends Handler {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1006:
                        mediaPlayer.start();
                    case 1007:
                        mediaPlayer.stop();
                }
            }
        }
    }
}



