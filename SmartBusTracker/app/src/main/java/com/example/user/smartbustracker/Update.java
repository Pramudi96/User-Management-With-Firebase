package com.example.user.smartbustracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update extends AppCompatActivity {

    EditText Email;
    EditText Name, Phone, Password;
    Button Update,changeMail;
    DatabaseReference R1;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = FirebaseDatabase.getInstance();
        R1 = database.getReference("Registration");


        Email = (EditText) findViewById(R.id.Email);
        Name = (EditText) findViewById(R.id.Name);
        Phone = (EditText) findViewById(R.id.Phone);
        Password = (EditText) findViewById(R.id.Password);
        Update = (Button) findViewById(R.id.Update);
        changeMail =(Button)findViewById(R.id.changeMail);



        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = Name.getText().toString();
                final String phone =Phone.getText().toString();


                String validName="^[A-Za-z]+$";
                String validPhone ="^[0-9]$";

                Matcher namev = Pattern.compile(validName).matcher(name);

                Matcher phonev =Pattern.compile(validPhone).matcher(phone);

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!namev.matches()){
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Name",Toast.LENGTH_SHORT).show();
                }




                if (!TextUtils.isEmpty(name))
                    R1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(name);



                if (!TextUtils.isEmpty(phone))
                   R1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone").setValue(phone);

                if(phone.length() < 10 | phone.length()>10){
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Phone Number 2",Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        changeMail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

        final String email = Email.getText().toString();
                String validemail ="[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+"\\@" +"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+"("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+";
                Matcher emailv = Pattern.compile(validemail).matcher(email);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!emailv.matches()){
                    Toast.makeText(getApplicationContext(),"Enter valid Email address",Toast.LENGTH_SHORT).show();
                    return;
                }




                if (!TextUtils.isEmpty(email))
                    R1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(email);

               final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

               Toast.makeText(Update.this, "Successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Update.this, Navigation.class);
                startActivity(intent);

                if (user != null && !Email.getText().toString().trim().equals("")) {
                    user.updateEmail(Email.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Update.this, "Email address is updated.", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(Update.this, "Failed to update email", Toast.LENGTH_SHORT).show();
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