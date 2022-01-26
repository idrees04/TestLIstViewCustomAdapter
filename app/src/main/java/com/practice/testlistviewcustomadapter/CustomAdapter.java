package com.practice.testlistviewcustomadapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;


public class CustomAdapter extends BaseAdapter {
    //    DatabaseHelperfor_insertschedule databaseHelperfor_insertschedule;
    private static LayoutInflater inflater = null;
    String[] class_room;
    Context context;
    String[] teacher_name;

    //    ArrayAdapter<String> arrayAdapter1;
    Calendar c = Calendar.getInstance();

    public CustomAdapter(teacher_attendance mainActivity, String[] arr, String[] arr1) {
        // TODO Auto-generated constructor stub
        class_room = arr;
        teacher_name = arr1;
        context = mainActivity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.row_layout, null);
        holder.radioButton_Group_PresentAbsent = (RadioGroup) rowView.findViewById(R.id.radioButton_group_PresentAbsent);
        holder.radioButtonPresent = (RadioButton) rowView.findViewById(R.id.radioButton_present);
        holder.txtlt = (TextView) rowView.findViewById(R.id.text_Lt);
        holder.txtteacher = (TextView) rowView.findViewById(R.id.text_teacher_name);
        holder.radioButtonAbsent = (RadioButton) rowView.findViewById(R.id.radioButton_absent);
        holder.txtStart_time_Picker = (TextView) rowView.findViewById(R.id.txtStartTimePicker);
        holder.txtStart_time_Picker.setFocusable(false);
        holder.txtEnd_time_Picker = (TextView) rowView.findViewById(R.id.txtEndTimePicker);
        holder.txtEnd_time_Picker.setFocusable(false);
        holder.txtlt.setText(class_room[position]);
        holder.txtteacher.setText(teacher_name[position]);
        holder.submitAttendance = (ImageButton) rowView.findViewById(R.id.submit_Attendance);
        holder.submitAttendance.setVisibility(View.VISIBLE);
//        holder.spinner_teacher_name = (Spinner) rowView.findViewById(R.id.spinner_teacher_name);
//        arrayAdapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, teacher_name);
//        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        holder.spinner_teacher_name.setAdapter(arrayAdapter1);
//        ArrayAdapter<String> arrayAdapter2;
//        holder.spinnerDays=(Spinner) rowView.findViewById(R.id.spinner_days);
//        arrayAdapter2=new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item,days);
//        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        holder.spinnerDays.setAdapter(arrayAdapter2);

//        final DataBaseHelper dataBaseHelper1=new DataBaseHelper(this.context);

        holder.submitAttendance.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String class_, teacherName, status = null, startTime, endTime;
                DatabaseHelperfor_insertschedule databaseHelperfor_insertschedule = new DatabaseHelperfor_insertschedule(v.getContext());
                startTime = holder.txtStart_time_Picker.getText().toString();

                endTime = holder.txtEnd_time_Picker.getText().toString();
                teacherName = holder.txtteacher.getText().toString();
                class_ = holder.txtlt.getText().toString();

