package com.example.zubayer.strudentsattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addteacher extends AppCompatActivity {

    EditText Tname,Tid,subject,tpassword;
    String tname,tid,sub,classname,tpass;
    Spinner classes;
    Button addButton;
    DatabaseReference databaseTeacher;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteacher);

        databaseTeacher= FirebaseDatabase.getInstance().getReference("Teacher");

        Tname= (EditText) findViewById(R.id.editText1);
        Tid= (EditText) findViewById(R.id.editText3);
        subject= (EditText) findViewById(R.id.editText4);
        tpassword= (EditText) findViewById(R.id.editText5);
        classes= (Spinner) findViewById(R.id.spinner3);
        addButton= (Button) findViewById(R.id.button9);
        mToolbar= (Toolbar) findViewById(R.id.ftoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Register as Teacher");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tname = Tname.getText().toString();
                tid = Tid.getText().toString();
                sub = subject.getText().toString();
                classname = classes.getSelectedItem().toString();
                tpass = tpassword.getText().toString();
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                if (!(TextUtils.isEmpty(Tid.getText().toString()))) {
                    // String id = databaseTeacher.push().getKey();
                    Teacher teacher =new Teacher(tname ,tid ,sub ,tpass,classname);
                    databaseTeacher.child(tid).setValue(teacher);
                    Toast.makeText(getApplicationContext(),"Teacher added successfully", Toast.LENGTH_LONG).show();
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(),"fields cannot be empty", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
