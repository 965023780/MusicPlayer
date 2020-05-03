package com.example.musicplayer.utils.imageLoader;

import android.graphics.Bitmap;

public class Cache implements ImageCache {
    CacheInMemory cacheInMemory = new CacheInMemory();
    CacheInSD cacheInSD = new CacheInSD();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = cacheInMemory.get(url);
        if (bitmap == null) {
            bitmap = cacheInSD.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bmp) {
        cacheInMemory.put(url, bmp);
        cacheInSD.put(url, bmp);
    }
}
