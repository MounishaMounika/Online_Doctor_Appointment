package com.example.onlinedoctorappointment;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class profile extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference;
    RecyclerView rv1;
    ArrayList<Pojo1> list1;
    DocAdapter docAdapter;
    TextView d1,d2,d3,d4,d5,d6,d7,d8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth=FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Doctors");
        rv1=findViewById(R.id.rv1);
        list1=new ArrayList<>();
        d1=findViewById(R.id.doctv);d2=findViewById(R.id.doctv1);
        d3=findViewById(R.id.doctv2);
        d4=findViewById(R.id.doctv3);d5=findViewById(R.id.doctv4);
        d6=findViewById(R.id.doctv5);d7=findViewById(R.id.doctv6);d8=findViewById(R.id.doctv7);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pojo1 pojo1 = dataSnapshot.getValue(Pojo1.class);
                    list1.add(pojo1);
                    docAdapter=new DocAdapter(profile.this,list1);
                    rv1.setAdapter(docAdapter);
                    rv1.setLayoutManager(new LinearLayoutManager(profile.this));
                    rv1.setItemAnimator(new DefaultItemAnimator());


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
    public void patientform(View view){
        startActivity(new Intent(this,PatientActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(this,ViewData.class));
                break;
            case R.id.signout:
                auth.signOut();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}

