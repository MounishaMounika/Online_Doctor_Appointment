package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class docregister extends AppCompatActivity {
    EditText doc,doc1,docpwd,doc2,doc3,doc4,doc5,doc6,doc7,doc8;
    FirebaseAuth auth;
    DatabaseReference reference;
    SharedPreferences preferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docregister);
        doc=findViewById(R.id.doc);
        doc1=findViewById(R.id.doc1);
        docpwd=findViewById(R.id.docpwd);
        doc2=findViewById(R.id.doc2);
        doc3=findViewById(R.id.doc3);
        doc4=findViewById(R.id.doc4);
        doc5=findViewById(R.id.doc5);
        doc8=findViewById(R.id.doc8);
        doc6=findViewById(R.id.doc6);doc7=findViewById(R.id.doc7);
        auth = FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Doctors");
        preferences=getSharedPreferences("docid", Context.MODE_PRIVATE);
        progressDialog=new ProgressDialog(docregister.this);

    }

    public void docregister(View view) {
        final String docmail = doc1.getText().toString().trim();
        String docpassword = docpwd.getText().toString().trim();
       final String docname=doc.getText().toString();
       final String hosname=doc2.getText().toString();
        final String doctype = doc3.getText().toString();
        final String docspecial=doc8.getText().toString();
        final String hcn = doc4.getText().toString();
        final String fee = doc5.getText().toString();
        final String time = doc6.getText().toString();
        final String address=doc7.getText().toString();
        final String docid=reference.push().getKey();

        progressDialog.setMessage("Loading....");
        progressDialog.show();

        if (docmail.isEmpty() || docpassword.isEmpty() || docname.isEmpty() || hosname.isEmpty() || doctype.isEmpty()||docspecial.isEmpty() || hcn.isEmpty() || fee.isEmpty() ||
                time.isEmpty()||address.isEmpty() ) {
            progressDialog.dismiss();
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();

        } else {
            auth.createUserWithEmailAndPassword(docmail, docpassword).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Pojo1 pojo1=new Pojo1(docmail,docname,hosname,doctype,docspecial,hcn,fee,time,docid,address);
                                reference.child(docid).setValue(pojo1);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("unique1",docid);
                                editor.commit();
                                startActivity(new Intent(docregister.this, DocProfile.class));
                                Toast.makeText(docregister.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(docregister.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }

    }
}
