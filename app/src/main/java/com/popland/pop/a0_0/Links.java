package com.popland.pop.a0_0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by hai on 29/08/2017.
 */

public class Links extends AppCompatActivity {
RelativeLayout RLlinks;
ProgressBar PBlinks;
TextSwitcher TSnumber;
ImageView IVimage;
String[] numbers = new String[100];
int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        RLlinks = (RelativeLayout)findViewById(R.id.RLlinks);
        PBlinks = (ProgressBar) findViewById(R.id.PBlinks);
        TSnumber = (TextSwitcher)findViewById(R.id.TSnumber);
        IVimage = (ImageView)findViewById(R.id.IVimage);

        //create data
        for(int i=0;i<100;i++)
            numbers[i] = String.valueOf(i);
        Toast.makeText(Links.this,SplashActivity.ImageArray.size()+"",Toast.LENGTH_SHORT).show();

        Animation in = AnimationUtils.makeInAnimation(this,false);
        Animation out = AnimationUtils.makeOutAnimation(this,false);

        TSnumber.setInAnimation(in);
        TSnumber.setOutAnimation(out);
        TSnumber.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView TV = new TextView(Links.this);
                TV.setLayoutParams(new TextSwitcher.LayoutParams(TextSwitcher.LayoutParams.MATCH_PARENT,TextSwitcher.LayoutParams.MATCH_PARENT));
                TV.setGravity(Gravity.CENTER);
                TV.setTextSize(30);
                return TV;
            }
        });
        TSnumber.setText(numbers[0]);

        Bitmap bmpA = BitmapFactory.decodeByteArray(SplashActivity.ImageArray.get(0),0,SplashActivity.ImageArray.get(0).length);
        IVimage.setImageBitmap(bmpA);

        Line line = new Line(this);
        RLlinks.addView(line);
    }

    float startX, startY, lastX, lastY;
    boolean drawingLock1 = false;
    class Line extends View{
        Paint paint = new Paint();

        public Line(Context context) {
            super(context);
            paint.setStrokeWidth(10);
            paint.setColor(Color.parseColor("#FFF5DB58"));
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
                    if(event.getX()>=TSnumber.getLeft() && event.getX()<=TSnumber.getRight()
                            && event.getY()>=TSnumber.getTop() && event.getY()<=TSnumber.getBottom()) {
                        startX = (TSnumber.getLeft() + TSnumber.getRight()) / 2;
                        startY = (TSnumber.getTop() + TSnumber.getBottom()) / 2;
                        lastX = startX;
                        lastY = startY;
                        drawingLock1 = true;
                    }
                    if(drawingLock1) {
                        lastX = event.getX();
                        lastY = event.getY();
                        if(lastX>=IVimage.getLeft() && lastX<=IVimage.getRight()
                                && lastY>=IVimage.getTop() && lastY<=IVimage.getBottom()){
                            index++;
                            PBlinks.setProgress(index);
                            MediaPlayer media = MediaPlayer.create(Links.this,R.raw.link);
                            media.start();
                            if (PBlinks.getProgress() == 100) {
                                Toast.makeText(Links.this, "100", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Links.this,Review.class);
                                startActivity(i);
                            } else {
                                TSnumber.setText(numbers[index]);
                                Bitmap bmpB = BitmapFactory.decodeByteArray(SplashActivity.ImageArray.get(index),0,SplashActivity.ImageArray.get(index).length);
                                IVimage.setImageBitmap(bmpB);
                                startX = startY = lastX = lastY = 0;
                                drawingLock1 = false;
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
}
