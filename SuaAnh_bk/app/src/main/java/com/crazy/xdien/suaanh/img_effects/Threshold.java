package com.crazy.xdien.suaanh.img_effects;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.crazy.xdien.suaanh.MyActivity;
import com.crazy.xdien.suaanh.R;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import static org.opencv.imgproc.Imgproc.cvtColor;


/**
 * Created by xdien on 9/22/14.
 */
public class Threshold extends MyActivity{
    private Bitmap preBitmap;
    private  Mat nguon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thesrhold);
        Intent intent = getIntent();
        ImageView iv = (ImageView) findViewById(R.id.thesrhold);
        //DisplayImg(nguon,intent.getStringExtra("linkImg"),iv);
    }
    private void img_Threshold(Mat src, Mat dst_gray)
    {
        cvtColor(src, dst_gray,7/*CV_RGB2GRAY*/);
    }

}

