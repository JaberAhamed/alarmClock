package com.example.user.standartaalarmclock;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity  {


   // Context context;
    ListView lstview;
    TextView textView;
    AlarmDataManager alarmDataManager;
    ToggolButtonHandleDataManager toggolButtonHandleDataManager;
    PopupWindow popupWindow;
    boolean cheackColor=false;


    int pos;

    ArrayList<AlarmDataClass> alarmDataClasses;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // context=this;
       textView=findViewById(R.id.alrmdata);
        lstview=(ListView)findViewById(R.id.list_View);

        alarmDataManager=new AlarmDataManager(this);
        toggolButtonHandleDataManager=new ToggolButtonHandleDataManager(this);




        alarmDataClasses=alarmDataManager.getAlarmData();











        final ArrayAdapter<AlarmDataClass>alarmDataClassArrayAdapter=new LanguageAdapter(this,R.layout.single_layout,R.id.alarmNameTV,alarmDataClasses);

       lstview.setAdapter(alarmDataClassArrayAdapter);

        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                   pos=position;

                    Intent intent=new Intent(MainActivity.this,Alarm_Activity.class);
                    pos=alarmDataClasses.get(position).getId();
                    intent.putExtra("pos",pos);

                    startActivity(intent);


                }


        });

        lstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, long id) {


                Toast.makeText(MainActivity.this, "   "+alarmDataClasses.get(position).getId()+"  "+position, Toast.LENGTH_SHORT).show();



                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete");
                builder.setMessage(alarmDataClasses.get(position).getAlarm_Name());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToggleButton toggleButton=findViewById(R.id.set_alarm);
                        if (toggleButton.isChecked()){
                            Toast.makeText(MainActivity.this, "Alarm is on Cannot delete", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            long data= alarmDataManager.deleteAlarm(alarmDataClasses.get(position).getId());

                          //  toggolButtonHandleDataManager.deleteToggolButtonPositon(new HandletoggolButton(position).getId());


                            alarmDataClassArrayAdapter.remove(alarmDataClasses.get(position));
                            alarmDataClassArrayAdapter.notifyDataSetChanged();

                            if (data>0){
                                Toast.makeText(MainActivity.this, "DELETED ", Toast.LENGTH_SHORT).show();
                                // startActivity(getIntent());
                                onResume();
                                //finish();
                            }
                        }


                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.show();


                return true;




            }
        });





    }







    public void createNewAlarm(View view) {
        Intent intent=new Intent(MainActivity.this,Alarm_Activity.class);
        intent.putExtra("pos",500);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onResume() {

        super.onResume();
        ArrayList<AlarmDataClass> alarmDataClasses=alarmDataManager.getAlarmData();
        final ArrayAdapter<AlarmDataClass>alarmDataClassArrayAdapter=new LanguageAdapter(this,R.layout.single_layout,R.id.alarmNameTV,alarmDataClasses);
        alarmDataClassArrayAdapter.notifyDataSetChanged();
        lstview.setAdapter(alarmDataClassArrayAdapter);


    }


}
