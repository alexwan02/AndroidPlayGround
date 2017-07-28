package com.kotlin.alexwan.bethelview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/*
 * Copyright (c) 2017.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * BezierView
 * Created by alexwan on 28/07/2017.
 */

public class BezierView extends View {

    private final int CHANGE_FACTOR = 8;
    // device info
    private int density;
    private int displayWidth;
    private int displayHeight;

    // Center circle position
    private float centerX;
    private float centerY;

    // Moving circle position
    private float movingX;
    private float movingY;

    // Init radius
    private float initRadius;

    // Center info
    private float centerRadius;

    // Moving info
    private float movingRadius;


    // Max length limit
    private float limitLength;

    // Last touch position
    private float lastX, lastY;

    // Path to draw
    private Path path;

    // Paint to draw
    private Paint paint;

    // Animator
    private ValueAnimator animator;


    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        density = (int) dm.density;
        displayWidth = dm.widthPixels;
        displayHeight = dm.heightPixels;

        // Circle init
        centerX = displayWidth / 2;
        centerY = displayHeight / 2;
        centerRadius = density * 10;
        initRadius = centerRadius;

        // Moving circle
        movingX = centerX;
        movingY = centerY;
        movingRadius = centerRadius;

        limitLength = 5 * centerRadius;


        // Path init
        path = new Path();
        // Init paint
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setColor(Color.parseColor("#0000ff"));
        paint.setStyle(Paint.Style.FILL);


        // Init anim
        initAnim();

        updatePath();
    }

    private void initAnim() {
        animator = ValueAnimator.ofFloat(1f, 0f).setDuration(800);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //
                Log.i(BezierView.class.getSimpleName(), "onAnimationUpdate : value = " + animation.getAnimatedValue());
                movingX = centerX + (lastX - centerX) * (float)animation.getAnimatedValue();
                movingY = centerY + (lastY - centerY) * (float)animation.getAnimatedValue();
                centerRadius = initRadius - vectorToPoint(centerX , centerY , movingX , movingY) / CHANGE_FACTOR;
                movingRadius = initRadius - centerRadius;
                updatePath();
                invalidate();
            }
        });
    }

    private void updatePath() {
        if (centerX == movingX || centerY == movingY) {
            return;
        }

        double corners = Math.atan((movingY - centerY) / (movingX - centerX));

        float offsetX1 = (float) (centerRadius * Math.sin(corners));
        float offsetY1 = (float) (centerRadius * Math.cos(corners));

        float offsetX2 = (float) (movingRadius * Math.sin(corners));
        float offsetY2 = (float) (movingRadius * Math.cos(corners));


        float x1 = centerX - offsetX1;
        float y1 = centerY + offsetY1;

        float x2 = movingX - offsetX2;
        float y2 = movingY + offsetY2;

        float x3 = movingX + offsetX2;
        float y3 = movingY - offsetY2;

        float x4 = centerX + offsetX1;
        float y4 = centerY - offsetY1;

        float midX = (movingX + centerX) / 2;
        float midY = (movingY + centerY) / 2;

        path.reset();
        path.moveTo(x1, y1);
        path.quadTo(midX, midY, x2, y2);
        path.lineTo(x3, y3);
        path.quadTo(midX, midY, x4, y4);
        path.lineTo(x1, y1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);


        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);



        switch (heightMode){
            case MeasureSpec.UNSPECIFIED :
                Log.i(BezierView.class.getSimpleName() , "onMeasure ：heightMode = UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST :
                Log.i(BezierView.class.getSimpleName() , "onMeasure ：heightMode = AT_MOST");
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(300 , MeasureSpec.EXACTLY);
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(300 , MeasureSpec.EXACTLY);
                break;
            case MeasureSpec.EXACTLY:
                Log.i(BezierView.class.getSimpleName() , "onMeasure ：heightMode = EXACTLY ; heightSize = " + heightSize);
                break;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(BezierView.class.getSimpleName() ,
                "onLayout : getHeight = " + getHeight() + " ; getWidth = " + getWidth()
                        + " \n; getTop = " + getTop() + "， top = " + top
                        + " \n; getBottom = " + getBottom()
                        + " \n; getPaddingTop = " + getPaddingTop() + " ; measure height = " + getMeasuredHeight());
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        movingX = centerX;
        movingY = centerY;
        updatePath();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        float temp;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (x < centerX - centerRadius || x > centerX + centerRadius
                        || y < centerY - centerRadius || y > centerY + centerRadius) {
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                movingX = x;
                movingY = y;
                temp = vectorToPoint(centerX, centerY, movingX, movingY);
                if (temp > limitLength) {
                    float multiple = limitLength / temp;
                    movingX = (movingX - centerX) * multiple + centerX;
                    movingY = (movingY - centerY) * multiple + centerY;
                    temp = limitLength;
                }

                centerRadius = initRadius - temp / CHANGE_FACTOR;
                movingRadius = initRadius - centerRadius;

                updatePath();
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastX = movingX;
                lastY = movingY;
                animator.start();
                return true;
        }

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, centerRadius, paint);
        canvas.drawCircle(movingX, movingY, movingRadius, paint);
        canvas.drawPath(path, paint);
    }

    private float vectorToPoint(float X1, float Y1, float X2, float Y2) {
        return (float) Math.sqrt(Math.pow(Math.abs(X2 - X1), 2) + Math.pow(Math.abs(Y2 - Y1), 2));
    }
}
