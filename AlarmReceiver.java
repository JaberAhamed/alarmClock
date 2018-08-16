package com.example.user.standartaalarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "itsssss run perfectly ", Toast.LENGTH_SHORT).show();
        String get_String_Key=intent.getStringExtra("extra");


        Intent intent_service=new Intent(context,AlarmService.class);

        intent_service.putExtra("extra",get_String_Key);

       //context.startService(intent_service);


       context.startForegroundService(intent_service);
    }
}
