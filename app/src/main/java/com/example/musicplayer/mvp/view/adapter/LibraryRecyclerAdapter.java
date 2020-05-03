package com.example.musicplayer.mvp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.bean.RecommendationBean;
import com.example.musicplayer.mvp.presenter.FlameRecommendationPresenter;
import com.example.musicplayer.mvp.presenter.LibraryPresenter;
import com.example.musicplayer.mvp.view.IBaseView;
import com.example.musicplayer.mvp.view.activity.PlayList;
import com.example.musicplayer.utils.imageLoader.ImageLoader;


public class LibraryRecyclerAdapter extends RecyclerView.Adapter<LibraryRecyclerAdapter.MyInnerViewHolder> implements IBaseView {
    LibraryPresenter libraryPresenter=new LibraryPresenter();
    ImageLoader imageLoader = new ImageLoader();
    Activity activity;

    public LibraryRecyclerAdapter(final Context context, Activity activity) {
        libraryPresenter.createModel(activity);
        libraryPresenter.sendRequestToModel(context);
        this.activity=activity;
    }

    @NonNull
    @Override
    public LibraryRecyclerAdapter.MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_item, parent, false);
        return new MyInnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LibraryRecyclerAdapter.MyInnerViewHolder holder, int position) {
        RecommendationBean recommendationBean = libraryPresenter.getBean(position);
        if (recommendationBean == null) {
            return;
        }
        holder.pictureUrl=recommendationBean.coverImgUrl;
        imageLoader.loadImage(recommendationBean.getCoverImgUrl(), holder.background);
        holder.name.setText(recommendationBean.getName());
        holder.singer.setText(recommendationBean.getCreator().getNickname());
        holder.id=recommendationBean.getId();
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
        private AppCompatImageView background;
        private AppCompatTextView name;
        private AppCompatTextView singer;
        String pictureUrl;
        private String id;

        public MyInnerViewHolder(final View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.library_item_pic);
            name = itemView.findViewById(R.id.library_item_name);
            singer = itemView.findViewById(R.id.library_singer);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor=itemView.getContext().getSharedPreferences("data",Activity.MODE_PRIVATE).edit();
                    editor.putString("id",id);
                    editor.putString("url",pictureUrl);
                    editor.putString("name",name.getText()+"");
                    editor.putString("singer",singer.getText()+"");
                    editor.apply();
                    Intent intent=new Intent(activity,PlayList.class);
                    activity.startActivity(intent);
                }
            });
        }
    }
}



