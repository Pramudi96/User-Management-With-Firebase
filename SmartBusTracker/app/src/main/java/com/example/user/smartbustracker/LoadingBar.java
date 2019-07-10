package com.example.user.smartbustracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class LoadingBar extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_bar);


        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        Thread thread = new Thread(){
            @Override
            public void run() {

                try{
                    for (int i = 0; i < 100; i++) {
                        progressBar.setProgress(i);
                        sleep(20);
                    }

                }catch(Exception e){
                    e.printStackTrace();

                }finally{

                    Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();

    }
}
