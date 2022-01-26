package com.practice.testlistviewcustomadapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by idrees on 07-Jul-17.
 */

public class task_show_absent_late_ extends Activity {

    String arrLt = "";
    String[] Lt;
    String arrTeacher = "";
    String[] Teacher;
    String arrStatusPresent = "";
    String arrStatusAbsentt = "";

    String[] staus;
    String arrLate = "";
    String[] late;

    task_show_absent_late_Custom task_show_absent_late_custom;
    DatabaseHelperfor_insertschedule myDATABASE_task;
    ListView listviewAbsent_late;
    ArrayList<com.practice.testlistviewcustomadapter.task_Show_Absent_Late_Contents> task_show_absent_late_contents_arrayList;
    com.practice.testlistviewcustomadapter.task_Show_Absent_Late_Contents task_show_absent_late_contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_show_absent_late_);

        listviewAbsent_late = (ListView) findViewById(R.id.listviewAbsent_Late_);
        myDATABASE_task = new DatabaseHelperfor_insertschedule(this);
        task_show_absent_late_contents_arrayList = new ArrayList<>();

        Cursor data = myDATABASE_task.getAbsentLateListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(task_show_absent_late_.this, " database is empty.", Toast.LENGTH_LONG).show();
        } else {

            while (data.moveToNext()) {
                String class1 = data.getString(data.getColumnIndex("class_"));
                String teacher1 = data.getString(data.getColumnIndex("teacher_"));
                String status1 = data.getString(data.getColumnIndex("status"));
                String late1 = data.getString(data.getColumnIndex("late"));

                arrLate=arrLate+late1+",";
                arrStatusAbsentt = arrStatusAbsentt + status1 + ",";
                arrLt=arrLt+class1+",";
                arrTeacher=arrTeacher+teacher1+",";
            }
            Lt=arrLt.split(",");
            Teacher=arrTeacher.split(",");
            staus=arrStatusAbsentt.split(",");
            late=arrLate.split(",");
            task_show_absent_late_custom = new task_show_absent_late_Custom(this, Lt, Teacher,staus,late);
            listviewAbsent_late.setAdapter(task_show_absent_late_custom);
        }
    }
}