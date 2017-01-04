package com.demoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_name,edt_mail,edt_pass;
    Button btn_signup,btn_signin;
    Firebase ref;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        ref = new Firebase("https://fir-app-5259c.firebaseio.com/");
        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");

        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_mail = (EditText)findViewById(R.id.edt_mail);
        edt_pass = (EditText)findViewById(R.id.edt_pass);
        btn_signup = (Button)findViewById(R.id.btn_reg);
        btn_signin = (Button)findViewById(R.id.btn_link);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PD.show();
                String name = edt_name.getText().toString();
                String email = edt_mail.getText().toString();
                String pass = edt_pass.getText().toString();

                ///...
                ref.child("users").child(name).child("name").setValue(name, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError error, Firebase firebase) {
                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "try Again", Toast.LENGTH_LONG).show();
                        }
                        PD.dismiss();
                    }
                });
                ref.child("users").child(name).child("email").setValue(email, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError error, Firebase firebase) {
                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "try Again", Toast.LENGTH_LONG).show();
                        }
                        PD.dismiss();
                    }
                });

                ref.child("users").child(name).child("pass").setValue(pass, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError error, Firebase firebase) {
                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "try Again", Toast.LENGTH_LONG).show();
                        }
                        PD.dismiss();
                    }
                });

            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}