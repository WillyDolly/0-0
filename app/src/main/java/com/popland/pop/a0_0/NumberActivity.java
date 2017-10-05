package com.popland.pop.a0_0;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import Utils.Lines;
import io.github.francoiscampbell.circlelayout.CircleLayout;

public class NumberActivity extends AppCompatActivity {
RelativeLayout RLlines;
ProgressBar PBimagination;
CircleLayout CL;
int stage = 2, level = 0;
int interval = 7000;
int index = 0;
ArrayList<TextView> tvArray;
ArrayList<Integer> numberArray = new ArrayList<>();
public static ArrayList<Integer> subNumberArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        RLlines = (RelativeLayout)findViewById(R.id.RLlines);
        PBimagination = (ProgressBar) findViewById(R.id.PBimagination);
        CL = (CircleLayout)findViewById(R.id.CircleLayout);
        for(int i=0;i<100;i++)
            numberArray.add(i);

        addTV_runTime();
    }

    public void addTV_runTime(){
        tvArray = new ArrayList<>();
        for(int i=1;i<=stage;i++){
            TextView TV = new TextView(NumberActivity.this);
            TV.setBackgroundResource(R.drawable.round_shape);
            TV.setGravity(Gravity.CENTER);
            LayoutParams LP = new LayoutParams(200,200);
            CL.addView(TV,LP);
            tvArray.add(TV);
        }

        Collections.shuffle(numberArray);
        subNumberArray = new ArrayList<>();
        CountDownTimer cdt = new CountDownTimer((stage+1)*interval,interval) {
            @Override
            public void onTick(long millisUntilFinished) {//tick st start
                float startX, startY, lastX, lastY;
                if(index>0){
                    startX = ( tvArray.get(index-1).getLeft() + tvArray.get(index-1).getRight() )/2;
                    startY = ( tvArray.get(index-1).getTop() + tvArray.get(index-1).getBottom() )/2;
                    lastX =  ( tvArray.get(index).getLeft() + tvArray.get(index).getRight() )/2;
                    lastY = ( tvArray.get(index).getTop() + tvArray.get(index).getBottom() )/2;
                    Lines lines = new Lines(NumberActivity.this,startX,startY,lastX,lastY);
                    RLlines.addView(lines);
                    MediaPlayer media = MediaPlayer.create(NumberActivity.this,R.raw.link);
                    media.start();
                }
                tvArray.get(index).setText(numberArray.get(index)+"");
                subNumberArray.add(numberArray.get(index));
                index++;
            }

            @Override
            public void onFinish() {//timeToFinish = interval*2
                index = 0;
                CL.removeAllViews();
                RLlines.removeAllViews();
                Toast.makeText(NumberActivity.this,"size: "+subNumberArray.size(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NumberActivity.this,ImageActivity.class);
                startActivityForResult(i,999);
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999 && resultCode==RESULT_OK && data!=null){
            //share - invite code
            if(data.getBooleanExtra("RightWrong",false)){
                level++;
                Toast.makeText(NumberActivity.this,"level: "+level,Toast.LENGTH_SHORT).show();
                PBimagination.setProgress(level);
                if(level%3==0 && level!=21){
                    stage++;
                   // interval=-1000;
                }
                if(level==21){
                    //remain last stage for endless
                    //finish game, do sth
                }
            }

            addTV_runTime();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
