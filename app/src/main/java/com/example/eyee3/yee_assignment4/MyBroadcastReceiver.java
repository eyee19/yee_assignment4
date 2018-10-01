package com.example.eyee3.yee_assignment4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent broadcast) {
        String action = broadcast.getAction();

        if(action.equals("my.action.string")){
            String state = broadcast.getExtras().getString("extra");
            Toast.makeText(context, state + " received a 5 star rating!", Toast.LENGTH_LONG).show();
        }
    }
}
