package com.ahmedkhaled.flashalerts;


import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class FlashHelper {
    private Camera cam;
    private Camera.Parameters parameters;
    private Boolean state=false;
    private int time=0;
    Context context;
    Handler callhandler;
    Runnable callrunnable;

    FlashHelper(Context context) {
        this.context=context;
        try {
            cam=Camera.open();
            parameters=cam.getParameters();
        }catch (Exception e){
            Toast.makeText(context,"error openning flash "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void openFlash(){
        if (cam != null) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(parameters);
            cam.startPreview();
        }
    }
    private void closeFlash() {
        if (cam != null) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            cam.setParameters(parameters);
            cam.stopPreview();
        }
    }


    public void alert(final int period){
        final Handler handler =new Handler();
        Runnable runnable =new Runnable() {
            @Override
            public void run() {
               if (time<2000) {
                   if (state) {
                       Log.d("TAG", "on");
                       openFlash();

                   } else {
                       Log.d("TAG", "off");
                       closeFlash();
                   }
                   time = time + period;
                   Log.d("TAG","time is   "+time);
                   state = !state;
                   handler.postDelayed(this, period);
               }
                else {
                   releaseFlash();
                   context.stopService(new Intent(context,MyService.class));

               }
            }

        };
        handler.post(runnable);
    }

    public void callAlert(final int period){
        callhandler =new Handler();
        callrunnable =new Runnable() {
            @Override
            public void run() {

                    if (state) {
                        Log.d("TAG", "CALL on");
                        openFlash();

                    } else {
                        Log.d("TAG", "CALL off");
                        closeFlash();
                    }

                    state = !state;
                    callhandler.postDelayed(this, period);


            }

        };
        callhandler.post(callrunnable);
    }


    public void releaseFlash(){
        if(cam !=null){
            cam.release();
            cam=null;
        }
    }

}
