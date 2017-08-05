package com.hoaiutc95.fragment_menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.hoaiutc95.doan.R;

/**
 * Created by Thu Hoai on 4/5/2017.
 */

public class DrawingView extends View {
    private Path drawPath; // vẽ đường đi
    private Paint drawPaint, canvasPaint;
    private int paintColor = 0xFF660000;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private float brushSize, lastBrushSize;
    private boolean erase=false;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    //setup drawing
    private void setupDrawing() {
        //Thiet lap cac doi tuong tuong tac tren canvas
        drawPath = new Path();
        drawPaint = new Paint();
        canvasPaint = new Paint(Paint.DITHER_FLAG);

        drawPaint.setColor(paintColor);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setAntiAlias(true);     //lam muot net ve
        drawPaint.setStrokeCap(Paint.Cap.ROUND);//hai đầu mút đoạn thảng
        drawPaint.setStrokeWidth(10);
        drawPaint.setStyle(Paint.Style.STROKE); //chi ve duong

        brushSize = getResources().getInteger(R.integer.small_size);
       lastBrushSize = brushSize;
        drawPaint.setStrokeWidth(brushSize);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view: ve khung va ve duong dan
        canvas.drawPath(drawPath, drawPaint);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor(String newColor) {
        //set color
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public void setBrushSize(float newSize) {
        //update size
        invalidate();
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize = pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize) {
        lastBrushSize = lastSize;
    }

    public float getLastBrushSize() {
        return lastBrushSize;
    }

    public void setErase(boolean isErase){
       //set erase true or false
        erase=isErase;
        if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);

    }
    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
}
//drawing path:duong dan theo doi hanh dong ve tren canvas dung drawpaint de ve len canvas(net ve), dung canvaspaint de ve(ve hoan chinh)
//Lớp Path giữ một tập hợp lệnh Vector – Drawing như là lines(các đường), rectangle (các HCN) và curves (các đường cong)