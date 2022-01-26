package com.practice.testlistviewcustomadapter;

/**
 * Created by idrees on 20-Jun-17.
 */

public class ScheduleContents {
    private String class_room;
    private String teacher_name;
    private String start_time;
    private String end_time;
    private String status;

    public ScheduleContents(String class_room_,String teacher_name_,String start_time_,String end_time_,String status_)
    {
        class_room=class_room_;
        teacher_name=teacher_name_;
        start_time=start_time_;
        end_time=end_time_;
        status=status_;
    }

    public String getClass_room() {
        return class_room;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getStatus() {
        return status;
    }
}
