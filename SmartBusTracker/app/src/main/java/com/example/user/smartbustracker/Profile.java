package com.example.user.smartbustracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity {

    DatabaseReference Ref;
    private Button Driver;
    private Button Customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Driver = (Button) findViewById(R.id.Driver);
        Customer = (Button) findViewById(R.id.Customer);

        Driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ref = FirebaseDatabase.getInstance().getReference("Drivers").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Toast.makeText(getApplicationContext(), dataSnapshot.getValue() + "", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ref = FirebaseDatabase.getInstance().getReference("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Toast.makeText(getApplicationContext(), dataSnapshot.getValue() + "", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}


















