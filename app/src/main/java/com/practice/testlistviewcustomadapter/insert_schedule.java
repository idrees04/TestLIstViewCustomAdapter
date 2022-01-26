package com.practice.testlistviewcustomadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import static com.practice.testlistviewcustomadapter.R.id.spinner_days;
//import static com.practice.testlistviewcustomadapter.R.id.spinner_teacher_name;

/**
 * Created by idrees on 15-Jun-17.
 */

public class insert_schedule extends Activity {
    String[] class_name = {"Select Class","Lt1", "Lt2", "Lt3", "Lt4", "Lt5", "Lt6", "Lt7", "Lt8", "Lt9", "Lt10", "Lt11", "Lt12", "Lt13", "Lt14", "Lt15", "Lt16", "Lt17", "Lab1", "Lab2", "Lab3", "Lab4", "Lab5"};
    String[] days={"Select Day","Monday","Tuesday","Wednesday","Thursday","Friday"};
    String[] starttime={"Sart Time","8:30","10:00","11:30","1:30","3:00","4:30"};
    String[] end_time={"End Time","10:00","11:30","1:00","3:00","4:30","5:30"};



    Context context;
    DatabaseHelperfor_insertschedule myDB;
    EditText etTeacher_name;
    Button btnADD, btnVIEW;
    Spinner spinner_class_room, spinner_start_time, spinner_end_time, spinner_day;
    ArrayAdapter<String> arrayAdapter, arrayAdapter_start_time,arrayAdapter_end_time,arrayAdapter_day;
    Button task1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_schedule_xml);

        myDB= new DatabaseHelperfor_insertschedule(this);
        context=this;
        spinner_class_room=(Spinner) findViewById(R.id.spClassRoom);
        spinner_start_time=(Spinner) findViewById(R.id.spStartTime);
        spinner_end_time=(Spinner) findViewById(R.id.spEndTime);
        spinner_day=(Spinner) findViewById(R.id.spDayOFWeak);
        btnVIEW=(Button) findViewById(R.id.btnView);
        btnADD=(Button) findViewById(R.id.btnAdd);
        etTeacher_name=(EditText)findViewById(R.id.etTeacherName);
        task1 = (Button) findViewById(R.id.btn_task);


        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, class_name);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_class_room.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, starttime);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_start_time.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, end_time);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_end_time.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, days);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_day.setAdapter(arrayAdapter);

        btnVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(insert_schedule.this,display_schedule.class);
                startActivity(intent);
            }
        });

        spinner_start_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int a = 0; a < position; a++)
                {
                    spinner_end_time.setSelection(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_end_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int a = 0; a < position; a++)
                {
                    spinner_start_time.setSelection(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextView selectedclass_room_text, selectedstart_time_text,selectedend_time_text,selectedday_text;
//                selectedclass_room_text=(TextView) findViewById(R.id.selected_classroom);
//                selectedstart_time_text=(TextView) findViewById(R.id.selected_starttime);
//                selectedend_time_text=(TextView) findViewById(R.id.selected_endtime);
//                selectedday_text=(TextView) findViewById(R.id.selected_day);
                String class_room_text, start_time_text,end_time_text,day_text,teacher_name_text;
                class_room_text=spinner_class_room.getSelectedItem().toString();
                start_time_text=spinner_start_time.getSelectedItem().toString();
                end_time_text=spinner_end_time.getSelectedItem().toString();
                day_text=spinner_day.getSelectedItem().toString();
                teacher_name_text=etTeacher_name.getText().toString();

                int pos1 =spinner_class_room.getSelectedItemPosition();
                int pos2=teacher_name_text.length();
                int pos3 =spinner_start_time.getSelectedItemPosition();
                int pos4 =spinner_end_time.getSelectedItemPosition();
                int pos5 =spinner_day.getSelectedItemPosition();

                if (pos1 != 0 && pos2 != 0 && pos3 != 0 && pos4 != 0&& pos5 != 0){
                    AddData(class_room_text,teacher_name_text,start_time_text, end_time_text,day_text);
                    spinner_class_room.setSelection(0);
                    spinner_start_time.setSelection(0);
                    spinner_end_time.setSelection(0);
                    spinner_day.setSelection(0);
                    etTeacher_name.setText("");

                }else{
                    Toast.makeText(insert_schedule.this,"You must put Teacher Name in the text field! or selected from spinner",Toast.LENGTH_LONG).show();
                }

//                selectedclass_room_text.setText(class_room_text);
//                selectedstart_time_text.setText(start_time_text);
//                selectedend_time_text.setText(end_time_text);
//                selectedday_text.setText(day_text);
            }
        });
//        task1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), task_show_absent_late_.class);
//                startActivity(intent);
//            }
//        });
    }

    public void AddData(String class_room_,String teacher_name_,String start_time_,String end_time_,String day_)
    {
        boolean insertData = myDB.AddData(class_room_,teacher_name_,start_time_,end_time_,day_);

        if(insertData==true){
            Toast.makeText(insert_schedule.this,"Successfully Entered Data!",Toast.LENGTH_LONG).show();
        }else
            {
            Toast.makeText(insert_schedule.this,"Something went wrong :(.",Toast.LENGTH_LONG).show();
        }
    }
}