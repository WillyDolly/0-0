package com.popland.pop.a0_0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Utils.SQLite;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{
TextView TVmemory, TVimagination;
SQLite sqlite;
public static ArrayList<byte[]> ImageArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TVmemory = (TextView)findViewById(R.id.TVmemory);
        TVimagination = (TextView)findViewById(R.id.TVimagination);

        TVmemory.setOnClickListener(this);
        TVimagination.setOnClickListener(this);

        sqlite = new SQLite(this);
        sqlite.check();
        ImageArray = sqlite.DBtoArrayList();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.TVmemory){
           Intent i = new Intent(SplashActivity.this,MemoryLand.class);
            startActivity(i);
        }
        if(v.getId()==R.id.TVimagination){
            Intent i = new Intent(SplashActivity.this,NumberActivity.class);
            startActivity(i);
        }
    }
}
