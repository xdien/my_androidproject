package com.crazy.xdien.suaanh.imageCache;

import android.graphics.Bitmap;

/**
 * Created by xdien on 9/22/14.
 */
public interface ImageCache {
    public Bitmap getBitmap(String url);
    public void putBitmap(String url, Bitmap bitmap);
}
