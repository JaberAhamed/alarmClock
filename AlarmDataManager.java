package com.example.user.standartaalarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AlarmDataManager {
    private Context context;

    private AlarmDataHelper alarmDataHelper;
    private SQLiteDatabase sqLiteDatabase;

    public AlarmDataManager(Context context) {
        this.context = context;
        alarmDataHelper=new AlarmDataHelper(context);
    }

    public long addAlarmData(AlarmDataClass alarmDataClass){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_NAME,alarmDataClass.getAlarm_Name());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_HOURE,alarmDataClass.getAlarm_Houre());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_MINUTE,alarmDataClass.getAlarm_Minute());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_FORMATE,alarmDataClass.getTime_Formate());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_DAY,alarmDataClass.getAlarm_Day());




        long insertedRow=sqLiteDatabase.insert(AlarmDataHelper.TABLE_ALARM,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }
    public long updateAlarmTime(AlarmDataClass alarmDataClass){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_NAME,alarmDataClass.getAlarm_Name());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_HOURE,alarmDataClass.getAlarm_Houre());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_MINUTE,alarmDataClass.getAlarm_Minute());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_FORMATE,alarmDataClass.getTime_Formate());
        contentValues.put(AlarmDataHelper.COLUMN_ALARM_DAY,alarmDataClass.getAlarm_Day());
        long insertedRow=sqLiteDatabase.update(AlarmDataHelper.TABLE_ALARM,contentValues,AlarmDataHelper.COLUMN_ALARM_ID+" = "+alarmDataClass.getId(),null);
        sqLiteDatabase.close();
        return insertedRow;

    }

    public long deleteAlarm(int id){
        open();
        long deleted=sqLiteDatabase.delete(AlarmDataHelper.TABLE_ALARM,AlarmDataHelper.COLUMN_ALARM_ID+" = "+id,null);
        sqLiteDatabase.close();
        return deleted;
    }
    public ArrayList<AlarmDataClass> getAlarmData(){
        ArrayList<AlarmDataClass>alarmDataClasses=new ArrayList<>();
        open();
        String selectQuery="select * from "+AlarmDataHelper.TABLE_ALARM;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                AlarmDataClass alarmDataClass=new AlarmDataClass();
                alarmDataClass.setId(cursor.getInt(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ALARM_ID)));
                alarmDataClass.setAlarm_Name(cursor.getString(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ALARM_NAME)));
                alarmDataClass.setAlarm_Houre(cursor.getInt(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ALARM_HOURE)));
                alarmDataClass.setAlarm_Minute(cursor.getInt(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ALARM_MINUTE)));
                alarmDataClass.setTime_Formate(cursor.getInt(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ALARM_FORMATE)));
                alarmDataClass.setAlarm_Day(cursor.getString(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ALARM_DAY)));




                alarmDataClasses.add(alarmDataClass);
            }while(cursor.moveToNext());
        }
        return alarmDataClasses;
    }

    private void open() {
        sqLiteDatabase=alarmDataHelper.getWritableDatabase();
    }
}
