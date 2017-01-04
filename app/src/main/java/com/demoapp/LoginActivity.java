package com.demoapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by DELL on 04-01-2017.
 */
public class LoginActivity extends AppCompatActivity {

    EditText edt_name,edt_pass;
    Button btn_signin;
    Firebase ref;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://fir-app-5259c.firebaseio.com/");

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");

        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_pass = (EditText)findViewById(R.id.edt_pass);
        btn_signin = (Button)findViewById(R.id.btn_reg);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PD.show();
                final String name = edt_name.getText().toString();
                final String pass = edt_pass.getText().toString();

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (name.equals(dataSnapshot.child("users").child(name).child("name").getValue())
                                &&pass.equals(dataSnapshot.child("users").child(name).child("pass").getValue())) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                        }
                        PD.dismiss();
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });
    }
}
