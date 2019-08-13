package com.bodhi.androidcustomtweenanimat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TweenAnimatJavaCodeActivity extends AppCompatActivity {

    int i =0;

    @BindView(R.id.tween_animat)
    ImageView tween_animat;

    @OnClick(R.id.tween_animat)
    void onTweenAnimat(){
        switch (i++ % 5) {
            case 0:
                TranslateAnimation translateAnimation = creatTranslateAnimat();
                tween_animat.startAnimation(translateAnimation);
                //动画监听
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //动画开始时执行
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //动画结束时执行
                        tween_animat.startAnimation(creatScaleAnimat());
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        ///动画重复时执行
                    }
                });

                break;
            case 1:
                tween_animat.startAnimation(creatScaleAnimat());

                break;
            case 2:
                tween_animat.startAnimation(creatRotateAnimat());

                break;
            case 3:
                tween_animat.startAnimation(creatAlphaAnimat());

                break;
            case 4:
                tween_animat.startAnimation(creatSetAnimat());

                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animat_java_code);
        ButterKnife.bind(this);
    }

    private AnimationSet creatSetAnimat() {

        // 组合动画设置
        AnimationSet setAnimation = new AnimationSet(true);
        setAnimation.setRepeatMode(Animation.REVERSE);
        setAnimation.setRepeatCount(1);

        TranslateAnimation translateAnimation = creatTranslateAnimat();
        ScaleAnimation scaleAnimation = creatScaleAnimat();
        RotateAnimation rotateAnimation = creatRotateAnimat();
        AlphaAnimation alphaAnimation = creatAlphaAnimat();

        setAnimation.addAnimation(translateAnimation);
        setAnimation.addAnimation(scaleAnimation);
        setAnimation.addAnimation(rotateAnimation);
        setAnimation.addAnimation(alphaAnimation);

        return setAnimation;
    }

    /**
     *  1. fromAlpha:动画开始时视图的透明度(取值范围: -1 ~ 1)
     *  2. toAlpha:动画结束时视图的透明度(取值范围: -1 ~ 1)
     */
    private AlphaAnimation creatAlphaAnimat() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(3000);
        return alphaAnimation;
    }

    /**
     * 1. fromDegrees ：动画开始时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
     * 2. toDegrees ：动画结束时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
     * 3. pivotXType：旋转轴点的x坐标的模式
     * 4. pivotXValue：旋转轴点x坐标的相对值
     * 5. pivotYType：旋转轴点的y坐标的模式
     * 6. pivotYValue：旋转轴点y坐标的相对值
     * pivotXType = Animation.ABSOLUTE:旋转轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
     * pivotXType = Animation.RELATIVE_TO_SELF:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
     *   pivotXType = Animation.RELATIVE_TO_PARENT:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
     */
    private RotateAnimation creatRotateAnimat() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        return rotateAnimation;
    }

    /**
     * 1. fromX ：动画在水平方向X的结束缩放倍数
     * 2. toX ：动画在水平方向X的结束缩放倍数
     * 3. fromY ：动画开始前在竖直方向Y的起始缩放倍数
     * 4. toY：动画在竖直方向Y的结束缩放倍数
     * 5. pivotXType:缩放轴点的x坐标的模式
     * 6. pivotXValue:缩放轴点x坐标的相对值
     * 7. pivotYType:缩放轴点的y坐标的模式
     * 8. pivotYValue:缩放轴点y坐标的相对值
     * pivotXType = Animation.ABSOLUTE:缩放轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
     * pivotXType = Animation.RELATIVE_TO_SELF:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
     *    pivotXType = Animation.RELATIVE_TO_PARENT:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
     */
    private ScaleAnimation creatScaleAnimat() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 2, 0, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        return scaleAnimation;
    }

    /**
     * 创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
     *  参数分别是：
     *  1. fromXDelta ：视图在水平方向x 移动的起始值
     *  2. toXDelta ：视图在水平方向x 移动的结束值
     *  3. fromYDelta ：视图在竖直方向y 移动的起始值
     *  4. toYDelta：视图在竖直方向y 移动的结束值
     */
    private TranslateAnimation creatTranslateAnimat() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 500);
        translateAnimation.setDuration(3000);
        return translateAnimation;
    }
}
