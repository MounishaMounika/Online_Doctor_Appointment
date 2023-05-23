package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DocLogin extends AppCompatActivity {
    EditText docmail,docpass;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_login);
        docmail=findViewById(R.id.dmail);
        docpass=findViewById(R.id.pwd2);
        auth=FirebaseAuth.getInstance();

    }

    public boolean login1(View view) {
        String doce = docmail.getText().toString().trim();
        String docp =docpass.getText().toString().trim();




        if(doce.isEmpty())
        {
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            docmail.setError("Field can't be empty");
            return false;
        }else if(docp.isEmpty()){
            Toast.makeText(this, "Please fill all the details ", Toast.LENGTH_SHORT).show();
            docpass.setError("Field can't be empty");
            return false;
        }
        auth.signInWithEmailAndPassword(doce,docp).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DocLogin.this, "Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DocLogin.this, DocProfile.class));
                    finish();
                }
                else {

                    Toast.makeText(DocLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return false;
    }






    public boolean forgotpassword(View view) {
        String dmai = docmail.getText().toString();
        if(dmai.isEmpty()){
            docmail.setError("Enter email to Reset Password ");
            return false;
        }
        auth.sendPasswordResetEmail(dmai).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DocLogin.this, "Reset mail sent", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DocLogin.this, "Enter valid email id", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return false;
       
    }

    public void create(View view)
    {
        
        startActivity(new Intent(this,docregister.class));
    }
}
