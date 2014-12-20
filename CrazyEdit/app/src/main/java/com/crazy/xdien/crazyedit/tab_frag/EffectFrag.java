package com.crazy.xdien.crazyedit.tab_frag;

/**
 * Created by xdien on 10/22/14.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazy.xdien.crazyedit.R;

public class EffectFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.effect_frag, container, false);
        ((TextView)android.findViewById(R.id.textView)).setText("Android");
        return android;
    }}
