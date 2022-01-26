package com.practice.testlistviewcustomadapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by idrees on 07-Jun-17.
 */

public class teacher_attendance extends Activity {

    String arr9 = "";
    String[] Lt;
    String arr8 = "";
    String[] Teacher;

    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] starttime = {"8:30", "10:00", "11:30", "1:30", "3:00", "4:30"};
    String[] end_time = {"10:00", "11:30", "1: 00", "3: 00", "4:30", "5:30"};
    ArrayAdapter<String> arrayAdapter;
    Spinner spinnerClass_StartTime, spinnerClass_EndTime, spinnerClassDays;
    String selected_StartTime, selected_EndTime, selected_ClassDays;
    Button btnShowClass;
    ListView lv;

    DatabaseHelperfor_insertschedule MydDB;
    Cursor data, distinctGetListContents;
    CustomAdapter adapter;
    int numrows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//            MyDb2=new DatabaseHelperfor_insertschedule(this);
//            Cursor data=MyDb2.getListContents();
        lv = (ListView) findViewById(R.id.listview1);
        spinnerClassDays = (Spinner) findViewById(R.id.spinner_Class_days);
        spinnerClass_StartTime = (Spinner) findViewById(R.id.spinner_Class_StartTime);
        spinnerClass_EndTime = (Spinner) findViewById(R.id.spinner_Class_EndTime);
        btnShowClass=(Button) findViewById(R.id.btn_showTimeTable);

        arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, days);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassDays.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, starttime);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass_StartTime.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, end_time);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass_EndTime.setAdapter(arrayAdapter);

        selected_EndTime = spinnerClass_EndTime.getSelectedItem().toString();
        selected_StartTime = spinnerClass_StartTime.getSelectedItem().toString();
        selected_ClassDays=spinnerClassDays.getSelectedItem().toString();
//        int pos3 = spinnerClass_EndTime.getSelectedItemPosition();
//        int pos2 = spinnerClass_StartTime.getSelectedItemPosition();
//        int pos1 = spinnerClassDays.getSelectedItemPosition();
//            if(pos1==0&&pos2==0&&pos3==0)
//            {
//                Toast.makeText(teacher_attendance.this,"You must put something in the text field! or not selected from spinner",Toast.LENGTH_LONG).show();
//            }

        spinnerClass_StartTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int a = 0; a < position; a++) {
                    spinnerClass_EndTime.setSelection(position);
                    selected_EndTime = spinnerClass_EndTime.getSelectedItem().toString();
                    selected_StartTime = spinnerClass_StartTime.getSelectedItem().toString();
//                        Toast.makeText(teacher_attendance.this,"Start TIme "+selected_StartTime+" End TIme "+selected_EndTime+" is Selected",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerClass_EndTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int a = 0; a < position; a++) {
                    spinnerClass_StartTime.setSelection(position);
                    selected_EndTime = spinnerClass_EndTime.getSelectedItem().toString();
                    selected_StartTime = spinnerClass_StartTime.getSelectedItem().toString();
//                        Toast.makeText(teacher_attendance.this,"Start TIme "+selected_StartTime+" End TIme "+selected_EndTime+" is Selected",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerClassDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int a = 0; a < position; a++) {
                    selected_ClassDays = spinnerClassDays.getSelectedItem().toString();
//                        Toast.makeText(teacher_attendance.this,selected_ClassDays+" is Selected",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        MydDB = new DatabaseHelperfor_insertschedule(this);
        distinctGetListContents = MydDB.distinctGetListContents(selected_ClassDays, selected_StartTime, selected_EndTime);
        numrows = distinctGetListContents.getCount();
        if (numrows == 0) {
            Toast.makeText(teacher_attendance.this, " Database is Empty ", Toast.LENGTH_SHORT).show();
            Lt=new String['e'];
            Teacher=new String['e'];
        } else {
            while (distinctGetListContents.moveToNext()) {
                String lt = distinctGetListContents.getString(distinctGetListContents.getColumnIndex("class_room"));
                arr9 = arr9 + lt + ",";
                String teacher = distinctGetListContents.getString(distinctGetListContents.getColumnIndex("teacher_name"));
                arr8 = arr8 + teacher + ",";
            }
            Lt = arr9.split(",");
            Teacher = arr8.split(",");
                }

//        final DataBaseHelper dataBaseHelper1=new DataBaseHelper(c.getTag());

//        if(Lt==null)
//        {
//            Toast.makeText(teacher_attendance.this,"class and Teacher is Null ",Toast.LENGTH_SHORT).show();
//        }
//        else {
//            adapter = new CustomAdapter(this, Lt, Teacher);
//            adapter.notifyDataSetChanged();
//
//            lv.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//        }
        adapter = new CustomAdapter(this, Lt, Teacher);
        lv.setAdapter(adapter);

//        btnShowClass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                    lv.setAdapter(adapter);
//            }
//        });
    }
}