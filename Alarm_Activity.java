package com.example.user.standartaalarmclock;

import android.app.*;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.attr.checked;

public class Alarm_Activity extends AppCompatActivity {

    private int  hour, minute;
    private Calendar now;
    int houre,minutt,h,m;
    String format,dayNam=" ";
    int am_pm;
   private EditText alarmNameEt;
    CheckBox eveday,saturday,sunday,monday,tuesday,wednesday,thursday,friday;
    int position;
    AlarmDataManager alarmDataManager;

    ArrayList<String>dayOfAlarm=new ArrayList<>();
    String allday="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_);

        alarmDataManager=new AlarmDataManager(this);

        now  = Calendar.getInstance();



        hour = now.get(Calendar.HOUR_OF_DAY);
        minute = now.get(Calendar.MINUTE);
        saturday=findViewById(R.id.satur_Day);
        sunday=findViewById(R.id.sun_Day);
        monday=findViewById(R.id.mon_Day);
        tuesday=findViewById(R.id.tues_Day);
        wednesday=findViewById(R.id.wednes_Day);
        thursday=findViewById(R.id.thurs_Day);
        friday=findViewById(R.id.fri_Day);
        alarmNameEt=findViewById(R.id.alarmET);

        Intent intent = getIntent();
        position= intent.getIntExtra("pos",0);
        Toast.makeText(this, "   the is she   "+position, Toast.LENGTH_SHORT).show();




    }


    public void time_Select(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,timeListener,hour,minute,false);


        timePickerDialog.show();


    }
    private TimePickerDialog.OnTimeSetListener timeListener =
            new TimePickerDialog.OnTimeSetListener() {


                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    now.set(0,0,0,i,i1);

                    timePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
                    timePicker.setCurrentMinute(now.get(Calendar.MINUTE));



                    minutt=timePicker.getMinute();

                    h=timePicker.getHour();
                    m=timePicker.getMinute();

                    if (h == 0) {

                        h += 12;

                        format = "AM";
                        am_pm=Calendar.AM;
                    }
                    else if (h == 12) {

                        format = "PM";
                        am_pm=Calendar.PM;

                    }
                    else if (h > 12) {

                        h -= 12;

                        format = "PM";
                        am_pm=Calendar.PM;


                    }
                    else {

                        format = "AM";
                        am_pm=Calendar.AM;

                    }
                    houre=h;
                    String hour_String,minute_String;
                    hour_String=String.valueOf(h);
                    minute_String=String.valueOf(m);
                    if (h<10){
                        hour_String="0"+hour_String;
                    }
                    if(m<10){
                        minute_String="0"+minute_String;
                    }

                    /*for ( i=0;i<dateClasses.size();i++){
                        if (position==dateClasses.get(i).getDate_Position()){
                            selectTime.setText(hour_String+":"+minute_String+" "+format);
                        }
                    }*/

                     ((Button)findViewById(R.id.time_Button)).setText(hour_String+" : "+minute_String+" "+format+"  "+position);
                }
            };




    public void onRadioButtonClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();



        switch(view.getId()) {

            case R.id.satur_Day:
                if (checked){

                    dayOfAlarm.add("sat");
                    Toast.makeText(this, " sat_Day "+dayOfAlarm.size(), Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(this, "remove sat_Day "+dayOfAlarm.size(), Toast.LENGTH_SHORT).show();

                    dayOfAlarm.remove("sat");

                }


                    break;
            case R.id.sun_Day:
                if (checked){



                    dayOfAlarm.add("sun");

                    Toast.makeText(this, " sun_Day " +dayOfAlarm.size(), Toast.LENGTH_SHORT).show();



                }
                else {

                    dayOfAlarm.remove("sun");
                    Toast.makeText(this, "remove sat_Day "+dayOfAlarm.size(), Toast.LENGTH_SHORT).show();
                }


                    break;
            case R.id.mon_Day:
                if (checked){
                    Toast.makeText(this, " mon_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.add("mon");
                }else{
                    Toast.makeText(this, "remove sat_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.remove("mon");
                }


                    break;
            case R.id.tues_Day:
                if (checked)
                {
                    Toast.makeText(this, " tue_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.add("tue");
                }
                else {
                    Toast.makeText(this, "remove sat_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.remove("tue");
                }


                    break;
            case R.id.wednes_Day:
                if (checked){
                    Toast.makeText(this, " wed_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.add("wed");
                }
                else{
                    Toast.makeText(this, "remove sat_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.remove("wed");
                }


                    break;
            case R.id.thurs_Day:
                if (checked){
                    Toast.makeText(this, " thu_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.add("thu");
                }
                else {
                    Toast.makeText(this, "remove sat_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.remove("thu");
                }

                    break;
            case R.id.fri_Day:
                if (checked){
                    Toast.makeText(this, " fri_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.add("fri");
                }
                  else {
                    Toast.makeText(this, "remove sat_Day ", Toast.LENGTH_SHORT).show();
                    dayOfAlarm.remove("fri");
                }

                    break;
        }

        TextView textView = findViewById(R.id.chekday);

        for(int i=0;i<dayOfAlarm.size();i++){

           allday= dayOfAlarm.get(i)+allday;

            textView.setText(dayOfAlarm.toString());
        }




    }


    public void saveAlarmData(View view) {

        String amarName=alarmNameEt.getText().toString();

        if (position==500){
            if (amarName.isEmpty()||houre==0||dayOfAlarm.toString().equals("")||dayOfAlarm.size()<1){
                Toast.makeText(this, "empty is not allow ", Toast.LENGTH_SHORT).show();
            }
            else {
                AlarmDataClass alarmDataClass=new AlarmDataClass(amarName,houre,minutt,am_pm,dayOfAlarm.toString());
                long isInserted=alarmDataManager.addAlarmData(alarmDataClass);
                if(isInserted>0){
                    Toast.makeText(this, "save"+isInserted, Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }else{
                    Toast.makeText(this, "unable to save data", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else {
            if (amarName.isEmpty()||houre==0||allday.equals("")){
                Toast.makeText(this, "empty is not allow ", Toast.LENGTH_SHORT).show();
            }
            else {
                AlarmDataClass alarmDataClass=new AlarmDataClass(position,amarName,houre,minutt,am_pm,dayOfAlarm.toString());
                long isInserted=alarmDataManager.updateAlarmTime(alarmDataClass);
                if(isInserted>0){
                    Toast.makeText(this, "update"+isInserted, Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(this, "not update", Toast.LENGTH_SHORT).show();
                }
            }

        }





    }

    public void add(View view) {

        dayOfAlarm.add("abd");
       /* for(int i=0;i<dayOfAlarm.size();i++){


            allday= dayOfAlarm.get(i);

        }*/
        TextView textView = findViewById(R.id.chekday);
        int i=0;

        String dfd="";
                dfd=dayOfAlarm.get(i)+dfd;
        textView.setText(dfd);
        i=i+1;


    }

    public void remov(View view) {
        dayOfAlarm.remove("abd");

        TextView textView = findViewById(R.id.chekday);
        String dfd=dayOfAlarm.get(0)+dayOfAlarm.get(1);
        textView.setText(dfd);
    }
}
