package com.practice.testlistviewcustomadapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by idrees on 08-Jun-17.
 */

public class display_attendance extends Activity {

    DatabaseHelperfor_insertschedule myDATABASE;
    ListView listview_DisplayAttendance;
    ArrayList<DisplayAttendanceContents> displayAttendanceContentsArrayList;
    DisplayAttendanceContents displayAttendanceContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_attendance);

        listview_DisplayAttendance = (ListView) findViewById(R.id.listview_display_Attendance);
        myDATABASE = new DatabaseHelperfor_insertschedule(this);
        displayAttendanceContentsArrayList = new ArrayList<>();

        Cursor data = myDATABASE.getAttendanceListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(display_attendance.this, " database is empty.", Toast.LENGTH_LONG).show();
        } else {

            while (data.moveToNext()) {
                displayAttendanceContents = new DisplayAttendanceContents(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5));
                displayAttendanceContentsArrayList.add(displayAttendanceContents);
            }
            custom_display_attendance adapter = new custom_display_attendance(this, R.layout.display_attendance_row_layout, displayAttendanceContentsArrayList);
            listview_DisplayAttendance.setAdapter(adapter);
        }
    }
}