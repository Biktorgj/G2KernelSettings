package com.biktor.gear2ksettings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Biktor on 31/07/2015.
 */
public class AutoStart extends BroadcastReceiver {

    final static String TAG = "GearKernelSettings";

    @Override
    public void onReceive(Context context, Intent arg1) {
        Log.w(TAG, "Establishing kernel settings");
        context.startService(new Intent(context, bootNotifierService.class));
    }
}