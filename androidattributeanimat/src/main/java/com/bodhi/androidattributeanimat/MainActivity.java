package com.bodhi.androidattributeanimat;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv1)
    TextView iv1;

    @BindView(R.id.myView)
    MyPointView myView;

    @BindView(R.id.iv2)
    ImageView iv2;

    @BindView(R.id.iv3)
    ImageView iv3;

    @BindView(R.id.myColorView)
    MyColorView myColorView;

    @BindView(R.id.btn1)
    Button btn1;

    @BindView(R.id.btn2)
    Button btn2;

    @BindView(R.id.btn3)
    Button btn3;

    @BindView(R.id.btn4)
    Button btn4;

    //ValueAnimator -default TypeEvaluator
    @OnClick(R.id.iv1)
    void onClickIv1(){
        // ofInt（）作用有两个
        // 1. 创建动画实例
        // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和3,表示将值从0平滑过渡到3
        ValueAnimator anim  = ValueAnimator.ofInt(iv1.getLayoutParams().width,800);

        anim.setDuration(3000);

        // 设置动画延迟播放时间
        anim.setStartDelay(10);

        //ValueAnimator.INFINITE时 动画无限重复
        anim.setRepeatCount(1);

        anim.setRepeatMode(ValueAnimator.REVERSE);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获得改变后的值
                int currentValue = (Integer)animation.getAnimatedValue();
                System.out.println(currentValue);

                //将改变后的值赋给对象的属性值
                iv1.getLayoutParams().width=currentValue;

                //刷新视图，即重新绘制，从而实现动画效果
                iv1.requestLayout();
            }
        });
        anim.start();
    }

    //ValueAnimator -Custom TypeEvaluator
    int i=0;
    @OnClick(R.id.myView)
    void onClickMyView(){
        // 创建初始动画时的对象  & 结束动画时的对象
        if (i++%2==0) {
            myView.startAnimat();
        }else{
            myView.stopAnimat();
        }
    }

    //objectAnimator -XML
    @OnClick(R.id.iv2)
    void onCLickIv2(){
        // 载入XML动画
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.objectanimat_alpha);

        // 设置动画对象
        animator.setTarget(iv2);

        animator.start();
    }

    //ObjectAnimator -JAVA
    @OnClick(R.id.iv3)
    void onCLickIv3(){
        //Alpha,TranslationX,TranslationY,ScaleX,ScaleY,Rotation,RotationX,RotationY
        ObjectAnimator rotation = ObjectAnimator.ofFloat(iv3, "rotationX", 0f, 360f);
        rotation.setDuration(3000);
        rotation.start();
    }

    //手动设置对象类属性的set（） & get（）方法一:通过继承原始类，直接给类加上该属性的 get（）&  set（）
    @OnClick(R.id.myColorView)
    void onClickMyColorView(){
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果
        ObjectAnimator anim = ObjectAnimator.ofObject(myColorView,"color",new MyColorEvaluator(),"#0000FF","#FF0000");
        anim.setDuration(5000);
        anim.setRepeatCount(3);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();

    }

    //手动设置对象类属性的set（） & get（）方法二:通过包装原始动画对象 即用一个类来包装原始对象
    @OnClick(R.id.btn1)
    void onClickBtn(){
        ViewWrapper wrapper = new ViewWrapper(btn1);
//        ObjectAnimator.ofInt(btn1, "width", 800).setDuration(3000).start();//无效
        ObjectAnimator.ofInt(wrapper, "width", wrapper.getWidth(),1000).setDuration(3000).start();
    }

    //AnimatorSet -JAVA
    @OnClick(R.id.btn2)
    void onClickBtn2(){
        AnimatorSet animSet = new AnimatorSet();

        ObjectAnimator translation = ObjectAnimator.ofFloat(btn2, "translationX",0,300,0);

        ObjectAnimator rotate = ObjectAnimator.ofFloat(btn2, "rotation", 0f, 360f);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(btn2, "alpha", 1f, 0f, 1f);

        ObjectAnimator scale = ObjectAnimator.ofFloat(btn2, "scaleX", 1, 2, 1);

        //play：播放当前动画 with：将现有动画和传入的动画同时执行 after：将现有动画插入到传入的动画之后执行 before：将现有动画插入到传入的动画之前执行
        animSet.play(translation).with(alpha).after(scale).before(rotate);

        animSet.setDuration(5000);
        animSet.start();
    }

    //AnimatorSet -XML
    @OnClick(R.id.btn3)
    void onClickBtn3(){
        AnimatorSet animator = (AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.set_animation);
        animator.setTarget(btn3);
        animator.start();

        //AnimatorListenerAdapter区别于AnimatorListener不用必须重写4个时刻方法 解决实现接口繁琐
        animator.addListener(new AnimatorListenerAdapter() {

            // 如想只想监听动画结束时刻，就只需要单独重写该方法就可以
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btn4.animate().alpha(0.1f);
            }
        });
    }

    //ViewPropertyAnimator
    @OnClick(R.id.btn4)
    void onClickBtn4(){

        // ViewPropertyAnimator的功能建立在animate()上
        // 特别注意:
        // 动画自动启动,无需调用start()方法.因为新的接口中使用了隐式启动动画的功能，只要我们将动画定义完成后，动画就会自动启动
        // 该机制对于组合动画也同样有效，只要不断地连缀新的方法，那么动画就不会立刻执行，等到所有在ViewPropertyAnimator上设置的方法都执行完毕后，动画就会自动启动
        // 如果不想使用这一默认机制，也可以显式地调用start()方法来启动动画
//        btn4.animate().alpha(0.1f);
        btn4.animate().alpha(0.1f).x(200).setDuration(2000).setInterpolator(new BounceInterpolator());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    private static class ViewWrapper{
        private View mTarget;
        // 构造方法:传入需要包装的对象
        public ViewWrapper(View target) {
            mTarget = target;
        }

        // 为宽度设置get（） & set（）
        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

    }
}
