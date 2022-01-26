package com.practice.testlistviewcustomadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_ViewAttendance, btnAttendance, btn_InsertSchedule, btn_ViewSchedule, btntask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn_ViewAttendance = (Button) findViewById(R.id.btn_View_Attendance);
        btnAttendance = (Button) findViewById(R.id.btn_Attendance);
        btn_InsertSchedule = (Button) findViewById(R.id.btn_Insert_Schedule);
        btn_ViewSchedule = (Button) findViewById(R.id.btn_View_Schedule);
        btntask = (Button) findViewById(R.id.btn_task);

        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), teacher_attendance.class);
                startActivity(intent);
            }
        });
        btn_InsertSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), insert_schedule.class);
                startActivity(intent);
            }
        });
        btn_ViewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), display_attendance.class);
                startActivity(intent);
            }
        });
        btn_ViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), display_schedule.class);
                startActivity(intent);
            }
        });

        btntask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), task_show_absent_late_.class);
                startActivity(intent);
            }
        });
    }
}