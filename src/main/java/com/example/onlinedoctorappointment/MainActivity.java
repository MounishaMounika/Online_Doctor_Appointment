package com.example.onlinedoctorappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

    }


    public void login(View view) {
        Intent i=new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void register(View view) {

        Intent i=new Intent(this,RegisterActivity.class);
        startActivity(i);

    }

    public void doclogin(View view) {
        Intent i=new Intent(this, DocLogin.class);
        startActivity(i);
    }

    public void docreg(View view) {
        startActivity(new Intent(this,docregister.class));
    }
}

