package com.clp.leanclouddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.regis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeancloudApp.isRevice=true;
            }
        });

        findViewById(R.id.un).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeancloudApp.isRevice=false;
            }
        });
    }
}
