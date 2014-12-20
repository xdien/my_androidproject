package com.crazy.xdien.suaanh.img_effects;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import org.opencv.core.Point;
import org.opencv.core.Size;

import com.crazy.xdien.suaanh.MyActivity;

import static org.opencv.imgproc.Imgproc.GaussianBlur;
import static org.opencv.imgproc.Imgproc.bilateralFilter;
import static org.opencv.imgproc.Imgproc.blur;


/**
 * Created by xdien on 19/09/2014.
 */
public class Smoothing extends MyActivity {
    //doc anh tu link
    private Mat src,dst;
    /// Global Variables
    int MAX_KERNEL_LENGTH = 31;
    private Intent recive;
    private String path;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lay du lieu tu MyActivity
        recive = getIntent();
        Intent intens=new Intent();
        //Mat test=new Mat(200,200, CvType.CV_8UC1);
        //Core.putText(test, "hi there ;)", new Point(0, 80)/*diem bat dau*/, Core.FONT_HERSHEY_SCRIPT_SIMPLEX, 2.2, new Scalar(200, 250, 0)/*Mau RGB*/, 2);
        path = recive.getStringExtra("linkImg");
        img_processSmoothing(path);
        Bitmap bm = Bitmap.createBitmap(dst.cols(), dst.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(dst,bm);
        if(bm != null) {
            try {

                imcahe.putBitmap(path,bm);
            }
            catch (Exception e)
            {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
        setResult(RESULT_OK, intens);
        finish();
    }
    private int img_processSmoothing(String link)
    {
        src = Highgui.imread(link);
        dst = src.clone();

        /*for ( int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2 )
        {
            blur( src, dst, new Size( i, i ), new Point(-1,-1) );
        }*/


        for ( int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2 )
        {
            GaussianBlur( src, dst, new Size( i, i ), 0, 0 );
        }

        /*for ( int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2 )
        {
            medianBlur ( src, dst, i );
        }

        for ( int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2 )
        {
            bilateralFilter( src, dst, i, i*2, i/2 );
        }*/
        return 0;
    }
}