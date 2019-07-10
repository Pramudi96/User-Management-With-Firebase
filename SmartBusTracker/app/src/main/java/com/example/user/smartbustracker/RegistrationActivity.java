package com.example.user.smartbustracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Email,Name,Phone,Password;
    private Button Register,Login,Up;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    DatabaseReference R1;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        R1 = database.getReference("Registration");

        Name = (EditText) findViewById(R.id.Name);
        Email = (EditText) findViewById(R.id.Email);
        Phone = (EditText) findViewById(R.id.Phone);
        Password = (EditText) findViewById(R.id.Password);
        Up =(Button)findViewById(R.id.Up);

        Register = (Button) findViewById(R.id.Register);
        Login = (Button) findViewById(R.id.Login);
        progressBar =(ProgressBar)findViewById(R.id.progressBar);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, Update.class));
                finish();
            }
        });


    }

            private void registerUser(){
                final String name = Name.getText().toString().trim();
                final String phone = Phone.getText().toString().trim();
                final String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();




                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    final Customer customer = new Customer(name, email, phone);

                                    FirebaseDatabase.getInstance().getReference("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegistrationActivity.this, " Customer Registration Successfull", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(RegistrationActivity.this,Navigation.class);
                                                startActivity(intent);

                                            }
                                        }
                                    });

                                } else {
                                    Toast.makeText(RegistrationActivity.this,"Customer has been already exists", Toast.LENGTH_LONG).show();
                                }

                            }

                        });


            }




    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register:
                registerUser();
                break;
        }
    }





}
