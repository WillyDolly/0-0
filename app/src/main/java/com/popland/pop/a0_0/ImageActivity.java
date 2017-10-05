package com.popland.pop.a0_0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import Utils.Point;
import io.github.francoiscampbell.circlelayout.CircleLayout;

import static com.popland.pop.a0_0.NumberActivity.subNumberArray;

public class ImageActivity extends AppCompatActivity {
RelativeLayout RLduong;
CircleLayout CLimage;
ArrayList<ImageView> ivArr;
ArrayList<Point> pointArr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        RLduong = (RelativeLayout)findViewById(R.id.RLduong);
        CLimage = (CircleLayout)findViewById(R.id.CLimage);

        ivArr = new ArrayList<>();
        ArrayList<Integer> indexArr = new ArrayList<>();
        for(int i=0;i< subNumberArray.size();i++)
            Log.i("subNumberArray", subNumberArray.get(i)+",");
        for(int i=0;i< subNumberArray.size();i++){
            byte[] bytes = SplashActivity.ImageArray.get( subNumberArray.get(i));
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            ImageView IV = new ImageView(ImageActivity.this);
            IV.setBackgroundResource(R.drawable.round_shape);
            IV.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            IV.setImageBitmap(bmp);
            ivArr.add(IV);
            indexArr.add(i);
        }
        Collections.shuffle(indexArr);
        for(int i = 0; i< subNumberArray.size(); i++){
            ImageView IV = ivArr.get(indexArr.get(i));
            RelativeLayout.LayoutParams LP = new RelativeLayout.LayoutParams(200,200);
            CLimage.addView(IV,LP);
        }

        Duong duong = new Duong(this);
        RLduong.addView(duong);
    }

    int position = 0;
    class Duong extends View {
        Paint paint = new Paint();
        int index = 1;
        boolean drawing = false;
        float[] points = new float[(NumberActivity.subNumberArray.size()-1)*4];

        public Duong(Context context) {
            super(context);
            paint.setStrokeWidth(10);
            paint.setColor(Color.parseColor("#FF47F2DE"));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawLines(points, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
//                    for(int i=0;i<points.length;i++)
//                        points[i] = 0;
                    if ( event.getX() >= ivArr.get(0).getLeft() &&  event.getX() <= ivArr.get(0).getRight()
                            && event.getY() >= ivArr.get(0).getTop() && event.getY() <= ivArr.get(0).getBottom()) {
                        points[position] = (ivArr.get(0).getLeft() + ivArr.get(0).getRight()) / 2;
                        points[position+1] = (ivArr.get(0).getTop() + ivArr.get(0).getBottom()) / 2;
                        points[position+2] = points[position];
                        points[position+3] = points[position+1];
                        drawing = true;
                    }
                    invalidate();
                    return true;
                }

            if(event.getAction()==MotionEvent.ACTION_MOVE) {
                if(drawing) {
                    points[position+2] = event.getX();
                    points[position+3] = event.getY();
                    if (points[position+2] >= ivArr.get(index).getLeft() && points[position+2] <= ivArr.get(index).getRight()
                            && points[position+3] >= ivArr.get(index).getTop() && points[position+3] <= ivArr.get(index).getBottom()) {
                        points[position+2] = (ivArr.get(index).getLeft() + ivArr.get(index).getRight()) / 2;
                        points[position+3] = (ivArr.get(index).getTop() + ivArr.get(index).getBottom()) / 2;
                        MediaPlayer media = MediaPlayer.create(ImageActivity.this,R.raw.link);
                        media.start();
                        if((position+4)<points.length){
                            index++;
                            position += 4;
                            points[position] = points[position - 2];
                            points[position + 1] = points[position - 1];
                            points[position+2] = points[position];
                            points[position+3] = points[position+1];
                        }else{
                            MediaPlayer player = MediaPlayer.create(ImageActivity.this,R.raw.clap);
                            player.start();
                            Intent i = new Intent();
                            i.putExtra("RightWrong",true);
                            setResult(RESULT_OK,i);
                            finish();
                        }
                    }
                }
                invalidate();
                return true;
                }

            if(event.getAction()==MotionEvent.ACTION_UP){
                //wrong, back
            }
            return super.onTouchEvent(event);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
