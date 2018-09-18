package com.example.luckyluke.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class StringReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle mBundle = intent.getExtras();
        String mString = mBundle.getString("test");
        Toast.makeText(context, "OK!! " + mString, Toast.LENGTH_LONG).show();
    }
}
