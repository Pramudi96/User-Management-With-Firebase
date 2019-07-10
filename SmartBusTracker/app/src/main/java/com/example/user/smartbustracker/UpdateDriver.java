package com.example.user.smartbustracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDriver extends AppCompatActivity {



    EditText Email;
    EditText Name, Phone, Password,BusNo;
    Button Update,changeMail;
    DatabaseReference R1;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_driver);


        database = FirebaseDatabase.getInstance();
        R1 = database.getReference("Drivers");


        Email = (EditText) findViewById(R.id.Email);
        Name = (EditText) findViewById(R.id.Name);
        Phone = (EditText)findViewById(R.id.Phone);
        BusNo =(EditText)findViewById(R.id.BusNo);
        Password = (EditText) findViewById(R.id.Password);
        Update = (Button) findViewById(R.id.Update);
        changeMail =(Button)findViewById(R.id.changeMail);



        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = Name.getText().toString();
                final String phone =Phone.getText().toString();
                final String busno =BusNo.getText().toString();

                if (!TextUtils.isEmpty(name)){
                    R1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(name);
                    Toast.makeText(UpdateDriver.this, "Name Updated Successfully", Toast.LENGTH_SHORT).show();
                }




                if (!TextUtils.isEmpty(phone)) {
                    R1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone").setValue(phone);
                    Toast.makeText(UpdateDriver.this, "Phone Number Updated Successfully", Toast.LENGTH_SHORT).show();
                }

                if (!TextUtils.isEmpty(busno)) {
                    R1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("busNo").setValue(busno);
                    Toast.makeText(UpdateDriver.this, "Bus Number Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        changeMail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String email = Email.getText().toString();

                if (!TextUtils.isEmpty(email))
                    R1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(email);

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Toast.makeText(UpdateDriver.this, "your mail changed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDriver.this, Navigation.class);
                startActivity(intent);

                if (user != null && !Email.getText().toString().trim().equals("")) {
                    user.updateEmail(Email.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(UpdateDriver.this, "Email address is updated.", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(UpdateDriver.this, "Failed to update email", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else if (Email.getText().toString().trim().equals("")) {
                    Email.setError("Enter email");

                }







            }
        });


    }
    }

