package com.crazy.xdien.crazyedit.tab_frag;

/**
 * Created by xdien on 10/22/14.
 */
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crazy.xdien.crazyedit.MainActivity;
import com.crazy.xdien.crazyedit.R;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class  HomeFrag extends Fragment {
    public static final int PICKFILE_RESULT_CODE =110;
    public static String picturePath;
    public static Bitmap thumbnail;
    private static boolean chooseIamge = false;
    private static Button main_openfileImage;

    public static ImageViewTouch main_ImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_frag, container, false);
        //((TextView)android.findViewById(R.id.textView)).setText("Android");
        main_openfileImage = (Button) rootView.findViewById(R.id.button_openImage);
        main_ImageView = (ImageViewTouch) rootView.findViewById(R.id.view);
        main_openfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile();
            }
        });
        return rootView;
    }
    //Ham mo file
    private void openFile(){
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICKFILE_RESULT_CODE);
    }
    /*Ham nhan ket qua tra ve*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == MainActivity.RESULT_OK ) {
                    Uri selectedImage = data.getData();
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    picturePath = c.getString(columnIndex);
                    c.close();
                    thumbnail = (BitmapFactory.decodeFile(picturePath));
                    //save image to cache
                    if (thumbnail == null) {
                        Toast.makeText(getActivity(), "Canh bao: khong the giai ma file chuong trinh co the dong!", Toast.LENGTH_LONG);
                        Log.e("MainActivity open fail:", "Chuong trinh se dong!!");
                    } else {
                        MainActivity.bmCache.putBitmap(picturePath, thumbnail);
                        Log.w("path of image from gallery......******************.........", picturePath + "");
                        chooseIamge = true;
                        main_openfileImage.setVisibility(View.INVISIBLE);
                        main_ImageView.setImageBitmap(thumbnail);
                    }
                }
                break;
            default:
                break;
        }
    }
}
