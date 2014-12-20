package com.crazy.xdien.crazyedit;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crazy.xdien.crazyedit.ImageCache.BitmapLruCache;
import com.crazy.xdien.crazyedit.tab_frag.TabPagerAdapter;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class MainActivity extends Activity {
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar actionBar;
    public static BitmapLruCache bmCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Khoi dong cac ham can thiet*/
        Log.w("lay tu jni",getStringFromNative());
        init();
       //TabAdapter = new TabPagerAdapter(this));
        Tab = (ViewPager)findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar = getActionBar();
                        actionBar.setSelectedNavigationItem(position);                    }
                });
        Tab.setAdapter(TabAdapter);
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){
            @Override
            public void onTabReselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Tab.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }};
        //Add New Tab
        actionBar.addTab(actionBar.newTab().setText("Home").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("iOS").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Windows").setTabListener(tabListener));
    }
    /*Ham khoi dong*/
    private void init()
    {
        bmCache = new BitmapLruCache();
        //main_openfileImage = (Button) findViewById(R.id.button_openImage);
        /*main_openfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFile();
            }
        });*/
        //main_ImageView = (ImageViewTouch) findViewById(R.id.main_imageviewTouch);
    }
    //Khai bao nhan dang plugin opencv
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            if (status == LoaderCallbackInterface.SUCCESS) {
                // now we can call opencv code !
                //oworld();
            } else {
                super.onManagerConnected(status);
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_5, this, mLoaderCallback);
        // you may be tempted, to do something here, but it's *async*, and may take some time,
        // so any opencv call here will lead to unresolved native errors.
    }
    private native String getStringFromNative();
    static {
        System.loadLibrary("mylibs");
    }
}