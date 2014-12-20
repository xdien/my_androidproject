package com.crazy.xdien.suaanh.imageCache;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xdien on 24/09/2014.
 */
public class MyPagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == ((View) arg1));
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resId = 0;
        switch (position) {
            case 1:
                //resId = R.layout.instructions_page1;
                break;
            case 2:
                //resId = R.layout.instructions_page2;
                break;
        }
        View view = inflater.inflate(resId, null);
        ((ViewPager) container).addView(view, 0);
        return view;
    }
}