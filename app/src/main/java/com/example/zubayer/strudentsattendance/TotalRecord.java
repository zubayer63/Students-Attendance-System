package com.example.zubayer.strudentsattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TotalRecord extends AppCompatActivity {
    ListView listView;
    String teacher_id,class_selected;
int  count=0;
    TextView Totalclass;
    EditText date;
    ArrayList Userlist = new ArrayList<>();
    ArrayList StudentRolllist = new ArrayList<>();
    ArrayList StudentAttendencelist = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    DatabaseReference dbStudent;
    String required_date;
   // RecyclerView recyclerView;
    ArrayList<SectionDataModel> allSampleData;
    final ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_record);
        listView = (ListView) findViewById(R.id.Alist);
        Totalclass=(TextView)findViewById(R.id.tclass);
      //  recyclerView= (RecyclerView) findViewById(R.id.myRecyclerview);
      /*  LinearLayoutManager linearLayout=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayout);*/
        Bundle bundle1 = getIntent().getExtras();
        class_selected = bundle1.getString("class_selected");
        teacher_id = bundle1.getString("tid");


        Userlist.clear();
        Userlist.add("SID");
        dbStudent = ref.child("Student");
        dbStudent.orderByChild("classes").equalTo(class_selected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Userlist.add(dsp.child("sid").getValue().toString()); //add result into array list
                }
                display_list(Userlist);
               // list(Userlist);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });

    }





    public void display_list(final ArrayList userlist) {

        //StudentRolllist.clear();
       // required_date = date.getText().toString();
        dbAttendance = ref.child("attendance");
        dbAttendance.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot dsp2 : dataSnapshot.getChildren()) {
                 ;
                    Log.i("vakye", dsp2.getValue().toString());
                    Log.i("vaky2", dsp2.getKey().toString());

                    //Userlist.add(dsp2.getValue().toString()); //add result into array list
                    count++;
                    //StudentRolllist.add("SID ");
                    StudentAttendencelist.add(dsp2.getKey());
                    for (Object sid : userlist) {
                        dbAttendance.child(dsp2.getKey()).child(sid.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Result will be holded Here

                                for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                                    String p1 = dsp.getValue().toString();
                                    Log.i("zzz",p1.toString());

                                    if((p1.equals("A / "+teacher_id))||(p1.equals("P / "+teacher_id))){
                                      //  StudentRolllist.add(dataSnapshot.getKey().toString() );
                                        StudentAttendencelist.add(dataSnapshot.getKey() + "      " +p1.substring(0,1));
Log.i("xxx", String.valueOf(StudentAttendencelist.size()));
                                    }
                                }

                               // list(StudentRolllist);
                               list(StudentAttendencelist);

                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                            }

                        });


                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void list(ArrayList studentlist){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentlist);

        listView.setAdapter(adapter);
        Totalclass.setText("Total Class : "+count+"");


    }
   /* public void list2(ArrayList studentlist){
    *//*    final SectionDataModel dm = new SectionDataModel();
      *//**//*  for (int j = 0; j <= 5; j++) {
            studentlist.add(new SingleItemModel("Item " + j));
        }
*//**//*
      Log.i("aaa", String.valueOf(studentlist.size()));
        dm.setSingleItemModels(studentlist);

        allSampleData.add(dm);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(adapter);*//**//**//*

        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(TotalRecord.this,studentlist);
        recyclerView.setAdapter(recyclerAdapter);




    }*/
}
