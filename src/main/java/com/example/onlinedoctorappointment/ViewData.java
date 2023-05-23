package com.example.onlinedoctorappointment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {


    RecyclerView rv;
    FirebaseAuth auth;
    DatabaseReference reference;
    ArrayList<Pojo>list;
    MyAdapter myAdapter;
    TextView b,a,c,d,e,f,g,h,p1,p2,p3,p4,p5,p6,p7,p8;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        auth=FirebaseAuth.getInstance();
        rv=findViewById(R.id.rv);
        list=new ArrayList<>();
        preferences=getSharedPreferences("id", Context.MODE_PRIVATE);
        String s=preferences.getString("id1",null);
        a=findViewById(R.id.tv); b=findViewById(R.id.tv1); c=findViewById(R.id.tv2);
        d=findViewById(R.id.tv3); e=findViewById(R.id.tv4); f=findViewById(R.id.tv5);
        g=findViewById(R.id.tv6);h=findViewById(R.id.tv7);
        p1=findViewById(R.id.p1);p2=findViewById(R.id.p2);p3=findViewById(R.id.p3);p4=findViewById(R.id.p4);
        p5=findViewById(R.id.p5);p6=findViewById(R.id.p6);p7=findViewById(R.id.p7);p8=findViewById(R.id.p8);
        reference=FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Database").orderByChild("id").equalTo(s);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pojo pojo = dataSnapshot.getValue(Pojo.class);
                    list.add(pojo);
                    myAdapter=new MyAdapter(ViewData.this,list);
                    rv.setAdapter(myAdapter);
                    rv.setLayoutManager(new LinearLayoutManager(ViewData.this));
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewData.this, "View failed", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void update(View view) {
        startActivity(new Intent(this,Update.class));

    }

}
