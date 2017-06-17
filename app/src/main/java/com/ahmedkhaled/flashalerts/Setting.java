package com.ahmedkhaled.flashalerts;

import android.content.Context;
import android.content.SharedPreferences;


public class Setting  {
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Setting(Context context) {
         sharedPreferences=context.getSharedPreferences("myprefs",Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();
    }

    void setOnOrOff(Boolean OnOrOff){
        if(OnOrOff){
            editor.putBoolean("onOrOff",true);
            editor.commit();
        }else {
            editor.putBoolean("onOrOff",false);
            editor.commit();
        }
    }

    public boolean getSatte(){
       return sharedPreferences.getBoolean("onOrOff",false);
    }
}
