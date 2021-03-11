package com.example.zubayer.strudentsattendance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class student_attendance_sheet extends AppCompatActivity {
  public static    int TOC=0,NOP=0,NOA=0;
    Toolbar mToolbar;
    TextView t;
    String p1,p2,p3,p4,p5,p6,p7,p8;
    double average;
    String student_id;
    ArrayList dates = new ArrayList<>();;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_sheet);
        mToolbar=(Toolbar)findViewById(R.id.studentsheet);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        t=(TextView) findViewById(R.id.textView3);

        listView = (ListView) findViewById(R.id.list);
        Bundle bundle = getIntent().getExtras();
        student_id = bundle.getString("sid");
        t.setText(student_id);

        dates.clear();
        dates.add(" Date               "+"NM      "+"DBMS      "+"CN       "+"SAD       "+ "TOC");

        dbAttendance = ref.child("attendance");
        dbAttendance.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {


                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    p1 = dsp.child(student_id).child("NM").getValue().toString().substring(0, 1);
                    p2 = dsp.child(student_id).child("DBMS").getValue().toString().substring(0, 1);
                    p3 = dsp.child(student_id).child("CN").getValue().toString().substring(0, 1);
                    p4 = dsp.child(student_id).child("SAD").getValue().toString().substring(0, 1);
                    p5 = dsp.child(student_id).child("TOC").getValue().toString().substring(0, 1);
                  dates.add(dsp.getKey()+ "     "+p1 + "           "+ p2 + "             "+ p3 + "            "+ p4 + "           "+ p5); //add result into array list


                    if (p1.equalsIgnoreCase("P")) {

                        NOP=NOP+1;
                        TOC=TOC+1;
                    }
                    if (p1.equalsIgnoreCase("A")) {

                        TOC=TOC+1;
                    }
                   if (p2.equalsIgnoreCase("P")) {

                          NOP=NOP+1;
                         TOC=TOC+1;
                    }
                    if (p2.equalsIgnoreCase("A")) {

                        TOC=TOC+1;
                   }
                   if (p3.equalsIgnoreCase("P")) {

                        NOP=NOP+1;
                        TOC=TOC+1;
                    }
                    if (p3.equalsIgnoreCase("A")) {

                        TOC=TOC+1;
                    }
                       if (p4.equalsIgnoreCase("P")) {

                           NOP=NOP+1;
                           TOC=TOC+1;
                    }
                       if (p4.equalsIgnoreCase("A")) {

                           TOC=TOC+1;
                    }
                    if (p5.equalsIgnoreCase("P")) {

                        NOP=NOP+1;
                        TOC=TOC+1;
                    }
                      if (p5.equalsIgnoreCase("A")) {

                          TOC=TOC+1;
                    }
//                    if (p6.equalsIgnoreCase("P")) {
//
//                        NOP=NOP+1;
//                        TOC=TOC+1;
//                    }
//                     if (p6.equalsIgnoreCase("A")) {
//
//                         TOC=TOC+1;
//                    }
//                     if (p7.equalsIgnoreCase("P")) {
//
//                         NOP=NOP+1;
//                         TOC=TOC+1;
//                   }
//                    if (p7.equalsIgnoreCase("A")) {
//
//                        TOC=TOC+1;
//                    }
//                     if (p8.equalsIgnoreCase("P")) {
//
//                         NOP=NOP+1;
//                         TOC=TOC+1;
//                    }
//                    if (p8.equalsIgnoreCase("A")) {
//
//                        TOC=TOC+1;
//                    }




                }

                }
                catch (Exception e){e.printStackTrace();}
                list(dates,NOP,TOC,NOA);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void list(ArrayList studentlist,int NOP,int TOC,int NOA){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentlist);
        DecimalFormat df=new DecimalFormat("0.00");


        listView.setAdapter(adapter);
        try {

           average =((NOP*100.0)/TOC);

            Log.i("msg", String.valueOf(p1));
            Log.i("msg2", String.valueOf(p2));
            Log.i("msg3", String.valueOf(p3));
            Log.i("msg4", String.valueOf(p4));
            Log.i("msg5", String.valueOf(p5));
            Log.i("msg6", String.valueOf(p6));
            Log.i("msg7", String.valueOf(p7));
            Log.i("msg8", String.valueOf(p8));
            Log.i("msg1", String.valueOf(NOP));
            Log.i("msg1", String.valueOf(NOP));
            t.setText("Your Attendance is :"+df.format(average)+"%");

            if(average>=60)
                t.setTextColor(Color.GREEN);
            if(average<60)
                t.setTextColor(Color.RED);
        }
        catch (Exception e){e.printStackTrace();}


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
