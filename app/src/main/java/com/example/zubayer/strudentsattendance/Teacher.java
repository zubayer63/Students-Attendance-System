package com.example.zubayer.strudentsattendance;

/**
 * Created by Zubayer on 19-Dec-18.
 */

public class Teacher {

    String tname;
    String tid;
    String subject;
    String classes;
    String tpass;



    public Teacher(String tname, String tid, String subject, String tpass, String classes) {
        this.tname = tname;
        this.tid = tid;
        this.subject = subject;

        this.tpass = tpass;
        this.classes = classes;
    }

    public String getTname() {
        return tname;
    }

    public String getTid() {
        return tid;
    }

    public String getSubject() {
        return subject;
    }

    public String gettpass() {
        return tpass;
    }

    public String getClasses() {
        return classes;
    }
}
