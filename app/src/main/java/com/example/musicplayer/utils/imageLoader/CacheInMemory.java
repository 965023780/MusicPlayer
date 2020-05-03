package com.example.musicplayer.utils.imageLoader;

import android.graphics.Bitmap;
import android.util.LruCache;

public class CacheInMemory implements ImageCache {
    private LruCache<String, Bitmap> imageCache;

    public CacheInMemory() {
        init();
    }

    private void init() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        imageCache = new LruCache<String, Bitmap>(cacheSize) {
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        imageCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return imageCache.get(url);
    }
}
