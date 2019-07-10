package com.example.user.smartbustracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    public Button button;
    public Button button3;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    public void in() {

        button = (Button) findViewById(R.id.button);
        button3 =(Button)findViewById(R.id.button3) ;
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Selection.class);
                startActivity(intent);

            }


        });


    button3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Welcome.this, MainActivity.class);
            startActivity(intent);

        }
    });


  //  protected void onStart() {
        //super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        in();
}
//        progressDialog = new ProgressDialog(this);

  //      firebaseAuth = FirebaseAuth.getInstance();


    //    button = (Button) findViewById(R.id.button);
      //  button3 = (Button) findViewById(R.id.button3);

        //button.setOnClickListener(this);
       // button3.setOnClickListener(this);

    }

    //@Override
   /* public void onClick(View view) {

        if (view == button) {

            startActivity(new Intent(this, RegistrationActivity.class));

        }

        if (view == button3) {
            startActivity(new Intent(this, MainActivity.class));
        }


    }*/

