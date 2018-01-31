package com.lm.costomprogess04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lm on 2018/1/25.
 * Email:1002464056@qq.com
 */

public class ShapView extends View {
    private Shape mCurrentShap=Shape.Circle;
    private Paint mPaint;
    private Path mPath;
    public ShapView(Context context) {
        this(context,null);
    }

    public ShapView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);

    }

    public void exchage() {
        switch (mCurrentShap) {
            case Circle:
                mCurrentShap = Shape.Square;
                break;
            case Square:
                mCurrentShap = Shape.Triangle;
                break;
            case Triangle:
                // 画三角  Path 画路线
                mCurrentShap = Shape.Circle;
                break;
        }
        invalidate();
    }

    public enum Shape{
        Circle, Square, Triangle
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heigth = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width>heigth?heigth:width,width>heigth?heigth:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mCurrentShap){
            case Circle:
                // 画圆形
                int center = getWidth() / 2;
                mPaint.setColor(Color.YELLOW);
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                // 画正方形
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                // 画三角  Path 画路线
                mPaint.setColor(Color.RED);
                if (mPath == null) {
                    // 画路径
                    mPath = new Path();
                    mPath.moveTo(getWidth() / 2, 0);
                    mPath.lineTo(0, (float) ((getWidth()/2)*Math.sqrt(3)));
                    mPath.lineTo(getWidth(), (float) ((getWidth()/2)*Math.sqrt(3)));
                    // path.lineTo(getWidth()/2,0);
                    mPath.close();// 把路径闭合
                }
                canvas.drawPath(mPath, mPaint);
                break;
        }



    }

}
