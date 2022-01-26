package com.practice.testlistviewcustomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by idrees on 20-Jun-17.
 */

public class column_ListAdapter_display_schedule extends ArrayAdapter<ScheduleContents> {
    private LayoutInflater inflater;
    private ArrayList<ScheduleContents> scheduleContentses;
    private int mViewResourceId;

    public column_ListAdapter_display_schedule(Context context, int textViewResourceId, ArrayList<ScheduleContents> scheduleContentses) {
        super(context, textViewResourceId, scheduleContentses);
        this.scheduleContentses = scheduleContentses;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = inflater.inflate(mViewResourceId, null);
        ScheduleContents scheduleContents = scheduleContentses.get(position);

        if (scheduleContents != null) {
            TextView classroom = (TextView) convertView.findViewById(R.id.txt_classroomListAdapter);
            TextView teachername = (TextView) convertView.findViewById(R.id.txt_teachernameListAdapter);
            TextView starttime = (TextView) convertView.findViewById(R.id.txt_starttimeListAdapter);
            TextView endtime = (TextView) convertView.findViewById(R.id.txt_endtimeListAdapter);
            TextView status = (TextView) convertView.findViewById(R.id.txt_displayStatusListAdapter);
            if (classroom != null) {
                classroom.setText(scheduleContents.getClass_room());
            }
            if (teachername != null) {
                teachername.setText(scheduleContents.getTeacher_name());
            }
            if (starttime != null) {
                starttime.setText(scheduleContents.getStart_time());
            }if (endtime != null) {
                endtime.setText(scheduleContents.getEnd_time());
            }
            if (status != null) {
                status.setText(scheduleContents.getStatus());
            }
        }
        return convertView;
    }
}
