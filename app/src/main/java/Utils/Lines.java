package Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by hai on 10/09/2017.
 */

public class Lines extends View {
    Paint paint = new Paint();
    float startX, startY, lastX, lastY;
    public Lines(Context context,float startX,float startY,float lastX,float lastY) {
        super(context);
        paint.setStrokeWidth(10);
        paint.setColor(Color.parseColor("#ff0900"));
        this.startX = startX;
        this.startY = startY;
        this.lastX = lastX;
        this.lastY = lastY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, startY, lastX, lastY, paint);
    }
}
