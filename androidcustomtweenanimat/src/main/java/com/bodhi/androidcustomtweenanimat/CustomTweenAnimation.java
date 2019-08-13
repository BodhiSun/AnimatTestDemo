package com.bodhi.androidcustomtweenanimat;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/4 21:21
 * desc :
 */
public class CustomTweenAnimation extends Animation {
    private float centerX;
    private float centerY;
    // 定义动画的持续事件
    private int duration;
    private Camera camera = new Camera();

    public CustomTweenAnimation(float x, float y,int duration){
        this.centerX=x;
        this.centerY=y;
        this.duration=duration;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //设置动画的持续时间
        setDuration(duration);
        //设置动画结束后保留效果
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }


    /**
     * @param interpolatedTime 代表了动画的时间进行比。不管动画实际的持续时间如何，当动画播放时，该参数总是从 0 到 1。
     * @param t  该参数代表了补间动画在不同时刻对图形或组件的变形程度。
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //super.applyTransformation(interpolatedTime, t);
        camera.save();

        //根据 interpolatedTime 时间来控制X,Y,Z 上偏移
        camera.translate(100.f-100.f*interpolatedTime,150.f*interpolatedTime-150,80.0f - 80.0f * interpolatedTime);//目标组件在三维空间里变换。

        // 根据 interploatedTime 设置在 X 轴 和 Y 轴旋转
        camera.rotateX(360*interpolatedTime);//将组件沿 X 轴旋转。
        camera.rotateY(360*interpolatedTime);

        // 获取 Transformation 参数的 Matrix 对象
        Matrix matrix = t.getMatrix();
        camera.getMatrix(matrix);//将 Camera 所做的变换应用到指定的 matrix 上。
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
        camera.restore();

    }
}
