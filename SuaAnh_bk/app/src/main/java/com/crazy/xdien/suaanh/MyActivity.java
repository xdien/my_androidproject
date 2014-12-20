package com.crazy.xdien.suaanh;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

import  android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.crazy.xdien.suaanh.filebrowser.FileChooser;
import com.crazy.xdien.suaanh.img_effects.Smoothing;
import com.crazy.xdien.suaanh.imageCache.BitmapLruCache;
import com.crazy.xdien.suaanh.img_effects.Smoonthing_Ext;
import com.crazy.xdien.suaanh.img_effects.Threshold;
import com.crazy.xdien.suaanh.menuSliding.DetailFragment;
import com.crazy.xdien.suaanh.menuSliding.HomeFragment;
import com.crazy.xdien.suaanh.menuSliding.NavDrawerItem;
import com.crazy.xdien.suaanh.menuSliding.NavDrawerListAdapter;

import java.util.ArrayList;

public class MyActivity extends Activity {
    public static final int REQUEST_OPEN_FILE_CHOOSER = 100;
    public static final int REQUEST_CODE_IMG_PROCESS_SMOOTHING = 101;
    private ImageView imgView;
    public static BitmapLruCache imcahe =new BitmapLruCache();
    public String link_image;
    // used to store app title
    private CharSequence mTitle;
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    //private NavDrawerListAdapter adapter;
    // nav drawer title
    private CharSequence mDrawerTitle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //su dung fragment
        setContentView(R.layout.activity_my);

        //Intent myIntent=new Intent(MyActivity.this, FileChooser.class);
        //startActivity(myIntent);
        imgView = (ImageView) findViewById(R.id.imageView1);
        Button smoothing = (Button) findViewById(R.id.moA);
        smoothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProcessSoothing();
            }
        });
        Button ext_act =(Button) findViewById(R.id.ext_act);
        ext_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMyaxt();
            }
        });
        /*
        * MyPagerAdapter adapter = new MyPagerAdapter();
        ViewPager pager = (ViewPager) findViewById(R.id.instructionsViewPager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);

        menu = new String[]{"Home","Android","Windows","Linux","Raspberry Pi","WordPress","Videos","Contact Us"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);
        dList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                displayView(position);
                /*dLayout.closeDrawers();
                Bundle args = new Bundle();
                args.putString("Menu", menu[position]);
                Fragment detail = new DetailFragment();
                detail.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();*/
                //dLayout.closeDrawer(dList);
            //}
        //});

    }


    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            if (status == LoaderCallbackInterface.SUCCESS) {
                // now we can call opencv code !
                //helloworld();
            } else {
                super.onManagerConnected(status);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.open) {
            openFileChooser();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_5, this, mLoaderCallback);
        // you may be tempted, to do something here, but it's *async*, and may take some time,
        // so any opencv call here will lead to unresolved native errors.
    }

    public void helloworld() {//ham ve "hi there"
        // make a mat and draw something
        Mat m = Mat.zeros(50, 400,/*size khung anh*/ CvType.CV_8UC3);
        Core.putText(m, "hi there ;)", new Point(0, 80)/*diem bat dau*/, Core.FONT_HERSHEY_SCRIPT_SIMPLEX, 2.2, new Scalar(200, 250, 0)/*Mau RGB*/, 2);

        // convert to bitmap:
        Bitmap bm = Bitmap.createBitmap(m.cols(), m.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(m, bm);

        // find the imageview and draw it!
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        iv.setImageBitmap(bm);
    }

    public void openFileChooser()//filechooser
    {
        Intent myIntent = new Intent(MyActivity.this, FileChooser.class);
        startActivityForResult(myIntent, REQUEST_OPEN_FILE_CHOOSER);
    }

    public void openProcessSoothing() {
        Intent myIntentPr1 = new Intent(MyActivity.this, Smoothing.class);
        //gui du lieu linkImg
        myIntentPr1.putExtra("linkImg", link_image);
        startActivityForResult(myIntentPr1, REQUEST_CODE_IMG_PROCESS_SMOOTHING);
    }

    private void openMyaxt()
    {
        Intent myExt_axt = new Intent(MyActivity.this,Smoonthing_Ext.class);
        myExt_axt.putExtra("linkImg", link_image);
        startActivity(myExt_axt);
    }

    private  void openConfigThreshold()
    {
        Intent myThreshold = new Intent(MyActivity.this,Threshold.class);
        startActivity(myThreshold);
    }
    /**
     * Xử lý kết quả trả về ở đây
     * crash khi bam back
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //Kiểm tra có đúng requestCode =REQUEST_CODE_INPUT hay không
        //Vì ta có thể mở Activity với những RequestCode khác nhau
        if (requestCode == REQUEST_OPEN_FILE_CHOOSER) {
            //Kiểm trả ResultCode trả về, cái này ở bên InputDataActivity truyền về
            //có nó rồi thì xử lý trở lên thông thường
            if (resultCode == RESULT_OK) {
                String link = data.getStringExtra("GetPath");
                link = link + "/" + data.getStringExtra("GetFileName");
                //File root = Environment.getExternalStorageDirectory();
                //Bitmap bMap = BitmapFactory.decodeFile(root + "/Download/nhung-hinh-anh-thien-nhien-dep-5.jpg");
                //convert to bitmap:
                link_image = link;
                Mat img = Highgui.imread(link_image);
                Bitmap bm = Bitmap.createBitmap(img.cols(), img.rows(), Bitmap.Config.ARGB_8888);
                //Convert to bitmap
                Utils.matToBitmap(img, bm);
                imgView.setImageBitmap(bm);
            }

        }
        if (requestCode == REQUEST_CODE_IMG_PROCESS_SMOOTHING) {
            if (resultCode == RESULT_OK) {
                //lay bitmap tu cache
                //imgView.setImageBitmap(imgLoader.getBitmapFromMemCache(link_image);
                if(imcahe.getBitmap(link_image)==null)
                {
                    Toast.makeText(this,"Khong the get bitmap",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    imgView.setImageBitmap(imcahe.getBitmap(link_image));
                }
            }
        }
    }
    public void DisplayImg(Mat src, Mat dst, String pathname, ImageView iv)
    {
        Bitmap preBitmap;
        preBitmap = imcahe.getBitmap(pathname);
        if(preBitmap == null)
        {
            dst = src;
            preBitmap = Bitmap.createBitmap(src.cols(), src.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(dst, preBitmap);
            Toast.makeText(this,"Khong su dung cache",Toast.LENGTH_LONG).show();
            iv.setImageBitmap(preBitmap);

        }
        else {
            Utils.bitmapToMat(preBitmap,dst);
            iv.setImageBitmap(preBitmap);
        }
    }
    /**
     * Slide menu item click listener
     * */
    /*private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }*/

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                //fragment = new FindPeopleFragment();
                break;
            case 2:
                //fragment = new PhotosFragment();
                break;
            case 3:
                //fragment = new CommunityFragment();
                break;
            case 4:
                //fragment = new PagesFragment();
                break;
            case 5:
                //fragment = new WhatsHotFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
            fragmentManager.beginTransaction().detach(fragment);


            // update selected item and title, then close the drawer
            /*mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);*/
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
