package com.biktor.gear2ksettings;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class bootNotifierService extends Service {
    final static String TAG = "GearKernelSettings";
    public bootNotifierService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences appPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        boolean idlemode = appPreferences.getBoolean(getString(R.string.idle_mode), true);
        boolean partialmode = appPreferences.getBoolean(getString(R.string.partial_mode), true);
        boolean touchwake = appPreferences.getBoolean(getString(R.string.touchwake), true);
        String ambientLevel =  appPreferences.getString(getString(R.string.ambient_brightness), "0");
        String vibrationLevel = appPreferences.getString(getString(R.string.vibrastrength), "0");
        int ambientlevel=Integer.parseInt(ambientLevel);
        int vibrationlevel=Integer.parseInt(vibrationLevel);
        //Runtime.getRuntime().exec("echo 1 > /proc/tssettings/touchwake");
        FileWriter fWriter;
;
        /* IDLE MODE */
        if (!idlemode){
            try {
                File idleFile = new File("/proc/lcdsettings/idlemode");
                fWriter = new FileWriter(idleFile, true);
                fWriter.write("0");
                fWriter.flush();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                File idleFile = new File("/proc/lcdsettings/idlemode");
                fWriter = new FileWriter(idleFile, true);
                fWriter.write("1");
                fWriter.flush();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /* LOW COLOR DEPTH - ALPM PARTIAL MODE */
        if (!partialmode){
            try {
                File partialFile = new File("/proc/lcdsettings/partialmode");
                fWriter = new FileWriter(partialFile, true);
                fWriter.write("0");
                fWriter.flush();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                File partialFile = new File("/proc/lcdsettings/partialmode");
                fWriter = new FileWriter(partialFile, true);
                fWriter.write("1");
                fWriter.flush();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* TOUCH WAKE */
        if (!touchwake){
            try {
                File twFile = new File("/proc/tssettings/touchwake");
                fWriter = new FileWriter(twFile, true);
                fWriter.write("0");
                fWriter.flush();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                File twFile = new File("/proc/tssettings/touchwake");
                fWriter = new FileWriter(twFile, true);
                fWriter.write("1");
                fWriter.flush();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /* AMBIENT LEVEL */
        if (ambientlevel > 0 && ambientlevel <4)
        {
            try {
                File ambientFile = new File("/proc/lcdsettings/ambient_brightness");
                fWriter = new FileWriter(ambientFile, true);
                fWriter.write(ambientLevel);
                fWriter.flush();
                fWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /* VIBRATION STRENGTH */
        if (vibrationlevel > 0 && vibrationlevel < 10)
        {
            try {
                File vibrationFile = new File("/proc/vibrasettings/strength");
                fWriter = new FileWriter(vibrationFile, true);
                fWriter.write(vibrationLevel);
                fWriter.flush();
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        //display in long period of time
        Toast.makeText(getApplicationContext(), "Gear2KSettings: changes pushed", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

