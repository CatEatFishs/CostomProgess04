package com.lm.costomprogess04;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.provider.Telephony;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Lm on 2018/1/24.
 * Email:1002464056@qq.com
 */

public class ProgressBar extends View {
    private int mOutBackgroundColor = Color.RED;
    private int mInnerBackgroundColor = Color.BLUE;
    private int mProgressTextColor = Color.RED;
    private int mProgressTextSize = 18;
    private int mRoundWidth = 10;//px
    private int mCurrentProgress=0;
    private int mMax=100;

    private Paint mInnerPaint,mOutPaint,mTextPaint;
    public ProgressBar(Context context) {
        this(context,null);
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar);
        mOutBackgroundColor=typedArray.getColor(R.styleable.ProgressBar_outBackgroundColor,mOutBackgroundColor);
        mInnerBackgroundColor=typedArray.getColor(R.styleable.ProgressBar_innerBackgroundColor,mInnerBackgroundColor);
        mProgressTextColor=typedArray.getColor(R.styleable.ProgressBar_progerssTextColor,mProgressTextColor);
        mProgressTextSize=typedArray.getDimensionPixelSize(R.styleable.ProgressBar_progerssTextSize,sp2px(mProgressTextSize));
        mRoundWidth = (int) typedArray.getDimension(R.styleable.ProgressBar_roundWidth,dip2px(mRoundWidth));
        typedArray.recycle();

        mInnerPaint=new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerBackgroundColor);
        mInnerPaint.setStrokeWidth(mRoundWidth);
        mInnerPaint.setStyle(Paint.Style.STROKE);//空心

        mOutPaint=new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(mOutBackgroundColor);
        mOutPaint.setStrokeWidth(mRoundWidth);
        mOutPaint.setStyle(Paint.Style.STROKE);//空心

        mTextPaint=new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mProgressTextColor);
        mTextPaint.setTextSize(mProgressTextSize);
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,getResources().getDisplayMetrics());

    }

    private float dip2px(int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
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
        int center= getWidth()/2;
        //先画内圆
        canvas.drawCircle(center,center,center-mRoundWidth/2,mInnerPaint);
        //再画外圆弧
        RectF rectF=new RectF(0+mRoundWidth/2,0+mRoundWidth/2,getWidth()-mRoundWidth/2,getHeight()-mRoundWidth/2);
        if (mCurrentProgress==0){return;}
        float angle=(float)mCurrentProgress/mMax;
        canvas.drawArc(rectF,0,angle*360,false,mOutPaint);
        //再画文字
        String text=((int) (angle * 100)) + "%";
        Rect bouns=new Rect();
        mTextPaint.getTextBounds(text,0,text.length(),bouns);
        int x=getWidth()/2 - bouns.width()/2;
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy= (fontMetrics.bottom - fontMetrics.top)/2 -fontMetrics.bottom;
        int baseLine=getHeight()/2 + dy;
        canvas.drawText(text,x,baseLine,mTextPaint);
    }

    public synchronized void setMax(int max){
        if (max<0){
//            throw
        }
        this.mMax=max;
    }
    public synchronized void setProgress(int progress){
        if (progress < 0){

        }
        this.mCurrentProgress=progress;
        invalidate();
    }

    public void setTextSize(int size) {
        this.mProgressTextSize=size;
    }
}
