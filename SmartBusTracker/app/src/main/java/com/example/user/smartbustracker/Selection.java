package com.example.user.smartbustracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selection extends AppCompatActivity {

    private Button Driver;
    private Button Custormer;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Driver =(Button)findViewById(R.id.Driver);
        Custormer =(Button)findViewById(R.id.Custormer);

        Driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Selection.this,DriversRegistration.class);
                startActivity(intent);
            }
        });

        Custormer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Selection.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}
