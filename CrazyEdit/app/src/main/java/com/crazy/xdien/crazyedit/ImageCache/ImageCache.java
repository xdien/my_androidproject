package com.crazy.xdien.crazyedit.ImageCache;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by xdien on 10/22/14.
 */
public interface ImageCache {
    public Bitmap getBitmap(String url);
    public void putBitmap(String url, Bitmap bitmap);
    public void DisplayImage(ImageView iv, String id);
}
