package com.bodhi.androidanimatsummary;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/4 14:46
 * desc :小球在屏幕上以 y=sin(x) 的数学函数轨迹运行
 */
public class PointTrackEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //第一个参数fraction 代表当前动画完成的百分比,第二个和第三个参数代表动画的初始值和结束值
        Point startPoint= (Point) startValue;
        Point endPoint= (Point) endValue;

        int x = (int) (startPoint.x+(endPoint.x-startPoint.x)*fraction);
        int y = (int) ((Math.sin(x*Math.PI/180)*100)+endPoint.y/2);

        //用x 和 y 产生一个新的点（Point对象）返回
        return new Point(x,y);
    }
}
