package com.bodhi.androidanimatsummary;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/4 15:31
 * desc :
 */
public class PointTrackView extends View {

    private static final int RADIUS=20;
    private int color;
    private int radius = RADIUS;
    private Paint mPaint;
    private Paint linePaint;
    private Point currentPoint;
    private TimeInterpolator interpolatorType = new LinearInterpolator();
    private AnimatorSet animatorSet;

    public PointTrackView(Context context) {
        this(context,null);
    }

    public PointTrackView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public PointTrackView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.TRANSPARENT);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(currentPoint==null){
            currentPoint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);
        }else{
            drawCircle(canvas);
        }

        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(10,getHeight()/2,getWidth(),getHeight()/2,linePaint);
        canvas.drawLine(10,getHeight()/2-150,10,getHeight()/2+150,linePaint);
        canvas.drawPoint(currentPoint.x,currentPoint.y,linePaint);
    }

    private void drawCircle(Canvas canvas) {
        int x = currentPoint.x;
        int y = currentPoint.y;
        canvas.drawCircle(x,y,radius,mPaint);
    }

    public void startAnimation(){

        Point startP=new Point(radius,radius);
        Point endP=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator valueAnimator  = ValueAnimator.ofObject(new PointTrackEvaluator(), startP, endP);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        ObjectAnimator animColor =ObjectAnimator.ofObject(this,"color",new ArgbEvaluator(), Color.GREEN,Color.YELLOW,Color.BLUE,Color.WHITE,Color.RED);
        animColor.setRepeatCount(-1);
        animColor.setRepeatMode(ValueAnimator.REVERSE);

        ValueAnimator animScale = ValueAnimator.ofFloat(20f, 80f, 60f, 10f, 35f,55f,10f);
        animScale.setRepeatCount(-1);
        animScale.setRepeatMode(ValueAnimator.REVERSE);
        animScale.setDuration(5000);
        animScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Number number = (Number) animation.getAnimatedValue();
                radius=number.intValue();
            }
        });

        animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).with(animColor).with(animScale);
        animatorSet.setDuration(5000);
        animatorSet.setInterpolator(interpolatorType);
        animatorSet.start();
    }

    public void setInterpolatorType(int type ) {
        switch (type) {
            case 1:
                interpolatorType = new BounceInterpolator();
                break;
            case 2:
                interpolatorType = new AccelerateDecelerateInterpolator();
                break;
            case 3:
                interpolatorType = new DecelerateInterpolator();
                break;
            case 4:
                interpolatorType = new AnticipateInterpolator();
                break;
            case 5:
                interpolatorType = new LinearInterpolator();
                break;
            case 6:
                interpolatorType=new LinearOutSlowInInterpolator();
                break;
            case 7:
                interpolatorType = new OvershootInterpolator();
            default:
                interpolatorType = new LinearInterpolator();
                break;
        }
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        mPaint.setColor(this.color);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void pauseAnimation(){
        if(animatorSet!=null)
            animatorSet.pause();
    }

    public void stopAnimation(){
        if(animatorSet!=null){
            animatorSet.cancel();
            clearAnimation();
        }
    }
}
