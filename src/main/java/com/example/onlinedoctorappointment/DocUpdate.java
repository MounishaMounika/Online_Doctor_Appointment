package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DocUpdate extends AppCompatActivity {
    EditText d1,d2,d3,d4,d5,d6,d7,d8;
    DatabaseReference reference;
    SharedPreferences preferences;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_update);
        auth= FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Doctors");
        preferences=getSharedPreferences("docid", Context.MODE_PRIVATE);
        d1=findViewById(R.id.ed);d2=findViewById(R.id.ed1);d3=findViewById(R.id.ed2);
        d4=findViewById(R.id.ed3);d5=findViewById(R.id.ed4);d6=findViewById(R.id.ed5);
        d7=findViewById(R.id.ed6);d8=findViewById(R.id.ed7);
    }

    public void docsave(View view) {
        String s1=preferences.getString("unique1",null);
        final String docname=d1.getText().toString();
        final String hosname=d2.getText().toString();
        final String doctype=d3.getText().toString();
        final String hcn=d4.getText().toString();
        final String docfee=d5.getText().toString();
        final String time=d6.getText().toString();
        final String ha=d7.getText().toString();
        final String ds=d8.getText().toString();
        final HashMap<String, Object> map = new HashMap<>();
        if (docname.isEmpty() || hosname.isEmpty() || doctype.isEmpty() || hcn.isEmpty() || docfee.isEmpty() ||
                time.isEmpty() || ha.isEmpty()) {
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();

        } else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query query = ref.child("Doctors").orderByChild("docid").equalTo(s1);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        map.put("docname",docname);
                        map.put("hosname", hosname);
                        map.put("doctype", doctype);
                        map.put("hcn", hcn);
                        map.put("fee", docfee);
                        map.put("time", time);
                        map.put("address", ha);
                        map.put("docspecial",ds);
                        dataSnapshot.getRef().updateChildren(map);
                        Toast.makeText(DocUpdate.this, "Data updated", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            startActivity(new Intent(this,DocProfile.class));
        }

    }
}
