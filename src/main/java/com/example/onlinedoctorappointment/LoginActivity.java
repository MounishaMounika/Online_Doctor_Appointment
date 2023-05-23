package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.onlinedoctorappointment.R.id.button;

public class LoginActivity extends AppCompatActivity {
    EditText mail,pass;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.pwd);
        auth=FirebaseAuth.getInstance();




    }

    public boolean login1(View view) {
        String et = mail.getText().toString().trim();
        String et1 =pass.getText().toString().trim();



        if(et.isEmpty())
        {

            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            mail.setError("Field can't be empty");
            return false;
        }else if(et1.isEmpty()){
            Toast.makeText(this, "Please fill all the details ", Toast.LENGTH_SHORT).show();
            pass.setError("Field can't be empty");
            return false;
        }
        auth.signInWithEmailAndPassword(et,et1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, profile.class));
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return false;
    }


    public void create(View view) {
     startActivity(new Intent(this,RegisterActivity.class));
    }

    public boolean forgotpassword(View view) {
        String mai = mail.getText().toString();
        if(mai.isEmpty()){
            mail.setError("Enter email to Reset Password ");
            return false;
        }
        auth.sendPasswordResetEmail(mai).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Reset mail sent", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Enter valid email id", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return false;
    }
}
