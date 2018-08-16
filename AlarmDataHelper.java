package com.example.user.standartaalarmclock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 7/18/2018.
 */

public class AlarmDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="AlarmDB";

    private static final int DATABASE_VERSION=1;

    public static final String TABLE_ALARM="alrmTable";
    public static final String COLUMN_ALARM_ID="id";
    public static final String COLUMN_ALARM_NAME="alrmName";
    public static final String COLUMN_ALARM_HOURE="alrmHoure";
    public static final String COLUMN_ALARM_MINUTE="alrmMinute";
    public static final String COLUMN_ALARM_FORMATE="alrmFormate";
    public static final String COLUMN_ALARM_DAY="alrmDay";



    public static final String TABLE_ToggolButtonHandle="toggoleButonPostionTable";
    public static final String COLUMN_ToggolButtonHandle_ID="id";
    public static final String COLUMN_ToggolButtonHandle_position="position";



    private String createEmployee="create table "+TABLE_ALARM+"("+COLUMN_ALARM_ID+" integer primary key autoincrement,"+COLUMN_ALARM_NAME+" text,"+COLUMN_ALARM_HOURE+" integer,"+COLUMN_ALARM_MINUTE+" integer,"+COLUMN_ALARM_FORMATE+" integer,"+COLUMN_ALARM_DAY+" text);";


    private String createToggolButtonPosotion="create table "+TABLE_ToggolButtonHandle+"("+COLUMN_ToggolButtonHandle_ID+" integer primary key autoincrement,"+COLUMN_ToggolButtonHandle_position+" integer );";

    public AlarmDataHelper(Context context) {


        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(createEmployee);
         db.execSQL(createToggolButtonPosotion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
