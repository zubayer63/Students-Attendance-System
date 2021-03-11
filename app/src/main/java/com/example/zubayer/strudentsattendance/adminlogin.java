package com.example.zubayer.strudentsattendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class adminlogin extends AppCompatActivity {
    DatabaseReference ref,dbStudent,dbAdmin,dbAttendance;
    Toolbar mToolbar;

    ArrayList studentList=new ArrayList<>();

    String date=new SimpleDateFormat("dd-MM-yyy").format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        mToolbar= (Toolbar) findViewById(R.id.ftoolbar);
        mToolbar.setTitle("Admin Dashboard : "+"("+date+")");
        ref= FirebaseDatabase.getInstance().getReference();
        dbStudent=ref.child("Student");
        dbAttendance=ref.child("Attendance");
        dbAdmin=ref.child("Admin");
    }




    public void AddStudentButton(View view) {
        Intent intent=new Intent(adminlogin.this,addstudent.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"On Progress",Toast.LENGTH_SHORT).show();

    }

    public void AddTeacherButton(View view) {
        Intent intent=new Intent(this,addteacher.class);
        startActivity(intent);
        Toast.makeText(this, "On progress", Toast.LENGTH_SHORT).show();
    }


    public void attendanceRecord(View view) {

        Intent intent=new Intent(adminlogin.this,adminAttandanceSheet.class);
        startActivity(intent);

    }


    public void CreateAttendance(View view) {



    dbStudent.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


           String sid, p1="-",p2="-",p3="-",p4="-",p5="-";
            AttandanceSheet a=new AttandanceSheet(p1,p2,p3,p4,p5);



           // String sid,NM="-",DBMS="-",CN="-",SAD="-",TOC="-";
            //Attandanc a=new Attandanc(NM,DBMS,CN,SAD,TOC);
            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                sid = dsp.child("sid").getValue().toString();

                dbAttendance.child(date).child(sid).setValue(a);




            }
            Toast.makeText(getApplicationContext(), "successfully created " + date + " db", Toast.LENGTH_LONG).show();


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

            Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();

        }
    });

    }




    public void changepassword(View view) {
        dbAdmin=ref.child("Admin");

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Type your new password");
        final LayoutInflater inflater = this.getLayoutInflater();
        View add_menu_layout = inflater.inflate(R.layout.changepassword, null);
        final EditText password=(EditText)add_menu_layout.findViewById(R.id.newpassword);
        alertDialog.setView(add_menu_layout);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(password.getText().toString()))
                {
                    dbAdmin.child("Admin").setValue(password.getText().toString());
                    Toast.makeText(adminlogin.this, "Successfully Changed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(adminlogin.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
                }



            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();


    }










    public void logout(View view) {
        Intent intent=new Intent(adminlogin.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
