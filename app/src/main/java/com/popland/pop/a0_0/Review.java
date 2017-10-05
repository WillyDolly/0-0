package com.popland.pop.a0_0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by hai on 02/09/2017.
 */

public class Review extends AppCompatActivity{
ProgressBar PBreview;
RelativeLayout  RLpoints;
TextView TVn1, TV;
ImageView IV1, IV2;
ArrayList<Integer> NumberArray = new ArrayList<>();
int Rbmp;
Line line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        PBreview = (ProgressBar)findViewById(R.id.PBreview);
        RLpoints = (RelativeLayout)findViewById(R.id.RLpoints);
        TVn1 = (TextView) findViewById(R.id.TVn1);
        IV1 = (ImageView) findViewById(R.id.IV1);
        IV2 = (ImageView) findViewById(R.id.IV2);
        TV = (TextView) findViewById(R.id.TV);

        Randomizer();
        line = new Line(this);
        RLpoints.addView(line);
    }

    public void Randomizer(){
        Random r = new Random();
        int Rso1, Rso2;
        do{
            Rso1 = r.nextInt(100);
            Rso2 = r.nextInt(100);
        }while(NumberArray.contains(Rso1) || Rso1==Rso2);

        NumberArray.add(Rso1);
        TVn1.setText(String.valueOf(Rso1));

        Rbmp = r.nextInt(2);
        if(Rbmp==0){
            setImage(Rso1,IV1);
            setImage(Rso2,IV2);
        }else{
            setImage(Rso1,IV2);
            setImage(Rso2,IV1);
        }
    }

    public void setImage(int Rso,ImageView IV){
        byte[] bA = SplashActivity.ImageArray.get(Rso);
        Bitmap bmp = BitmapFactory.decodeByteArray(bA,0,bA.length);
        IV.setImageBitmap(bmp);
    }

    float startX, startY, lastX, lastY;
    boolean drawingLock1 = false;
    class Line extends View{
            Paint paint = new Paint();

            public Line(Context context) {
                super(context);
                paint.setStrokeWidth(10);
                paint.setColor(Color.parseColor("#ff00dd"));
            }

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawLine(startX, startY, lastX, lastY, paint);
            }

           @Override
           public boolean onTouchEvent(MotionEvent event) {
               switch(event.getAction()){
                   case MotionEvent.ACTION_MOVE:
                       if(areaConditions(event,1)) {
                           startX = (TVn1.getLeft() + TVn1.getRight()) / 2;
                           startY = (TVn1.getTop() + TVn1.getBottom()) / 2;
                           lastX = startX;
                           lastY = startY;
                           drawingLock1 = true;
                       }
                       if(drawingLock1) {
                           lastX = event.getX();
                           lastY = event.getY();
                           if(Rbmp==0){
                               if(areaConditions(event,2)) {
                                   TV.setText("right");
                                   ifRight();
                               }
                               if(areaConditions(event,3))
                                   TV.setText("wrong");
                           }else{
                               if(areaConditions(event,2))
                                   TV.setText("wrong");
                               if(areaConditions(event,3)) {
                                   TV.setText("right");
                                   ifRight();
                               }
                           }
                       }
                       break;
                   case MotionEvent.ACTION_UP:
                       drawingLock1 = false;
                       startX = startY = lastX = lastY = 0;
                       break;
               }
               invalidate();
               return true;
           }
    }

    public void ifRight(){
        PBreview.setProgress(PBreview.getProgress()+1);
        MediaPlayer media = MediaPlayer.create(Review.this,R.raw.link);
        media.start();
        if(PBreview.getProgress()==100){
            Intent i = new Intent(Review.this,SplashActivity.class);
            startActivity(i);
        }else{
            drawingLock1 = false;
            startX = startY = lastX = lastY = 0;
            Randomizer();
        }
    }

    public boolean areaConditions(MotionEvent event,int type){
        boolean check = false;
        switch(type){
            case 1:
                check = event.getX()>=TVn1.getLeft() && event.getX()<=TVn1.getRight()
                        && event.getY()>=TVn1.getTop() && event.getY()<=TVn1.getBottom();
                break;
            case 2:
                check = event.getX()>=IV1.getLeft() && event.getX()<=IV1.getRight()
                        && event.getY()>=IV1.getTop() && event.getY()<=IV1.getBottom();
                break;
            case 3:
                check = event.getX()>=IV2.getLeft() && event.getX()<=IV2.getRight()
                        && event.getY()>=IV2.getTop() && event.getY()<=IV2.getBottom();
                break;
        }
        return check;
    }

}
