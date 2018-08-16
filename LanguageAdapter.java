package com.example.user.standartaalarmclock;


import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.CompoundButton;

import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static java.util.Calendar.DAY_OF_WEEK;


/**
 * Created by User on 7/11/2018.
 */

public class LanguageAdapter extends ArrayAdapter<AlarmDataClass> {
    int groupid;
    Calendar cal;
    ArrayList<AlarmDataClass> item_list;
    Context context;
    AlarmManager alarmManager;
    boolean isExecuted = false;
    ToggolButtonHandleDataManager toggolButtonHandleDataManager = new ToggolButtonHandleDataManager(context);
    ArrayList<HandletoggolButton> handletoggolButtons = new ArrayList<>();

    private PendingIntent pendingIntent;

    public LanguageAdapter(Context context, int vg, int id, ArrayList<AlarmDataClass> item_list) {
        super(context, vg, id, item_list);
        this.context = context;
        groupid = vg;
        this.item_list = item_list;

    }

    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView alarmName, textview, textViewe;
        public ToggleButton toggleButton;


    }

    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View rowView = null;
        final ViewHolder viewHolder = new ViewHolder();
        toggolButtonHandleDataManager = new ToggolButtonHandleDataManager(context);
        // Inflate the list_item.xml file if convertView is null
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(groupid, parent, false);

            viewHolder.alarmName = (TextView) rowView.findViewById(R.id.alarmNameTV);
            viewHolder.textview = (TextView) rowView.findViewById(R.id.txt);
            viewHolder.textViewe = (TextView) rowView.findViewById(R.id.textd);
            viewHolder.toggleButton = rowView.findViewById(R.id.set_alarm);


            viewHolder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    handletoggolButtons = toggolButtonHandleDataManager.getAllToggolPosition();
                    if (isChecked) {

                        //  insertToggolButtonPosition();


                        if (item_list.get(position).getAlarm_Day().contains("sun")) {
                            int sun = Calendar.SUNDAY;
                            forday(1, item_list.get(position).getAlarm_Houre(), item_list.get(position).getAlarm_Minute(), position, item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("mon")) {
                            int mon = Calendar.MONDAY;
                            forday(2, item_list.get(position).getAlarm_Houre(), item_list.get(position).getAlarm_Minute(), position, item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("tue")) {
                            int tue = Calendar.TUESDAY;
                            forday(3, item_list.get(position).getAlarm_Houre(), item_list.get(position).getAlarm_Minute(), position, item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("wed")) {
                            int wed = Calendar.WEDNESDAY;
                            forday(4, item_list.get(position).getAlarm_Houre(), item_list.get(position).getAlarm_Minute(), position, item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("thu")) {
                            int thu = Calendar.THURSDAY;

                            forday(5, item_list.get(position).getAlarm_Houre(), item_list.get(position).getAlarm_Minute(), position, item_list.get(position).getTime_Formate());

                        }
                        if (item_list.get(position).getAlarm_Day().contains("fri")) {

                            forday(6, item_list.get(position).getAlarm_Houre(), item_list.get(position).getAlarm_Minute(), position, item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("sat")) {
                            int sat = Calendar.SATURDAY;
                            forday(7, item_list.get(position).getAlarm_Houre(), item_list.get(position).getAlarm_Minute(), position, item_list.get(position).getTime_Formate());
                        }

                    } else {

                        /*toggolButtonHandleDataManager.deleteToggolButtonPositon(position);
                        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();*/
                        if (item_list.get(position).getAlarm_Day().contains("sat")) {
                            int sat = Calendar.SATURDAY;

                            cancelalrm(7, position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("sun")) {
                            int sun = Calendar.SUNDAY;

                            cancelalrm(1, position);
                        }
                        if (item_list.get(position).getAlarm_Day().contains("mon")) {
                            int mon = Calendar.MONDAY;
                            cancelalrm(2, position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("tue")) {
                            int tue = Calendar.TUESDAY;
                            cancelalrm(3, position);


                        }
                        if (item_list.get(position).getAlarm_Day().contains("wed")) {
                            int wed = Calendar.WEDNESDAY;
                            cancelalrm(4, position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("thu")) {
                            int thu = Calendar.THURSDAY;
                            cancelalrm(5, position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("fri")) {
                            cancelalrm(6, position);

                        }


                    }

                }

                private void insertToggolButtonPosition() {


                    ArrayList<Integer> kdf = new ArrayList<>();
                    for (int i = 0; i < handletoggolButtons.size(); i++) {
                        kdf.add(handletoggolButtons.get(i).getPositon());


                    }
                    if (!kdf.toString().contains(String.valueOf(position))) {
                        Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
                        toggolButtonHandleDataManager.addToggolButtonPositon(new HandletoggolButton(position));
                        Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
                    }

                }

                   /* viewHolder.toggleButton.setTag(item_list.get(position));
                    AlarmDataClass alarmDataClass= (AlarmDataClass) viewHolder.toggleButton.getTag();
                    alarmDataClass.setSelecet(buttonView.isChecked());
                    Toast.makeText(context,String.valueOf(position) , Toast.LENGTH_SHORT).show();*/


            });


            ArrayList<HandletoggolButton> handletoggolButtons = new ArrayList<>();
            handletoggolButtons = toggolButtonHandleDataManager.getAllToggolPosition();
            if (!isExecuted) {
                for (int i = 0; i < handletoggolButtons.size(); i++) {

                    viewHolder.toggleButton.setTag(item_list.get(handletoggolButtons.get(i).getPositon()));
                    AlarmDataClass alarmDataClass = (AlarmDataClass) viewHolder.toggleButton.getTag();
                    alarmDataClass.setSelecet(true);
                    Toast.makeText(context, " ....." + handletoggolButtons.size(), Toast.LENGTH_SHORT).show();
                }
                isExecuted = true;
            }


            rowView.setTag(viewHolder);
            /*   viewHolder.toggleButton.setTag(item_list.get(1));*/
        } else {
            rowView = convertView;

            ((ViewHolder) rowView.getTag()).toggleButton.setTag(item_list.get(position));
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.alarmName.setText(item_list.get(position).getAlarm_Name());

        String ampm;
        if (item_list.get(position).getTime_Formate() == 0) {
            ampm = "AM";
        } else {
            ampm = "PM";
        }
        holder.textview.setText(item_list.get(position).getAlarm_Houre() + ":" + item_list.get(position).getAlarm_Minute() + " " + ampm);
        holder.textViewe.setText(item_list.get(position).getAlarm_Day());

        holder.toggleButton.setChecked(item_list.get(position).isIsselected());










           /* holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked){
                      if (item_list.get(position).getAlarm_Day().contains("sat")){
                             int sat=Calendar.SATURDAY;
                          forday(7,item_list.get(position).getAlarm_Houre(),item_list.get(position).getAlarm_Minute(),position,item_list.get(position).getTime_Formate());
                      }
                        if (item_list.get(position).getAlarm_Day().contains("sun")){
                            int sun=Calendar.SUNDAY;
                            forday(1,item_list.get(position).getAlarm_Houre(),item_list.get(position).getAlarm_Minute(),position,item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("mon")){
                            int mon=Calendar.MONDAY;
                            forday(2,item_list.get(position).getAlarm_Houre(),item_list.get(position).getAlarm_Minute(),position,item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("tue")){
                            int tue=Calendar.TUESDAY;
                            forday(3,item_list.get(position).getAlarm_Houre(),item_list.get(position).getAlarm_Minute(),position,item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("wed")){
                            int wed=Calendar.WEDNESDAY;
                            forday(4,item_list.get(position).getAlarm_Houre(),item_list.get(position).getAlarm_Minute(),position,item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("thu")){
                            int thu=Calendar.THURSDAY;
                            forday(5,item_list.get(position).getAlarm_Houre(),item_list.get(position).getAlarm_Minute(),position,item_list.get(position).getTime_Formate());
                        }
                        if (item_list.get(position).getAlarm_Day().contains("fri")){

                            forday(6,item_list.get(position).getAlarm_Houre(),item_list.get(position).getAlarm_Minute(),position,item_list.get(position).getTime_Formate());
                        }









                    }
                    else {
                        if (item_list.get(position).getAlarm_Day().contains("sat")){
                            int sat=Calendar.SATURDAY;

                          cancelalrm(7,position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("sun")){
                            int sun=Calendar.SUNDAY;

                            cancelalrm(1,position);
                        }
                        if (item_list.get(position).getAlarm_Day().contains("mon")){
                            int mon=Calendar.MONDAY;
                            cancelalrm(2,position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("tue")){
                            int tue=Calendar.TUESDAY;
                            cancelalrm(3,position);


                        }
                        if (item_list.get(position).getAlarm_Day().contains("wed")){
                            int wed=Calendar.WEDNESDAY;
                            cancelalrm(4,position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("thu")){
                            int thu=Calendar.THURSDAY;
                            cancelalrm(5,position);

                        }
                        if (item_list.get(position).getAlarm_Day().contains("fri")){
                            cancelalrm(7,position);

                        }


                    *//*    Toast.makeText(context, "is not checked ", Toast.LENGTH_SHORT).show();

                        if (checkservice()==true){
                            final Intent intent=new Intent(context,AlarmReceiver.class);
                            intent.putExtra("extra","No");
                            context.sendBroadcast(intent);

                            pendingIntent=PendingIntent.getBroadcast(context,position,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.cancel(pendingIntent);
                        }
                        else {
                            final Intent intent=new Intent(context,AlarmReceiver.class);
                            pendingIntent=PendingIntent.getBroadcast(context,position,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.cancel(pendingIntent);
                        }*//*

                    }
                }


            });
*/
        return rowView;
    }

    private void cancelalrm(int week, int position) {

        int pos = position + week + 102;
        final Intent intent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, pos, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(context, "CANCEL   " + pos + "  " + week + position, Toast.LENGTH_LONG).show();


    }

    public void forday(int week, int hour, int minuts, int position, int formate) {


        Calendar calSet = Calendar.getInstance();
        Calendar  now=Calendar.getInstance();
        int wek =now.get(Calendar.DAY_OF_WEEK);

        calSet.set(Calendar.DAY_OF_WEEK,week);
        calSet.set(Calendar.HOUR_OF_DAY, hour);
        calSet.set(Calendar.MINUTE, minuts);
        calSet.set(Calendar.AM_PM, formate);
        calSet.set(Calendar.SECOND, 0);

        if (calSet.getTimeInMillis()<=now.getTimeInMillis()){


            calSet.add(Calendar.DAY_OF_MONTH,7);
        }



        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        int pos = position+ week;
        intent.putExtra("extra", "yes");
        pendingIntent = PendingIntent.getBroadcast(context, pos, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);

        //Toast.makeText(context, "Alarm set "  + "  "  + "  " + week + hour + minuts, Toast.LENGTH_LONG).show();




      /*  Long alarmTime = calSet.getTimeInMillis();

        //Also change the time to 24 hours.

            intent.putExtra("extra","yes");
            int pos=position+week;
            pendingIntent= PendingIntent.getBroadcast(context,pos,intent,0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,alarmTime, 2*60*1000, pendingIntent);
            Toast.makeText(context, "Alarm set "+pos+"  "+week+hour+minuts, Toast.LENGTH_LONG).show();*/
    }
   /* private boolean checkservice() {

        if (isServiceRunning(getContext(),AlarmService.class)){

            return true;

        }
        else {

            return false;
        }
    }

    private boolean isServiceRunning(Context context, Class<AlarmService> alarmServiceClass) {
        ActivityManager activityManager= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> sevices=activityManager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo:sevices){
            if (runningServiceInfo.service.getClassName().equals(alarmServiceClass.getName())){
                return true;
            }
        }
        return false;

    }*/





}
/*
            int wek =now.get(Calendar.DAY_OF_WEEK);

            if (wek!=week){
                calSet.add(Calendar.DAY_OF_MONTH,7);
                Toast.makeText(context, "false   and not week equal", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "false   week is equal", Toast.LENGTH_SHORT).show();
            }*/


        /*if (calSet.getTimeInMillis()<=now.getTimeInMillis()){
            Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
        }*/
// Toast.makeText(context, String.valueOf(calSet.getTimeInMillis() + "   " + now.getTimeInMillis() + "   " + System.currentTimeMillis()), Toast.LENGTH_LONG).show();

       /* if (calSet.getTimeInMillis() <= now.getTimeInMillis()) {
            _alarm = calSet.getTimeInMillis() + (AlarmManager.INTERVAL_DAY + 1);
            Toast.makeText(context, "plus" + calSet.getTimeInMillis() + " " + now.getTimeInMillis(), Toast.LENGTH_SHORT).show();
            //}
*/
        /*else{
            _alarm = calSet.getTimeInMillis();
            Toast.makeText(context, "--", Toast.LENGTH_SHORT).show();
        }





         /*if (wek!=week){
            days = week + (7 - now.get(Calendar.DAY_OF_WEEK));
            calSet.add(Calendar.DATE, days);
            calSet.set(Calendar.HOUR_OF_DAY, hour);
            calSet.set(Calendar.MINUTE, minuts);
            calSet.set(Calendar.AM_PM, formate);
            calSet.set(Calendar.SECOND, 0);
           *//* calSet.add(Calendar.DAY_OF_MONTH,7);*//*
            Toast.makeText(context, "not equal", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(context, "not equal", Toast.LENGTH_SHORT).show();
            calSet.add(Calendar.DATE, week);
            calSet.set(Calendar.HOUR_OF_DAY, hour);
            calSet.set(Calendar.MINUTE, minuts);
            calSet.set(Calendar.AM_PM, formate);
            calSet.set(Calendar.SECOND, 0);
        }*/


/*
        if (calSet.before(Calendar.getInstance())){
                calSet.add(Calendar.DAY_OF_MONTH,7);
                // calSet.add(Calendar.DAY_OF_MONTH,7);
                Toast.makeText(context, "after", Toast.LENGTH_SHORT).show();

                }
                else {
                Toast.makeText(context, " before ", Toast.LENGTH_SHORT).show();
                }

*/