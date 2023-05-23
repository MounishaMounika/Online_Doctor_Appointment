package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DocProfile extends AppCompatActivity{
    FirebaseAuth auth;
    DatabaseReference reference;
    RecyclerView rv4;
    ArrayList<Pojo1> list1;
    DocAdapter docAdapter;
    TextView d1,d2,d3,d4,d5,d6,d7,d8,t1,t2,t3,t4,t5,t6,t7,t8,ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);
        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Doctors");
        rv4=findViewById(R.id.rv4);
        list1=new ArrayList<>();
        d1=findViewById(R.id.doctv);d2=findViewById(R.id.doctv1);
        d3=findViewById(R.id.doctv2);
        d4=findViewById(R.id.doctv3);d5=findViewById(R.id.doctv4);ds=findViewById(R.id.t8);
        d6=findViewById(R.id.doctv5);d7=findViewById(R.id.doctv6);d8=findViewById(R.id.doctv7);
        t1 = findViewById(R.id.t1);t2 = findViewById(R.id.t2);t3 = findViewById(R.id.t3);t4 =findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);t6 = findViewById(R.id.t6);t7 = findViewById(R.id.t7);t8=findViewById(R.id.t8);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pojo1 pojo1 = dataSnapshot.getValue(Pojo1.class);
                    list1.add(pojo1);
                    docAdapter = new DocAdapter(DocProfile.this, list1);
                    rv4.setAdapter(docAdapter);
                    rv4.setLayoutManager(new LinearLayoutManager(DocProfile.this));
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
        getMenuInflater().inflate(R.menu.docmenu,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.docpro:
                startActivity(new Intent(this,DocViewData.class));
                break;
            case R.id.so:
                auth.signOut();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

