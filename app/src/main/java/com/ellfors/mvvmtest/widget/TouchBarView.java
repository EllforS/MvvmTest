package com.ellfors.mvvmtest.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.ellfors.mvvmtest.app.MyApp;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * TouchBarView
 * 2020-11-26 16:25
 */
public class TouchBarView extends View {

    private int mCircleStartX;
    private int mCircleStartY;

    private int mLargeCircleRadius;
    private int mLargeCircleBg;
    private int mLargeCircleStroke;
    private int mLargeCircleStrokeWidth;

    private int mSmallCircleRadius;
    private int mSmallCircleBg;

    private int mLineWidth;
    private int mLineNormalColor;
    private int mLineSelectedColor;
    private int mLineLength;

    private float mValueText;
    private int mValueSize;
    private int mValueColor;
    private int mValueOffset;

    private int downX;

    public TouchBarView(Context context) {
        super(context);
        init(context, null);
    }

    public TouchBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mLargeCircleRadius = dp2px(9);
        mLargeCircleBg = 0XFFFFFFFF;
        mLargeCircleStroke = 0XFFFFECE6;
        mLargeCircleStrokeWidth = dp2px(1);

        mSmallCircleRadius = dp2px(5);
        mSmallCircleBg = 0XFFFF6633;

        mCircleStartX = mLargeCircleRadius;
        mCircleStartY = mLargeCircleRadius + dp2px(1);

        mLineWidth = dp2px(4);
        mLineNormalColor = 0XFFE6E6E6;
        mLineSelectedColor = 0XFFFF6633;
        mLineLength = dp2px(266);

        mValueText = 0;
        mValueSize = sp2px(13);
        mValueColor = 0XFF333333;
        mValueOffset = mLineLength + mLineWidth + dp2px(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = dp2px(315);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = dp2px(20);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawLine(canvas);
        drawLargeCircle(canvas);
        drawSmallCircle(canvas);
        drawValue(canvas);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                downX = (int) event.getX();
                downX = Math.min(downX, mLineLength + mLineWidth - mLargeCircleRadius);
                downX = Math.max(downX, mLargeCircleRadius);
                break;
            default:
                break;
        }
        mCircleStartX = downX;
        mValueText = ((float) (mCircleStartX - mLargeCircleRadius)) / ((float) (mLineLength + mLineWidth - mLargeCircleRadius * 2));
        invalidate();

        return true;
    }

    private void drawLine(Canvas mCanvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mLineSelectedColor);
        mCanvas.drawCircle((float) mLineWidth / 2, mCircleStartY, (float) mLineWidth / 2, paint);

        paint = new Paint();
        paint.setColor(mLineSelectedColor);
        paint.setStrokeWidth(mLineWidth);
        mCanvas.drawLine((float) mLineWidth / 2, mCircleStartY, mCircleStartX, mCircleStartY, paint);

        paint = new Paint();
        paint.setColor(mLineNormalColor);
        paint.setStrokeWidth(mLineWidth);
        mCanvas.drawLine(mCircleStartX, mCircleStartY, mLineLength + (float) mLineWidth / 2, mCircleStartY, paint);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mLineNormalColor);
        mCanvas.drawCircle(mLineLength + (float) mLineWidth / 2, mCircleStartY, (float) mLineWidth / 2, paint);
    }

    private void drawLargeCircle(Canvas mCanvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(mLargeCircleStrokeWidth);
        paint.setColor(mLargeCircleStroke);
        paint.setStyle(Paint.Style.STROKE);
        mCanvas.drawCircle(mCircleStartX, mCircleStartY, mLargeCircleRadius, paint);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mLargeCircleBg);
        mCanvas.drawCircle(mCircleStartX, mCircleStartY, mLargeCircleRadius, paint);
    }

    private void drawSmallCircle(Canvas mCanvas) {
        Paint paint = new Paint();
        paint.setColor(mSmallCircleBg);
        paint.setStyle(Paint.Style.FILL);
        mCanvas.drawCircle(mCircleStartX, mCircleStartY, mSmallCircleRadius, paint);
    }

    private void drawValue(Canvas mCanvas) {
        Paint paint = new Paint();
        paint.setTextSize(mValueSize);
        paint.setColor(mValueColor);
        paint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
        paint.getFontMetrics(fontMetrics);
        float offset = (fontMetrics.descent + fontMetrics.ascent) / 2;

        mCanvas.drawText(getText(), mValueOffset, mCircleStartY - offset, paint);
    }

    public String getText() {
        DecimalFormat formater = new DecimalFormat(",#0");
        // 设置最大小数位数
        formater.setMaximumFractionDigits(0);
        // 设置分组大小，也就是显示逗号的位置
        formater.setGroupingSize(3);
        // 设置四舍五入的模式
        formater.setRoundingMode(RoundingMode.FLOOR);
        return formater.format(mValueText * 100) + "%";
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, MyApp.context.getResources().getDisplayMetrics());
    }

    private int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, MyApp.context.getResources().getDisplayMetrics());
    }
}