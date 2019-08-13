package com.bodhi.androidsenioranimat;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*Tween动画，属性动画，帧动画，CircularReveal，Activity转场动画，5.0新转场动画，Interpolator插值器
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countNum)
    TextView countNum;

    @BindView(R.id.tween_animat)
    ImageView tween_animat;

    @BindView(R.id.attribute_animat)
    ImageView attribute_animat;

    @BindView(R.id.mCustomView)
    MyCustomView mCustomView;

    @BindView(R.id.path_animat)
    ImageView path_animat;

    @BindView(R.id.frame_animat)
    ImageView frame_animat;

    @BindView(R.id.CircularReveal_animat)
    ImageView CircularReveal_animat;

    @OnClick(R.id.tween_animat)
    void onClickTween(){
        //TweenAnimation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween_animat);
        tween_animat.startAnimation(animation);
    }


    @OnClick(R.id.attribute_animat)
    void onClickAttribute(){
        //AttributeAnimation ValueAnimator-数值发生器
//        ValueAnimator animator = ValueAnimator.ofFloat(1,360);
//        animator.setDuration(1500);
//        animator.setInterpolator(new AccelerateInterpolator());
//        animator.setRepeatCount(1);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float)animation.getAnimatedValue();
//
//                attribute_animat.setRotationY(value);
//
//                attribute_animat.setScaleX(value/100);
//                attribute_animat.setScaleY(value/100);
//
//                //也可以将数字动画地从0增加到360
//                countNum.setText(String.valueOf(value));
//            }
//        });
//        animator.start();

        //AttributeAnimation ObjectAnimator
        ObjectAnimator oAnimator  = ObjectAnimator.ofFloat(attribute_animat, "rotationY", 0, 359);
        oAnimator.setDuration(1000);
        oAnimator.setRepeatCount(1);
        oAnimator.setRepeatMode(ValueAnimator.REVERSE);
        oAnimator.start();

    }

    @OnClick(R.id.mCustomView)
    void onClickCustomView(){
        //AttributeAnimation ObjectAnimator
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCustomView, "fraction", 0, 180);
        animator.setDuration(2000);
        animator.setRepeatCount(0);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    @OnClick(R.id.path_animat)
    void onClickPathAnimat(){
        //AttributeAnimation ObjectAnimator 区别就是ObjectAnimator.ofFloat方法的重载，传四个参数
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(100,30);
        path.lineTo(200,80);
        path.lineTo(300,150);
        path.lineTo(400,240);
        path.lineTo(500,350);
        path.lineTo(600,480);
        path.lineTo(700,630);

        ObjectAnimator animator = ObjectAnimator.ofFloat(path_animat, View.X,View.Y,path);
        animator.setDuration(2000);
        animator.setRepeatCount(1);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    @OnClick(R.id.frame_animat)
    void onClickFrameAnimat(){
        //FrameAnimation
        frame_animat.setImageResource(R.drawable.frame_animat);
        AnimationDrawable anim  = (AnimationDrawable) frame_animat.getDrawable();
        anim.start();
    }

    @OnClick(R.id.CircularReveal_animat)
    void onClickCircularRevealAnimat(){
//        Animator anim  = ViewAnimationUtils.createCircularReveal(CircularReveal_animat, CircularReveal_animat.getWidth() / 2, CircularReveal_animat.getHeight() / 2, CircularReveal_animat.getWidth(), 0);
//        anim.setDuration(2000);
//        anim.start();

        Animator anim2 = ViewAnimationUtils.createCircularReveal(CircularReveal_animat, 0, 0, 0, (float) Math.hypot(CircularReveal_animat.getWidth(), CircularReveal_animat.getHeight()));
        anim2.setDuration(2000);
        anim2.start();

    }

    @OnClick(R.id.countNum)
    void onActivityTransAnimat(){

        Intent intent = new Intent(this, Main2Activity.class);
        //传统转场动画
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);

        /*5.0新转场动画
        * Explode,下一个页面的元素从四面八方进入，最终形成完整的页面
        * Slide,下一个页面元素从底部依次向上运动，最终形成完整的页面
        * Fade,下一个页面元素渐变出现，最终形成完整的页面
        * */
//        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        //share，共享元素动画 在跳转的两个Activity之间，如果有相同的View元素，那么，两个元素就可以设置成共享状态
         startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this,
                 Pair.create((View)countNum,"fab"),
                 Pair.create((View)tween_animat,"fab2"))
                 .toBundle());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



    }
}
