package com.lm.costomprogess04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lm on 2018/1/26.
 * Email:1002464056@qq.com
 */

public class LineView extends View {
    private Paint mPaint;
    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(100);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heigth = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > heigth ? heigth:width,width > heigth ? heigth:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawLine(0,0,getWidth()/2,0,mPaint);
        // 画直线
//        canvas.drawLine(0, 0, 400, 0, mPaint);
        canvas.drawLine(110,100,110,20,mPaint);
//        canvas.save();
//
//        Rect rect=new Rect(0,0,getWidth()/2,30);
//        canvas.clipRect(rect);
//        canvas.drawRect(0,0,getWidth()/2+2,50,mPaint);
//        canvas.restore();
    }

}
