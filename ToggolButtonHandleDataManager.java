package com.example.user.standartaalarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ToggolButtonHandleDataManager {
    private Context context;

    private AlarmDataHelper alarmDataHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ToggolButtonHandleDataManager(Context context) {
        this.context = context;
        alarmDataHelper=new AlarmDataHelper(context);
    }

    public long addToggolButtonPositon(HandletoggolButton handletoggolButton){
        open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(AlarmDataHelper.COLUMN_ToggolButtonHandle_position,handletoggolButton.getPositon());
        long insertedRow=sqLiteDatabase.insert(AlarmDataHelper.TABLE_ToggolButtonHandle,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }

    public long deleteToggolButtonPositon(int id){
        open();
        long deleted=sqLiteDatabase.delete(AlarmDataHelper.TABLE_ToggolButtonHandle,AlarmDataHelper.COLUMN_ToggolButtonHandle_position+" = "+id,null);
        sqLiteDatabase.close();
        return deleted;
    }
    public ArrayList<HandletoggolButton> getAllToggolPosition(){
        ArrayList<HandletoggolButton>handletoggolButtons=new ArrayList<>();
        open();
        String selectQuery="select * from "+AlarmDataHelper.TABLE_ToggolButtonHandle;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HandletoggolButton handletoggolButton=new HandletoggolButton();
                handletoggolButton.setId(cursor.getInt(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ToggolButtonHandle_ID)));
                handletoggolButton.setPositon(cursor.getInt(cursor.getColumnIndex(AlarmDataHelper.COLUMN_ToggolButtonHandle_position)));

                handletoggolButtons.add(handletoggolButton);
            }while(cursor.moveToNext());
        }
        return handletoggolButtons;
    }

    private void open() {
        sqLiteDatabase=alarmDataHelper.getWritableDatabase();
    }



}
