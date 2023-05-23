package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText name, em, phone, st, dis, ci, pin, str, pass;
    FirebaseAuth auth;
    DatabaseReference reference;
    SharedPreferences preferences;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        em = findViewById(R.id.em);
        phone = findViewById(R.id.phone);
        st = findViewById(R.id.st);
        dis = findViewById(R.id.dis);
        ci = findViewById(R.id.ci);
        pin = findViewById(R.id.pin);
        str = findViewById(R.id.str);
        pass = findViewById(R.id.pwd);
        auth = FirebaseAuth.getInstance();
        preferences=getSharedPreferences("id", Context.MODE_PRIVATE);
        reference = FirebaseDatabase.getInstance().getReference("Database");
        progressDialog=new ProgressDialog(RegisterActivity.this);

    }


    public void register(View view) {
       final String mail = em.getText().toString().trim();
         String password = pass.getText().toString().trim();
        final String name1=name.getText().toString();
        final String phonenum=phone.getText().toString();
        final String state = st.getText().toString();
        final String district = dis.getText().toString();
        final String city = ci.getText().toString();
        final String pincode = pin.getText().toString();
        final String street = str.getText().toString();
        final String id=reference.push().getKey();
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        if (name1.isEmpty() || mail.isEmpty() || password.isEmpty() || phonenum.isEmpty() || state.isEmpty() || district.isEmpty() || city.isEmpty() ||
                pincode.isEmpty() || street.isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();

        } else {
            auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Pojo pojo = new Pojo(name1, phonenum, state, district, city, pincode, street,mail,id);
                                reference.child(id).setValue(pojo);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("id1",id);
                                editor.commit();
                                startActivity(new Intent(RegisterActivity.this, profile.class));
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }
}













