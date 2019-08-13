package com.bodhi.androidattributeanimat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/5 15:50
 * desc :
 */
public class MyColorView extends View {
    // 设置需要用到的变量
    public static final float RADIUS = 80f;// 圆的半径 = 100
    private Paint mPaint;// 绘图画笔

    // 设置背景颜色属性
    private String color;

    public String getColor() {
        return color == null ? "" : color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));

        // 调用了invalidate()方法,即画笔颜色每次改变都会刷新视图，然后调用onDraw()方法重新绘制圆
        invalidate();
    }

    public MyColorView(Context context) {
        this(context,null);
    }

    public MyColorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(100,100,RADIUS,mPaint);
    }
}