                if (holder.radioButtonPresent.isChecked()) {
                    if (!startTime.equals("LATE")) {
                        if (!endTime.equals("LEAVE")) {
                            status = holder.radioButtonPresent.getText().toString();
                            Toast.makeText(rowView.getContext(), teacher_name[position] + " is " + status, Toast.LENGTH_SHORT).show();
                            databaseHelperfor_insertschedule.AddAttendance(class_, teacherName, status, startTime, endTime);
                        } else {
                            Toast.makeText(rowView.getContext(), " LEVE TIME IS not SET", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(rowView.getContext(), " LATE TIME IS not SET", Toast.LENGTH_SHORT).show();
                    }
                } else if (holder.radioButtonAbsent.isChecked()) {
                    status = holder.radioButtonAbsent.getText().toString();
//                    endTime.equals ("");
//                    holder.txtStart_time_Picker.setText("");
//                    holder.txtEnd_time_Picker.setText("");
                    startTime = holder.txtStart_time_Picker.getText().toString();
                    endTime = holder.txtEnd_time_Picker.getText().toString();

                    databaseHelperfor_insertschedule.AddAttendance(class_, teacherName, status, startTime, endTime);
                    Toast.makeText(rowView.getContext(), teacher_name[position] + " is " + status, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(rowView.getContext(), "Teacher Status is not Mark", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holder.dataBaseHelper =new DataBaseHelper(this.context);

//        holder.spinner_teacher_name.setVisibility(View.INVISIBLE);
        holder.txtteacher.setVisibility(View.VISIBLE);
//        holder.spinner_teacher_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                for (int a = 0; a < position; a++) {
//                    holder.txtteacher.setText(teacher_name[position]);
////                    Toast.makeText(rowView.getContext(), teacher_name[position], Toast.LENGTH_SHORT).show();
//                    holder.spinner_teacher_name.setVisibility(View.INVISIBLE);
//                    holder.txtteacher.setVisibility(View.VISIBLE);
//                    holder.radioButtonPresent.setChecked(true);
//                    holder.txtEnd_time_Picker.setVisibility(View.VISIBLE);
//                    holder.txtStart_time_Picker.setVisibility(View.VISIBLE);
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });


        holder.txtStart_time_Picker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Toast.makeText(context, "You Clicked "+class_room[position], Toast.LENGTH_SHORT).show();

                if (holder.radioButtonPresent.isChecked()) {
                    TimePickerDialog.OnTimeSetListener tim = new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // TODO Auto-generated method stub


                            holder.txtStart_time_Picker.setText(hourOfDay + " : " + minute);

                        }
                    };
                    new TimePickerDialog(context, tim, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();

                    holder.txtEnd_time_Picker.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(rowView.getContext(), "Please Mark Present", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.txtEnd_time_Picker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
//                Toast.makeText(context, "You Clicked "+class_room[position], Toast.LENGTH_SHORT).show();
                if (holder.txtStart_time_Picker.getText()== "LATE") {
                    Toast.makeText(rowView.getContext(), "Please Set Start Time First ", Toast.LENGTH_SHORT).show();
                }
                else {



                    TimePickerDialog.OnTimeSetListener tim = new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // TODO Auto-generated method stub



                                holder.txtStart_time_Picker.getText();
                                holder.txtEnd_time_Picker.setText(hourOfDay + " : " + minute);

//                                Toast.makeText(rowView.getContext(), "Please Set First ", Toast.LENGTH_SHORT).show();

                        }
                    };
                    new TimePickerDialog(context, tim, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();

                }

            }
        });

        holder.radioButtonAbsent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.radioButtonAbsent.isChecked()) {
                    holder.txtEnd_time_Picker.setVisibility(View.INVISIBLE);
                    holder.txtStart_time_Picker.setVisibility(View.INVISIBLE);
//                    holder.spinner_teacher_name.setVisibility(View.VISIBLE);
//                    holder.txtteacher.setVisibility(View.INVISIBLE);
                    holder.txtEnd_time_Picker.setText("NULL");
                    holder.txtStart_time_Picker.setText("NULL");
                } else if (holder.radioButtonPresent.isChecked()) {
                    holder.txtEnd_time_Picker.setVisibility(View.VISIBLE);
                    holder.txtStart_time_Picker.setVisibility(View.VISIBLE);
//                    holder.spinner_teacher_name.setVisibility(View.INVISIBLE);
                    holder.txtteacher.setVisibility(View.VISIBLE);
                    holder.txtEnd_time_Picker.setText("LEAVE");
                    holder.txtStart_time_Picker.setText("LATE");
                }
            }
        });
        holder.radioButtonPresent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.radioButtonPresent.isChecked()) {
                    holder.txtStart_time_Picker.setVisibility(View.VISIBLE);
//                    holder.spinner_teacher_name.setVisibility(View.INVISIBLE);
                    holder.txtteacher.setVisibility(View.VISIBLE);
                    holder.txtEnd_time_Picker.setText("LEAVE");
                    holder.txtStart_time_Picker.setText("LATE");
                } else if (holder.radioButtonAbsent.isChecked()) {
                    holder.txtStart_time_Picker.setVisibility(View.INVISIBLE);
                    holder.txtEnd_time_Picker.setVisibility(View.INVISIBLE);
//                    holder.spinner_teacher_name.setVisibility(View.VISIBLE);
                    holder.txtteacher.setVisibility(View.INVISIBLE);
                    holder.txtEnd_time_Picker.setText("NULL");
                    holder.txtStart_time_Picker.setText("NULL");
                }
            }
        });
        return rowView;
    }

    public class Holder {
        TextView txtlt, txtteacher;
        RadioButton radioButtonPresent, radioButtonAbsent;
        TextView txtStart_time_Picker, txtEnd_time_Picker;
        Spinner spinner_teacher_name;
        ImageButton submitAttendance;
        RadioGroup radioButton_Group_PresentAbsent;
    }
}