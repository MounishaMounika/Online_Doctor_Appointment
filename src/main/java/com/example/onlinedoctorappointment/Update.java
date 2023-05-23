package com.example.onlinedoctorappointment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Update extends AppCompatActivity {
    EditText e11, e12, e13, e14, e15, e16, e17;
    DatabaseReference reference;
    SharedPreferences preferences;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        auth=FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Database");
        e11 = findViewById(R.id.e);
        e12 = findViewById(R.id.e1);
        e13 = findViewById(R.id.e2);
        e14 = findViewById(R.id.e3);
        e15 = findViewById(R.id.e4);
        e16 = findViewById(R.id.e5);
        e17 = findViewById(R.id.e6);
        preferences=getSharedPreferences("id", Context.MODE_PRIVATE);


    }

    public void save(View view) {
        String s=preferences.getString("id1",null);
       final String name = e11.getText().toString();
        final String phone = e12.getText().toString();
        final String state = e13.getText().toString();
        final String district = e14.getText().toString();
        final String city = e15.getText().toString();
        final String pincode = e16.getText().toString();
        final String street = e17.getText().toString();
        final HashMap<String, Object> map = new HashMap<>();
        if (name.isEmpty() || phone.isEmpty() || state.isEmpty() || district.isEmpty() || city.isEmpty() ||
                pincode.isEmpty() || street.isEmpty()) {
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();

        } else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query query = ref.child("Database").orderByChild("id").equalTo(s);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                         map.put("name",name);
                        map.put("mobile", phone);
                        map.put("state", state);
                        map.put("district", district);
                        map.put("city", city);
                        map.put("pincode", pincode);
                        map.put("street", street);
                        dataSnapshot.getRef().updateChildren(map);
                        Toast.makeText(Update.this, "Data updated", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            startActivity(new Intent(this, profile.class));

        }
    }
}
