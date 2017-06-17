package com.ahmedkhaled.flashalerts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Setting setting =new Setting(context);
        if(setting.getSatte()){

        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            Intent callIntent = new Intent(context, MyService.class);
            callIntent.setAction("android.intent.action.PHONE_STATE");

            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int state = telephonyManager.getCallState();
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                Toast.makeText(context, " CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
                context.startService(callIntent);

            } else {
                Toast.makeText(context, " CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
                context.stopService(callIntent);

            }

            Log.d("TAG", "helo from receiver CALL");

        } else if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Intent smsIntent = new Intent(context, MyService.class);
            smsIntent.setAction("android.provider.Telephony.SMS_RECEIVED");
            context.startService(smsIntent);
            Log.d("TAG", "helo from receiver SMS");

        }

    }

    }

}
