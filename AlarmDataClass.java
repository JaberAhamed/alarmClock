package com.example.user.standartaalarmclock;

/**
 * Created by User on 7/12/2018.
 */

public class AlarmDataClass {
    private int id;
    private String alarm_Name;
    private int alarm_Houre;
    private int alarm_Minute;
    private int time_Formate;
    private String alarm_Day;
    private boolean isselected=false;

    public AlarmDataClass() {
    }

    public AlarmDataClass(int id, String alarm_Name, int alarm_Houre, int alarm_Minute, int time_Formate, String alarm_Day) {
        this.id = id;
        this.alarm_Name = alarm_Name;
        this.alarm_Houre = alarm_Houre;
        this.alarm_Minute = alarm_Minute;
        this.time_Formate = time_Formate;
        this.alarm_Day = alarm_Day;
    }

    public AlarmDataClass(String alarm_Name, int alarm_Houre, int alarm_Minute, int time_Formate, String alarm_Day) {
        this.alarm_Name = alarm_Name;
        this.alarm_Houre = alarm_Houre;
        this.alarm_Minute = alarm_Minute;
        this.time_Formate = time_Formate;
        this.alarm_Day = alarm_Day;
    }

    public boolean isIsselected() {
        return isselected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlarm_Name() {
        return alarm_Name;
    }

    public void setAlarm_Name(String alarm_Name) {
        this.alarm_Name = alarm_Name;
    }

    public int getAlarm_Houre() {
        return alarm_Houre;
    }

    public void setAlarm_Houre(int alarm_Houre) {
        this.alarm_Houre = alarm_Houre;
    }

    public int getAlarm_Minute() {
        return alarm_Minute;
    }

    public void setAlarm_Minute(int alarm_Minute) {
        this.alarm_Minute = alarm_Minute;
    }

    public int getTime_Formate() {
        return time_Formate;
    }

    public void setTime_Formate(int time_Formate) {
        this.time_Formate = time_Formate;
    }

    public String getAlarm_Day() {
        return alarm_Day;
    }

    public void setAlarm_Day(String alarm_Day) {
        this.alarm_Day = alarm_Day;
    }
    public void setSelecet(boolean selecet){

        this.isselected=selecet;
    }

    /* private int id;
    private String alarm_Name;
    private String alarm_Houre;
    private String alarm_Minute;
    private String time_Formate;
    private String alarm_Day;


    public AlarmDataClass() {
    }

    public AlarmDataClass(int id, String alarm_Name, String alarm_Houre, String alarm_Minute, String time_Formate, String alarm_Day) {
        this.id = id;
        this.alarm_Name = alarm_Name;
        this.alarm_Houre = alarm_Houre;
        this.alarm_Minute = alarm_Minute;
        this.time_Formate = time_Formate;
        this.alarm_Day = alarm_Day;

    }
    public AlarmDataClass( String alarm_Name, String alarm_Houre, String alarm_Minute, String time_Formate, String alarm_Day) {

        this.alarm_Name = alarm_Name;
        this.alarm_Houre = alarm_Houre;
        this.alarm_Minute = alarm_Minute;
        this.time_Formate = time_Formate;
        this.alarm_Day = alarm_Day;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlarm_Name() {
        return alarm_Name;
    }

    public void setAlarm_Name(String alarm_Name) {
        this.alarm_Name = alarm_Name;
    }

    public String getAlarm_Houre() {
        return alarm_Houre;
    }

    public void setAlarm_Houre(String alarm_Houre) {
        this.alarm_Houre = alarm_Houre;
    }

    public String getAlarm_Minute() {
        return alarm_Minute;
    }

    public void setAlarm_Minute(String alarm_Minute) {
        this.alarm_Minute = alarm_Minute;
    }

    public String getTime_Formate() {
        return time_Formate;
    }

    public void setTime_Formate(String time_Formate) {
        this.time_Formate = time_Formate;
    }

    public String getAlarm_Day() {
        return alarm_Day;
    }

    public void setAlarm_Day(String alarm_Day) {
        this.alarm_Day = alarm_Day;
    }*/


}
