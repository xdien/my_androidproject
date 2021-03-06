package com.crazy.xdien.useapixposed;

/**
 * Created by xdien on 8/9/14.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.crazy.xdien.useapixposed.Constants.STATE;

import java.util.Locale;

public class GoogleSearchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String queryText = intent.getStringExtra(GoogleSearchApi.KEY_QUERY_TEXT);
        //queryText = queryText.toLowerCase(Locale.ENGLISH);
        queryText = queryText.toLowerCase(Locale.ENGLISH);

        if (!queryText.contains("turn") && !queryText.contains("toggle"))
            return;

        STATE newState = STATE.UNDEFINED;

        if (queryText.contains("on")) {
            newState = STATE.TURN_ON;
        } else if (queryText.contains("off")) {
            newState = STATE.TURN_OFF;
        } else if (queryText.contains("toggle")
                && !queryText.contains("on") && !queryText.contains("off")) {
            newState = STATE.TOGGLE;
        }

        if (newState.equals(STATE.UNDEFINED))
            return;

        /*if (queryText.contains("wifi") || queryText.contains("wi-fi")) {
            WifiHandler.handleStateChange(context, newState);
        }

        if (queryText.contains("bluetooth")) {
            BluetoothHandler.handleStateChange(context, newState);
        }

        if (queryText.contains("data")) {
            MobileDataHandler.handleStateChange(context, newState);
        }*/
        if(queryText.contains("sokect")){

        }
    }


}
