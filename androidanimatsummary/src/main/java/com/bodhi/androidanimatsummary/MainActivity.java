package com.bodhi.androidanimatsummary;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Build.VERSION_CODES.P;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_frame_animat)
    ImageView iv_frame_animat;

    @BindView(R.id.iv_tween_animat)
    ImageView iv_tween_animat;

    @BindView(R.id.iv_attribute_animat)
    ImageView iv_attribute_animat;

    @BindView(R.id.iv_attribute_track)
    ImageView iv_attribute_track;

    @BindView(R.id.iv_attribute_point_track)
    PointTrackView iv_attribute_point_track;

    @OnClick(R.id.iv_frame_animat)
    void onClickFrame() {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_frame_animat.getDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.f11),100);
        animationDrawable.start();
//        animationDrawable.stop();
    }

    @OnClick(R.id.iv_tween_animat)
    void onClickTween() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween_alpha_animat);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.tween_scale_animat);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.tween_set_animat);
        animation2.setFillAfter(true);//动画在播放结束时是否保持最终的状态
        iv_tween_animat.startAnimation(animation2);
    }


    @OnClick(R.id.iv_attribute_animat)
    void onClickAttribute() {
//        ObjectAnimator rotationAnim  = ObjectAnimator.ofFloat(iv_attribute_animat, "rotation", 0f, 360f);
//        rotationAnim.setDuration(1000);
//        rotationAnim.start();

//        ObjectAnimator alphaAnim  = ObjectAnimator.ofFloat(iv_attribute_animat, "alpha", 1.0f,0f);
//        alphaAnim.setRepeatCount(1);
//        alphaAnim.setRepeatMode(ObjectAnimator.REVERSE);
//        alphaAnim.setDuration(2000);
//        alphaAnim.start();

        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(iv_attribute_animat, "scaleX", 1.0f, 2.0f, 0.2f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(iv_attribute_animat, "scaleY", 1.0f, 2.0f, 0.2f);
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(iv_attribute_animat, "translationX", 0, 500);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(iv_attribute_animat, "translationY", 0, 450);
        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(scaleXAnim, scaleYAnim, transXAnim, transYAnim);//同步执行
        animatorSet.playSequentially(scaleXAnim,scaleYAnim,transXAnim,transYAnim);//顺序依次执行
        animatorSet.setDuration(3000);
        animatorSet.start();

    }

    @OnClick(R.id.iv_attribute_track)
    void onClickTrack(){
        Point startP=new Point(0,0);
        Point endP=new Point((getResources().getDisplayMetrics().widthPixels/2-iv_attribute_track.getWidth()/2),0);
        ValueAnimator valueAnimator  = ValueAnimator.ofObject(new PointTrackEvaluator(), startP, endP);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point currentPoint  = (Point) animation.getAnimatedValue();
                Log.e("pointTrack","currentPoint："+currentPoint.x+" "+currentPoint.y);
                iv_attribute_track.setTranslationX(currentPoint.x);
                iv_attribute_track.setTranslationY(currentPoint.y);
            }
        });
        valueAnimator.start();

    }

    @OnClick(R.id.iv_attribute_point_track)
    void onClickPointTrack() {
        iv_attribute_point_track.startAnimation();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
