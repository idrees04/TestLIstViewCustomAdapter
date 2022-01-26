package com.practice.testlistviewcustomadapter;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
//import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.icu.text.MessagePattern.ArgType.SELECT;

/**
 * Created by idrees on 20-Jun-17.
 */

public class DatabaseHelperfor_insertschedule extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Schedulenew1.db";

    private static final String TABLE_NAME = "timetable";
    private static final String COL0 = "class_ID";
    private static final String COL1 = "class_room";
    private static final String COL2 = "teacher_name";
    private static final String COL3 = "start_time";
    private static final String COL4 = "end_time";
    private static final String COL5 = "day";

    private static final String TABLE_ATTENDANCE1 = "attendance";
    private static final String COL10 = "AttendanceID";
    private static final String COL11 = "class_";
    private static final String COL12 = "teacher_";
    private static final String COL13 = "status";
    private static final String COL14 = "late";
    private static final String COL15 = "leave";
    //    public static final String COL16 = "day";
    private static final String createTimeTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( class_ID INTEGER PRIMARY KEY AUTOINCREMENT, class_room TEXT, teacher_name TEXT, start_time TEXT, end_time TEXT, day TEXT);";
    private static final String createAttendance = "CREATE TABLE IF NOT EXISTS " + TABLE_ATTENDANCE1 + " ( AttendanceID INTEGER PRIMARY KEY AUTOINCREMENT, class_ TEXT, teacher_ TEXT, status TEXT, late TEXT, leave TEXT);";

    private static final String dropTimeTable="DROP TABLE " + TABLE_NAME +" IF EXISTS";
    private static final String dropAttendance="DROP IF TABLE" + TABLE_ATTENDANCE1 +" IF EXISTS ";
    public Context context;

    public DatabaseHelperfor_insertschedule(Context context) {
        super(context, DATABASE_NAME, null, 2);//for default location
        this.context=context;
//        super(context, Environment.getExternalStorageDirectory()//to create database in mobile phone sd card or external storage
//                + File.separator + DATABASE_NAME + File.separator + DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                " class_room TEXT, teacher_name TEXT, start_time TEXT, end_time TEXT, day TEXT)";
        try {
            db.execSQL(createTimeTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            db.execSQL(createAttendance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
        db.execSQL(dropAttendance);
        onCreate(db);
    } catch (SQLException e) {
        e.printStackTrace();
    }
        try {
        db.execSQL(dropTimeTable);
        onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            db.execSQL(createAttendance);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            db.execSQL(createTimeTable);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean AddData(String class_room, String teacher_name, String start_time, String end_time, String day) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, class_room);
        contentValues.put(COL2, teacher_name);
        contentValues.put(COL3, start_time);
        contentValues.put(COL4, end_time);
        contentValues.put(COL5, day);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //query for 1 week repeats
    public Cursor getListContentsViewSchedule() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT class_room, teacher_name FROM " + TABLE_NAME, null);
        return data;
    }
    public Cursor distinctGetListContents(String days, String startTime, String endTime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor distinctData = db.rawQuery("SELECT class_room, teacher_name FROM " + TABLE_NAME+" WHERE day = '"+days+"' AND start_time = '"+startTime+"' AND end_time = '"+endTime+"'", null);
        return distinctData;
    }

    public Cursor insertAttendance(String days, String startTime, String endTime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor insertAttendanceData = db.rawQuery("SELECT class_room, teacher_name FROM " + TABLE_NAME+" WHERE day = '"+days+"' AND start_time = '"+startTime+"' AND end_time = '"+endTime+"'", null);
        return insertAttendanceData;
    }

    public Cursor getAttendanceListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_ATTENDANCE1, null);
        return data;
    }
//( AttendanceID INTEGER PRIMARY KEY AUTOINCREMENT, class_ TEXT, teacher_ TEXT, status TEXT, late TEXT, leave TEXT);
    public Cursor getAbsentLateListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
////        String createAttendance = "CREATE TABLE IF NOT EXISTS " + TABLE_ATTENDANCE1 + " ( AttendanceID INTEGER PRIMARY KEY AUTOINCREMENT, class_ TEXT, teacher_ TEXT, status TEXT, late TEXT, leave TEXT);";
//        String abc="Absent";
//        Cursor data = db.rawQuery("SELECT class_, teacher_, status, late FROM attendance WHERE status ( Absent = "+"Absent"+");", null);
//
////        sqlite> SELECT * FROM COMPANY WHERE AGE NOT IN ( 25, 27 );
////        class_, status, teacher_, status


////        + " WHERE status ( Absent == "+ ab

//        String a,b,c,d,e,f;
//        a="8:30";
//        b="10:0";
//        c="11:30";
//        d="1:30";
//        e="3:0";
//        f="4:30";
        String stat= "Absent";
//       Cursor data = db.rawQuery("SELECT class_, teacher_, status, late FROM '" + TABLE_ATTENDANCE1+"'WHERE  late = '"+a+"' OR late = '"+b+"' OR late = '"+c+"' OR late = '"+d+"' OR late = '"+e+"' OR late = '"+f+"'" , null);

        Cursor data = db.rawQuery("SELECT class_, teacher_, status, late FROM '" + TABLE_ATTENDANCE1+"'WHERE  status = '"+stat+"'" , null);

        return data;
   }


    public boolean AddAttendance(String class_,String teacher_,String status_,String late_,String leave_) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(COL11, class_);
        contentValue.put(COL12, teacher_);
        contentValue.put(COL13, status_);
        contentValue.put(COL14, late_);
        contentValue.put(COL15, leave_);
        SQLiteDatabase db = this.getWritableDatabase();
        long result1 = db.insert(TABLE_ATTENDANCE1, null, contentValue);

        //if data as inserted incorrectly it will return -1
        if (result1 == -1) {
            return false;
        } else {
            return true;
        }
    }
}