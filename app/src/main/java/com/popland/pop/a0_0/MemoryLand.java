package com.popland.pop.a0_0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

import java.util.ArrayList;

public class MemoryLand extends AppCompatActivity {
TileView tileView;
 MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tileView = new TileView(this);
        tileView.setSize(480,3520);
        tileView.addDetailLevel(1f,"land/1000/%d_%d.png");
        tileView.addDetailLevel(0.5f,"land/500/%d_%d.png");
        tileView.addDetailLevel(0.25f,"land/250/%d_%d.png");
        tileView.addDetailLevel(0.125f,"land/125/%d_%d.png");
        tileView.setScaleLimits(0,5);
        tileView.setScale(5);
        tileView.defineBounds(0,0,29,219);
        tileView.setShouldRenderWhilePanning(true);
        tileView.scrollTo(positions.get(0)[0],positions.get(0)[1]);
        setContentView(tileView);

       int index = 0;
       for(double[] d : positions){
        //EasyFlipView flipView = new EasyFlipView(this);
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.setTag(index);

        TextView TV = new TextView(this);
        TV.setGravity(Gravity.CENTER);
        TV.setText(index+"");
        TV.setTextColor(Color.BLUE);
        TV.setTextSize(20);
        LL.addView(TV);

        ImageView IV = new ImageView(this);
        IV.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        byte[] bytes = SplashActivity.ImageArray.get(index);
        Bitmap bp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        IV.setImageBitmap(bp);
        LL.addView(IV);

        tileView.addMarker(LL,d[0],d[1],-0.5f,-1f);
        index++;
     }
      tileView.setMarkerTapListener(markerTapListener);
     mp = MediaPlayer.create(this,R.raw.mozart);
     mp.start();
     mp.setLooping(true);
    }

 int move = 0;
    MarkerLayout.MarkerTapListener markerTapListener = new MarkerLayout.MarkerTapListener() {
     @Override
     public void onMarkerTap(View view, int x, int y) {
      if(( (int)view.getTag()) == move){
        tileView.scrollToAndCenter(positions.get(move+1)[0],positions.get(move+1)[1]);
       Animation animation = AnimationUtils.loadAnimation(MemoryLand.this,R.anim.scale);
       view.startAnimation(animation);
        move++;
      }
     }
    };

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
 getMenuInflater().inflate(R.menu.navigation,menu);
  menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
   @Override
   public boolean onMenuItemClick(MenuItem item) {
    Intent i = new Intent(MemoryLand.this,Review.class);
    startActivity(i);
    return false;
   }
  });
  return true;
 }

 ArrayList<double[]> positions = new ArrayList<>();
//    {   positions.add(new double[]{1,217});
//        positions.add(new double[]{16,217});
//        positions.add(new double[]{25,217});
//        positions.add(new double[]{28,214});
//        positions.add(new double[]{27,204});
//        positions.add(new double[]{21,205});
//        positions.add(new double[]{16,205});
//        positions.add(new double[]{12,205});
//        positions.add(new double[]{10,208});
//        positions.add(new double[]{4,206});

       {positions.add(new double[]{2,194});
        positions.add(new double[]{9,192});
        positions.add(new double[]{16,194});
        positions.add(new double[]{21,196});
        positions.add(new double[]{25,196});
        positions.add(new double[]{27,186});
        positions.add(new double[]{25,180});
        positions.add(new double[]{18,182});
        positions.add(new double[]{11,184});
        positions.add(new double[]{4,180});

        positions.add(new double[]{2,171});
        positions.add(new double[]{7,172});
        positions.add(new double[]{12,174});
        positions.add(new double[]{21,171});
        positions.add(new double[]{28,172});
        positions.add(new double[]{26,169});
        positions.add(new double[]{27,161});
        positions.add(new double[]{20,163});
        positions.add(new double[]{12,160});
        positions.add(new double[]{2,164});

        positions.add(new double[]{1,154});
        positions.add(new double[]{5,153});
        positions.add(new double[]{9,155});
        positions.add(new double[]{16,150});
        positions.add(new double[]{23,150});
        positions.add(new double[]{27,152});
        positions.add(new double[]{27,141});
        positions.add(new double[]{16,138});
        positions.add(new double[]{9,145});
        positions.add(new double[]{5,140});

        positions.add(new double[]{3,131});
        positions.add(new double[]{9,129});
        positions.add(new double[]{15,135});
        positions.add(new double[]{21,129});
        positions.add(new double[]{26,124});
        positions.add(new double[]{24,127});
        positions.add(new double[]{15,115});
        positions.add(new double[]{15,125});
        positions.add(new double[]{5,120});
        positions.add(new double[]{5,125});

        positions.add(new double[]{3,116});
        positions.add(new double[]{8,109});
        positions.add(new double[]{14,111});
        positions.add(new double[]{21,109});
        positions.add(new double[]{27,116});
        positions.add(new double[]{25,104});
        positions.add(new double[]{20,104});
        positions.add(new double[]{15,104});
        positions.add(new double[]{10,104});
        positions.add(new double[]{5,104});

        positions.add(new double[]{1,86});
        positions.add(new double[]{12,86});
        positions.add(new double[]{16,92});
        positions.add(new double[]{19,93});
        positions.add(new double[]{28,91});
        positions.add(new double[]{27,86});
        positions.add(new double[]{27,82});
        positions.add(new double[]{15,80});
        positions.add(new double[]{16,85});
        positions.add(new double[]{8,83});

        positions.add(new double[]{2,78});
        positions.add(new double[]{6,77});
        positions.add(new double[]{9,77});
        positions.add(new double[]{15,77});
        positions.add(new double[]{18,73});
        positions.add(new double[]{24,70});
        positions.add(new double[]{20,71});
        positions.add(new double[]{15,72});
        positions.add(new double[]{11,72});
        positions.add(new double[]{5,70});

        positions.add(new double[]{3,55});
        positions.add(new double[]{11,55});
        positions.add(new double[]{19,55});
        positions.add(new double[]{24,54});
        positions.add(new double[]{27,54});
        positions.add(new double[]{24,52});
        positions.add(new double[]{27,51});
        positions.add(new double[]{26,44});
        positions.add(new double[]{17,44});
        positions.add(new double[]{7,44});

        positions.add(new double[]{2,38});
        positions.add(new double[]{7,32});
        positions.add(new double[]{10,32});
        positions.add(new double[]{13,34});
        positions.add(new double[]{19,36});
        positions.add(new double[]{26,26});
        positions.add(new double[]{20,26});
        positions.add(new double[]{18,27});
        positions.add(new double[]{14,28});
        positions.add(new double[]{2,23});

        positions.add(new double[]{2,15});
        positions.add(new double[]{8,11});
        positions.add(new double[]{13,14});
        positions.add(new double[]{14,9});
        positions.add(new double[]{22,11});
        positions.add(new double[]{24,6});
        positions.add(new double[]{26,2});
        positions.add(new double[]{17,4});
        positions.add(new double[]{11,5});
        positions.add(new double[]{5,2});}
}
