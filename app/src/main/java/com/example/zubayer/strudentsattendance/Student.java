package com.example.zubayer.strudentsattendance;

/**
 * Created by Zubayer on 19-Dec-18.
 */

public class Student {
    String sname;
    String sid;
    String classes;
    String spass;



    public Student(String sname, String sid,String spass,String classes) {
        this.sname = sname;
        this.sid = sid;

        this.spass = spass;
        this.classes = classes;
    }

    public String getSname() { return sname; }

    public String getSid() {
        return sid;
    }
    public String getClasses() {
        return classes;
    }

    public String getspass() { return spass; }
}
