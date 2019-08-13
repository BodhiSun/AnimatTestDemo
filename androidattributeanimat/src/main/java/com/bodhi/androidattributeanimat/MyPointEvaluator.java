package com.bodhi.androidattributeanimat;

import android.animation.TypeEvaluator;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/5 14:22
 * desc : 参数说明:
 *        fraction：表示动画完成度（根据它来计算当前动画的值）
 *        startValue、endValue：动画的初始值和结束值
 */
public class MyPointEvaluator implements TypeEvaluator<MyPoint> {
    @Override
    public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {

        int x1 = ((Number)startValue.getX()).intValue();
        int x2 = ((Number)endValue.getX()).intValue();
        int x= (int) (x1+(x2-x1)*fraction);

        int y1 = ((Number)startValue.getY()).intValue();
        int y2 = ((Number)endValue.getY()).intValue();
        int y= (int) (y1+(y2-y1)*fraction);
        
        
        int color =0;
        if(fraction>0.5){
            color=endValue.getColor();
        }else{
            color=startValue.getColor();
        }
        return new MyPoint(color,x,y);
    }
}
