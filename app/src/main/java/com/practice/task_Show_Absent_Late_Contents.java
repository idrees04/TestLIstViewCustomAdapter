package com.practice.testlistviewcustomadapter;

/**
 * Created by idrees on 07-Jul-17.
 */

public class task_Show_Absent_Late_Contents {

    private String class_;
    private String teacher_;
    private String status_;
    private String late_;
//    private String leave_;

    public task_Show_Absent_Late_Contents(String classis, String teacher, String status, String late)
    {
        class_=classis;
        teacher_=teacher;
        status_=status;
        late_=late;
//        leave_=leave;
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

//    public String getLeave_() {
//        return leave_;
//    }

}
