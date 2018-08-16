package com.example.user.standartaalarmclock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Pop extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void stopAlarm(View view) {

        Toast.makeText(this, "stop alarm", Toast.LENGTH_SHORT).show();

        AlarmReceiver alarmReceiver=new AlarmReceiver();
        Intent intent=new Intent(this,AlarmService.class);
        intent.putExtra("extra","No");
        alarmReceiver.onReceive(this,intent);
        finish();
    }
}
