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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriversRegistration extends AppCompatActivity {

    private EditText Email, Name, Phone, Password,BusNo;

    private Button Register, Login, Up;
    private RadioButton root_select;
    private RadioGroup radio;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    DatabaseReference R1;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_registration);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        R1 = database.getReference("Drivers");

        /*PK = (RadioButton)findViewById(R.id.PK) ;
        KN =(RadioButton)findViewById(R.id.KN);*/

       /* radio =(RadioGroup)findViewById(R.id.root);
        int select_id = radio.getCheckedRadioButtonId();
        root_select =(RadioButton)findViewById(select_id);*/

        Name = (EditText) findViewById(R.id.Name);
        Email = (EditText) findViewById(R.id.Email);
        Phone = (EditText) findViewById(R.id.Phone);
        Password = (EditText) findViewById(R.id.Password);
        Up = (Button) findViewById(R.id.Up);
        BusNo =(EditText)findViewById(R.id.BusNo);

        Register = (Button) findViewById(R.id.Register);
        Login = (Button) findViewById(R.id.Login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriversRegistration.this, MainActivity.class));
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
                startActivity(new Intent(DriversRegistration.this, Update.class));
                finish();
            }
        });


    }


    private void registerUser() {

        //int select_id = radio.getCheckedRadioButtonId();
        final String name = Name.getText().toString().trim();
        final String phone = Phone.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String busno = BusNo.getText().toString().trim();
       final  String password = Password.getText().toString().trim();
        //final String Roots=root_select.getText().toString().trim();



        //
        String validemail ="[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+"\\@" +"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+"("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+";

       // String validemail ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
               // + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


       // String validNAme ="[a-zA-Z]";

        String validName="^[A-Za-z]+$";

       // String validPhone ="[0-9]{10}";

        //String validPhone ="\\d{10}";
        String validPhone ="^[0-9]$";

       // String validBusNo = "^[A-Za-z]+$"+"^[0-9]$";
        //String validBusNo = "^[a-zA-Z0-9]+$";
        //

        String validBusNo="[0-9a-zA-Z]+$";

        Matcher namev = Pattern.compile(validName).matcher(name);
        Matcher emailv = Pattern.compile(validemail).matcher(email);
        Matcher phonev =Pattern.compile(validPhone).matcher(phone);
        Matcher BusNo =Pattern.compile(validBusNo).matcher(busno);
        boolean m =phone.matches(phone);

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
            return;

           //Name.setError("Not Valid Email");
        }
       if(!namev.matches()){
            Toast.makeText(getApplicationContext(),"Please Enter Valid Name",Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!emailv.matches()){
            Toast.makeText(getApplicationContext(),"Enter valid Email address",Toast.LENGTH_SHORT).show();
            return;
        }



        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!m){
            Toast.makeText(getApplicationContext(),"Please Enter Valid Phone Number 1",Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.length() < 10 | phone.length()>10){
            Toast.makeText(getApplicationContext(),"Please Enter Valid Phone Number 2",Toast.LENGTH_SHORT).show();
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
        if(TextUtils.isEmpty(busno)){
            Toast.makeText(getApplicationContext(), "Please Enter  Bus Number", Toast.LENGTH_SHORT).show();
            return;
        }

       if (!BusNo.matches()){
            Toast.makeText(getApplicationContext(), "Invalid Bus Number", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(DriversRegistration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            Driver driver = new Driver(name, email, phone,busno);
                            FirebaseDatabase.getInstance().getReference("Drivers").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(DriversRegistration.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(DriversRegistration.this, Navigation.class);
                                        startActivity(intent);

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(DriversRegistration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Register:
                registerUser();
                break;
        }
    }
}
