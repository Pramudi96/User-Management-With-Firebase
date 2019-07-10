package com.example.user.smartbustracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Root extends AppCompatActivity {

  int no[] = {17,177};

  Spinner No;
  TextView t1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>myad = new ArrayAdapter<String>(Root.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item  );
        spinner.setAdapter(myad);

        Spinner spinner1 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String>myad1 =new ArrayAdapter<String>(Root.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.cake));
        myad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myad1);

        spinner.setAdapter(myad);
        spinner1.setAdapter(myad1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    startActivity(new Intent(Root.this,MainActivity.class));
                }
                if (i == 2){
                    startActivity(new Intent(Root.this,MapsActivity.class));
                }

            }





            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    startActivity(new Intent(Root.this,MainActivity.class));
                }
                if (i == 2){
                    startActivity(new Intent(Root.this,MapsActivity.class));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}
