package com.crazy.xdien.suaanh.menuSliding;

/**
 * Created by xdien on 9/25/14.
 */


        import android.app.Fragment;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import com.crazy.xdien.suaanh.R;
        import com.crazy.xdien.suaanh.img_effects.Smoonthing_Ext;
        import com.crazy.xdien.suaanh.img_effects.Threshold;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn = (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
            {
                Intent intent = new Intent();

                // explicitly set the activity context and class
                // associated with the intent (context, class)
                intent.setClass(getActivity(), Smoonthing_Ext.class);

                // pass the current position
                //intent.putExtra("index", index);

                startActivity(intent);
            }
                break;
            default:
                break;
        }
    }

}