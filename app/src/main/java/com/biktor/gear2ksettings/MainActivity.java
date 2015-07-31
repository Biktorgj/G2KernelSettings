package com.biktor.gear2ksettings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

            }

        });
    }

    public void launchSettings(View view) {
        Intent i = new Intent(MainActivity.this, displaySettingsActivity.class);
        startActivity(i);
    }

    public void updateKernelSettings(View view) {
        Intent serviceIntent = new Intent(this, bootNotifierService.class);
        startService(serviceIntent);
    }
}
