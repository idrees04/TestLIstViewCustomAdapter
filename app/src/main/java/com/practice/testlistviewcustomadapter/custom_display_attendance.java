package com.practice.testlistviewcustomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import static android.R.attr.resource;

/**
 * Created by idrees on 08-Jun-17.
 */

public class custom_display_attendance extends ArrayAdapter<DisplayAttendanceContents> {

    private LayoutInflater minflater;
    private  ArrayList<DisplayAttendanceContents> displayAttendanceContentses;
    private int viewResourceID;

    public custom_display_attendance(Context context, int textViewResourceID,ArrayList<DisplayAttendanceContents>displayAttendanceContentses)
//    public custom_display_attendance(Context context, int resource ,ArrayList<DisplayAttendanceContents> objects)
 {
        super(context, textViewResourceID, displayAttendanceContentses);

        this.displayAttendanceContentses=displayAttendanceContentses;
        minflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceID = textViewResourceID;
    }
    public View getView(int position, View convertView, ViewGroup parents)
    {
            convertView=minflater.inflate(viewResourceID,null);

        DisplayAttendanceContents displayAttendance=displayAttendanceContentses.get(position);
        if(displayAttendance!=null)
        {
            TextView displayClass=(TextView) convertView.findViewById(R.id.txt_displayClassroom_);
            TextView displayTeacher=(TextView) convertView.findViewById(R.id.txt_displayTeachername_);
            TextView displayStatus=(TextView) convertView.findViewById(R.id.txt_displayStatus_);
            TextView displayStarttime=(TextView) convertView.findViewById(R.id.txt_displayStarttime_);
            TextView displayEndtime=(TextView) convertView.findViewById(R.id.txt_displayEndtime_);

            if(displayClass!=null)
            {
                displayClass.setText(displayAttendance.getClass_());
            }
            if(displayTeacher!=null)
            {
             displayTeacher.setText(displayAttendance.getTeacher_());
            }
            if(displayStatus!=null)
            {
                displayStatus.setText(displayAttendance.getStatus_());
            }
            if (displayStarttime!=null)
            {
                displayStarttime.setText(displayAttendance.getLate_());
            }
            if(displayEndtime!=null)
            {
                displayEndtime.setText(displayAttendance.getLeave_());
            }

        }
        return convertView;
    }
}