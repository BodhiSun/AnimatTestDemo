package com.bodhi.androidattributeanimat;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/5 14:38
 * desc :
 */
public class MyPointView extends View {
    // 设置需要用到的变量
    public static final float RADIUS = 70f;// 圆的半径 = 70
    private MyPoint currentPoint;// 当前点坐标
    private Paint mPaint;// 绘图画笔
    private ValueAnimator animator;

    public MyPointView(Context context) {
        this(context,null);
    }

    public MyPointView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    // 绘制逻辑:先在初始点画圆,通过监听当前坐标值(currentPoint)的变化,每次变化都调用onDraw()重新绘制圆,从而实现圆的平移动画效果
    @Override
    protected void onDraw(Canvas canvas) {

        // 如果当前点坐标为空(即第一次)
        if (currentPoint==null) {
            // 创建一个点对象(坐标是(70,70))
            currentPoint = new MyPoint(Color.BLUE,RADIUS,RADIUS);

            // 在该点画一个圆:圆心 = (70,70),半径 = 70
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            mPaint.setColor(currentPoint.getColor());
            canvas.drawCircle(x,y,RADIUS,mPaint);

            // (重点关注)将属性动画作用到View中
            // 步骤1:创建初始动画时的对象点  & 结束动画时的对象点
            MyPoint myObject1 = new MyPoint(Color.GREEN,RADIUS,RADIUS);
            MyPoint myObject2 = new MyPoint(Color.RED,900f,300f);
            MyPointEvaluator myObjectEvaluator = new MyPointEvaluator();

            // 步骤2:创建动画对象 & 设置初始值 和 结束值
            animator = ValueAnimator.ofObject(myObjectEvaluator, myObject1, myObject2);

            // 步骤3：设置动画参数
            animator.setDuration(3000);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            //通过 值 的更新监听器，将改变的对象手动赋值给当前对象
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPoint = (MyPoint) animation.getAnimatedValue();
                    System.out.println(currentPoint.getX()+"---"+currentPoint.getY());

                    invalidate();
                }
            });
//            animator.start();
        }else{
            // 如果坐标值不为null,则画圆
            //所以坐标值每改变一次,就会调用onDraw()一次,就会画一次圆,从而实现动画效果
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            mPaint.setColor(currentPoint.getColor());
            canvas.drawCircle(x,y,RADIUS,mPaint);

        }
    }

    public void startAnimat(){
        if(animator!=null&&!animator.isRunning()){
            animator.start();
        }
    }

    public void stopAnimat(){
        if(animator!=null){
            animator.cancel();
        }
    }
}
