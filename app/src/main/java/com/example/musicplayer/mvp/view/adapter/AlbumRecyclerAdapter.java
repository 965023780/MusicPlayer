package com.example.musicplayer.mvp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer.R;
import com.example.musicplayer.bean.AlbumBean;
import com.example.musicplayer.mvp.presenter.FlameAlbumPresenter;
import com.example.musicplayer.mvp.view.IBaseView;
import com.example.musicplayer.mvp.view.activity.Home;
import com.example.musicplayer.utils.imageLoader.ImageLoader;


public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.MyInnerViewHolder> implements IBaseView {
    FlameAlbumPresenter flameAlbumPresenter = new FlameAlbumPresenter();
    ImageLoader imageLoader = new ImageLoader();
    ViewPager viewPager;

    public AlbumRecyclerAdapter(final Context context, Activity activity,ViewPager viewPager) {
        flameAlbumPresenter.createModel(activity);
        flameAlbumPresenter.sendRequestToModel(context);
        this.viewPager=viewPager;
    }

    @NonNull
    @Override
    public AlbumRecyclerAdapter.MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_flame_item_alubm, parent, false);
        return new MyInnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumRecyclerAdapter.MyInnerViewHolder holder, int position) {
        AlbumBean albumBean=flameAlbumPresenter.getBean(position);
        if (albumBean== null) {
            return;
        }
        imageLoader.loadImage(albumBean.getPicUrl(), holder.background);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void showProcess() {

    }

    @Override
    public void getData() {

    }

    public class MyInnerViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView background;

        public MyInnerViewHolder(final View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.home_flame_item_background);
        }

    }
}



