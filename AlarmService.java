package com.example.user.standartaalarmclock;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class AlarmService extends Service {
    MediaPlayer mediaPlayer;
    private int startId;
    private boolean isRunning;
    private Context context;
    private static final int NOTID=1;

    public AlarmService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();






        String state=intent.getExtras().getString("extra");
        assert state != null;
        switch (state) {
            case "yes":

                startId = 1;
                break;
            case "No":
                startId = 0;

                break;
            default:
                startId = 0;
                break;
        }

        if (!this.isRunning && startId==1)
        {
            Log.e("there is no music","and you want start");
            mediaPlayer=MediaPlayer.create(this,R.raw.sleepy);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

           sendNotification(" woke! up! woke! up! ");

            Intent i = new Intent();
            i.setClass(this, Pop.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);


            Toast.makeText(this, "there is no music and you want start", Toast.LENGTH_SHORT).show();

            this.isRunning=true;
            this.startId=0;
        }
        else if(this.isRunning && startId==0)
        {
            Log.e("there is  music","and you want stop");
            mediaPlayer.stop();
            mediaPlayer.reset();
            sendNotification("");

            Toast.makeText(this, "there is  music and you want stop ", Toast.LENGTH_SHORT).show();

            this.isRunning=false;
            this.startId=0;
        }
        else if (!this.isRunning && startId ==0)
        {
            Log.e("there is no music","and you want stop");
            Toast.makeText(this, "there is no music and you want stop ", Toast.LENGTH_SHORT).show();

            this.isRunning=false;
            this.startId=0;
            stopSelf();
        }
        else if(this.isRunning && startId==1)
        {
            Log.e("there is  music","and you want start");
            Toast.makeText(this, "there is  music and you want start", Toast.LENGTH_SHORT).show();

            this.isRunning=true;
            this.startId=1;
        }
        else {
            Log.e("else "," somhow any problem ");
        }





        return START_NOT_STICKY;
    }






    @Override
    public void onDestroy() {

        Log.e("OnDestroy process "," somhow any problem "+this.isRunning);


        super.onDestroy();

        this.isRunning=false;
    }



    private void sendNotification(String msg) {
        int NOTIFICATION_ID = 234;
        String CHANNEL_ID = "my_channel_01";
        Log.d("AlarmService ","  Alarm service preparing for send notificatio "+ msg);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {



            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_MIN;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(" AALLARRMM NOTIFY ")
                .setContentText(msg);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);

        builder.setContentIntent(pendingIntent);

        //notificationManager.notify(NOTIFICATION_ID, builder.build());
        /// startForeground(NOTIFICATION_ID,builder.build());

        if (msg.equals("")){

            stopSelf();
        }
        else {
            startForeground(NOTIFICATION_ID,builder.build());
        }


    }

}
