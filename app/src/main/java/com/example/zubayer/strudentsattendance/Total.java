package com.example.zubayer.strudentsattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Total extends AppCompatActivity {
    ArrayList<SectionDataModel> allSampleData;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    DatabaseReference dbStudent;
    String teacher_id, class_selected;
    ArrayList Userlist = new ArrayList<>();
    RecyclerView my_recycler_view;
    SectionDataModel dm;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        allSampleData = new ArrayList<SectionDataModel>();
        Bundle bundle1 = getIntent().getExtras();
        class_selected = bundle1.getString("class_selected");
        teacher_id = bundle1.getString("tid");

         my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        listView=(ListView) findViewById(R.id.listview) ;



        Userlist.add("SID");
        dbStudent = ref.child("Student");

        dbStudent.orderByChild("classes").equalTo(class_selected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        Userlist.add(dsp.child("sid").getValue().toString());

                    }

                        list(Userlist);



                    Log.i("sid", String.valueOf(Userlist));

                 display(Userlist);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });





    }

    public void display(final ArrayList userlist) {

            Log.i("user", String.valueOf(userlist.size()));
        dbAttendance = ref.child("attendance");
        Log.i("trik",dbAttendance.getKey());


        dbAttendance.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.i("aaa",dataSnapshot.toString());
                        //DataSnapshot d= (DataSnapshot) dataSnapshot.getChildren();
                        Log.i("key",dataSnapshot.getKey());




                        for (final DataSnapshot dsp2 : dataSnapshot.getChildren()) {
                            Log.i("tag", dsp2.getValue().toString());
                            Log.i("tag2", dsp2.getKey().toString());



                            //dm.setHeaderTitle(dsp2.getKey());

                            //Log.i("dm",dsp2.getKey());

                            final ArrayList<SingleItemModel> singleItemModels = new ArrayList<SingleItemModel>();

                            for (Object sid : userlist) {

                                dbAttendance.child(dsp2.getKey()).child(sid.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                      //  Log.i("lll",dataSnapshot.getValue().toString());

                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            String p = dataSnapshot1.getValue().toString();

                                            Log.i("tag5", p);
                                            if ((p.equals("A / " + teacher_id)) || (p.equals("P / " + teacher_id))) {
                                                singleItemModels.add(new SingleItemModel(p.substring(0, 1)));
                                                Log.i("xxxxxxxx", String.valueOf(singleItemModels.size()));
                                            }
                                        }
                                        dm = new SectionDataModel(singleItemModels, dsp2.getKey());

                                        Log.i("yyy",dm.toString());
                                        //dm.setSingleItemModels();
                                        allSampleData.add(dm);
                                        Log.i("zzz", String.valueOf(allSampleData));
                                        my_recycler_view.setHasFixedSize(true);
                                        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(Total.this, allSampleData);

                                        Log.i("rrr",adapter.toString());
                                        my_recycler_view.setLayoutManager(new LinearLayoutManager(Total.this, LinearLayoutManager.HORIZONTAL, false));

                                        my_recycler_view.setAdapter(adapter);


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
                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();

                }
            });



    }

    public void list(ArrayList studentlist){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Total.this, android.R.layout.simple_list_item_1, android.R.id.text1, studentlist);

        listView.setAdapter(adapter);


    }
}





