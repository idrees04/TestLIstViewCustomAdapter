package com.practice.testlistviewcustomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by idrees on 07-Jul-17.
 */

public class task_show_absent_late_Custom extends BaseAdapter {

//    private LayoutInflater minflater;
//    private ArrayList<task_Show_Absent_Late_Contents> task_show_absent_late_contentses;
//    private int viewResourceID;

    private static LayoutInflater minflater = null;

    String[] class_room;
    Context context;
    String[] teacher_name;
    String[] status_;
    String[] late_;

//
//    ArrayAdapter<String> arrayAdapter1;
//    Calendar c = Calendar.getInstance();
//
//    public CustomAdapter(teacher_attendance mainActivity, String[] arr, String[] arr1) {
//        // TODO Auto-generated constructor stub
//        class_room = arr;
//        teacher_name = arr1;
//        context = mainActivity;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    public task_show_absent_late_Custom(task_show_absent_late_ context12, String[] class_room1, String[] teacher_name1, String[] status1, String[] late1) {
        class_room = class_room1;
        teacher_name = teacher_name1;
        status_ = status1;
        late_ = late1;
        context = context12;

//        this.task_show_absent_late_contentses=task_show_absent_late_contentses;
        minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        viewResourceID = textViewResourceID;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return class_room.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        final View rowView;

        rowView = minflater.inflate(R.layout.task_show_absent_late_rowlayout, null);
//
//        TextView class_ = (TextView) rowView.findViewById(R.id.txtClass_task_Row);
//        TextView teacher = (TextView) rowView.findViewById(R.id.txtTeacher_task_row);
//        TextView status = (TextView) rowView.findViewById(R.id.txtStatus_task_row);
//        TextView late = (TextView) rowView.findViewById(R.id.txtLate_task_row);

        holder.class_ = (TextView) rowView.findViewById(R.id.txtClass_task_Row);
        holder.teacher = (TextView) rowView.findViewById(R.id.txtTeacher_task_row);
        holder.status = (TextView) rowView.findViewById(R.id.txtStatus_task_row);
        holder.late = (TextView) rowView.findViewById(R.id.txtLate_task_row);
//            TextView displayEndtime=(TextView) convertView.findViewById(R.id.txtLeave_task);


//        class_.setText(class_room[position]);
//        teacher.setText(teacher_name[position]);
//        status.setText(status_[position]);
//        late.setText(late_[position]);

        holder.class_.setText(class_room[position]);
        holder.teacher.setText(teacher_name[position]);
        holder.status.setText(status_[position]);
        holder.late.setText(late_[position]);

        return rowView;
    }

    public class Holder {
        TextView class_, teacher, status, late;
    }
}
