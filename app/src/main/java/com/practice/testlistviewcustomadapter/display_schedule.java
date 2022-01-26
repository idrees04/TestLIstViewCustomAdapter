package com.practice.testlistviewcustomadapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by idrees on 20-Jun-17.
 */

public class display_schedule extends Activity {

    DatabaseHelperfor_insertschedule myDB;
    ArrayList<ScheduleContents> scheduleContentsArrayList;
    ScheduleContents scheduleContents;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_schedule);

        myDB = new DatabaseHelperfor_insertschedule(this);
        scheduleContentsArrayList = new ArrayList<>();

        Cursor data = myDB.getListContentsViewSchedule();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(display_schedule.this, " database is empty.", Toast.LENGTH_LONG).show();
        } else {

            while (data.moveToNext()) {
                scheduleContents = new ScheduleContents(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5));
//                To run this application on emulator uncomment this upper line
//                scheduleContents = new ScheduleContents(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4));
//                To run this application on mobile uncomment this upper line

                scheduleContentsArrayList.add(scheduleContents);

            }
            column_ListAdapter_display_schedule adapter = new column_ListAdapter_display_schedule(this, R.layout.list_adapter_for_display_schedule, scheduleContentsArrayList);
            listView = (ListView) findViewById(R.id.listview_display_schedule);
            listView.setAdapter(adapter);
        }
    }
}
