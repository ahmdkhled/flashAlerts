package com.ahmedkhaled.flashalerts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    FlashHelper flashHelper;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
    return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            flashHelper = new FlashHelper(this);
            flashHelper.alert(200);
            Log.d("TAG","hello from service SMS");
        }
       else if (intent.getAction().equals("android.intent.action.PHONE_STATE")){
            flashHelper = new FlashHelper(this);
            flashHelper.callAlert(200);
            Log.d("TAG","hello from service CALL");
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flashHelper.releaseFlash();
        try {
        flashHelper.callhandler.removeCallbacks(flashHelper.callrunnable);}
        catch (Exception e){

        }
        Log.d("TAG","service Destroyed");
    }
}
