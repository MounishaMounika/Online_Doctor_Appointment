package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DocViewData extends AppCompatActivity  {
    FirebaseAuth auth;
    DatabaseReference reference;
    RecyclerView r3;
    ArrayList<Pojo1> list2;
    Adapter adapter;
    TextView a1,b1,c1,d1,e1,f1,g1,h1,t1,t2,t3,t4,t5,t6,t7,t8;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_view_data);
        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Doctors");
        r3=findViewById(R.id.r3);
        list2=new ArrayList<>();
        a1=findViewById(R.id.doctv);b1=findViewById(R.id.doctv1);c1=findViewById(R.id.doctv2);d1=findViewById(R.id.doctv3);
        e1=findViewById(R.id.doctv4);f1=findViewById(R.id.doctv5);g1=findViewById(R.id.doctv6);h1=findViewById(R.id.doctv7);
        t1 = findViewById(R.id.t1);t2 = findViewById(R.id.t2);t3 = findViewById(R.id.t3);t4 =findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);t6 = findViewById(R.id.t6);t7 = findViewById(R.id.t7);t8=findViewById(R.id.t8);
        preferences=getSharedPreferences("docid", Context.MODE_PRIVATE);
        String s1=preferences.getString("unique1",null);
        reference=FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Doctors").orderByChild("docid").equalTo(s1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pojo1 pojo1 = dataSnapshot.getValue(Pojo1.class);
                    list2.add(pojo1);
                    adapter=new Adapter(DocViewData.this,list2);
                    r3.setAdapter(adapter);
                    r3.setLayoutManager(new LinearLayoutManager(DocViewData.this));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void patientform(View view){
        startActivity(new Intent(this,PatientActivity.class));

    }

    public void edit(View view) {
        startActivity(new Intent(this,DocUpdate.class));
    }
}
