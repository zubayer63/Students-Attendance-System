package com.example.zubayer.strudentsattendance;

/**
 * Created by Zubayer on 01-Apr-19.
 */

public class SingleItemModel {
    public SingleItemModel() {
    }

    String attendence;
    public SingleItemModel(String attendence) {
        this.attendence = attendence;
    }



    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }
}