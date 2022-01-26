package com.practice.testlistviewcustomadapter;

/**
 * Created by idrees on 05-Jul-17.
 */

public class DisplayAttendanceContents {
    private String class_;
    private String teacher_;
    private String status_;
    private String late_;
    private String leave_;

    public DisplayAttendanceContents(String classis, String teacher, String status, String late, String leave)
    {
        class_=classis;
        teacher_=teacher;
        status_=status;
        late_=late;
        leave_=leave;
    }

    public String getClass_() {
        return class_;
    }

    public String getTeacher_() {
        return teacher_;
    }

    public String getStatus_() {
        return status_;
    }

    public String getLate_() {
        return late_;
    }

    public String getLeave_() {
        return leave_;
    }

}