package com.crazy.xdien.suaanh.img_effects;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.crazy.xdien.suaanh.MyActivity;
import com.crazy.xdien.suaanh.R;

import org.opencv.android.NativeCameraView;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;

import static org.opencv.imgproc.Imgproc.GaussianBlur;

/**
 * Created by xdien on 9/22/14.
 */
public class Smoonthing_Ext extends MyActivity {
    private NativeCameraView mOpenCvCameraView;
    private Bitmap preBitmap;
    private ImageView iv;
    private SeekBar sk;
    private  Mat nguon,ra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smoothing_ext);
        iv = (ImageView) findViewById(R.id.thumdnail_smoothing);
        Intent intent = getIntent();
        sk = (SeekBar) findViewById(R.id.seekBar);
        Mat src = Highgui.imread(intent.getStringExtra("linkImg"));
        //nguon = new Mat();
        //lay bitmap tu cache neu chua co thi load tu linkImg
        preBitmap = imcahe.getBitmap(intent.getStringExtra("linkImg"));
        if(preBitmap == null)
        {

            preBitmap = Bitmap.createBitmap(src.cols(), src.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(src,preBitmap);
            iv.setImageBitmap(preBitmap);
            nguon = src;
        }
        else {
            Utils.bitmapToMat(preBitmap,nguon);
            iv.setImageBitmap(preBitmap);
        }
        //neu co cache thi load tu cache neu khong
        //DisplayImg(src,nguon, intent.getStringExtra("linkImg"),iv);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                try {
                    ra = nguon.clone();
                    img_smoothing(nguon, ra, (Integer) progress);

                    preBitmap = Bitmap.createBitmap(ra.cols(), ra.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(ra, preBitmap);
                    iv.setImageBitmap(preBitmap);
                }
                catch (Exception e)
                {
                    Toast.makeText(Smoonthing_Ext.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void img_smoothing(Mat src,Mat dst,int MAX_KERNEL_LENGTH)
    {
        for ( int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2 )
        {
            GaussianBlur( src, dst, new Size( i, i ), 0, 0 );
            //medianBlur ( src, dst, i );
        }
    }
}
