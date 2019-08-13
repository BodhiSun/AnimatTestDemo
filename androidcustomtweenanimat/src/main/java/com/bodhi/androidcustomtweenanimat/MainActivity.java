package com.bodhi.androidcustomtweenanimat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] mRes={R.id.imageView_a,R.id.imageView_b,R.id.imageView_c,R.id.imageView_d,R.id.imageView_e};
    private List<ImageView> mImageViews = new ArrayList<>();
    private boolean mFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        int sum = mRes.length;
        for (int i = 0; i < sum; i++) {
            ImageView imageView = (ImageView) findViewById(mRes[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_a:
                if (mFlag) {
                    startAnim();
                    mFlag=false;
                } else {
                    closeAnim();
                    mFlag=true;
                }
                break;
            case R.id.imageView_b:
                startActivity(new Intent(this,MainSurfaceViewActivity.class));
                break;
            case R.id.imageView_c:
                startActivity(new Intent(this,TweenAnimatJavaCodeActivity.class));

                break;
            case R.id.imageView_d:
                startActivity(new Intent(this,CustomTweenAnimatActivity.class));

                break;
            case R.id.imageView_e:
                Toast.makeText(MainActivity.this, "e", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startAnim() {
        ObjectAnimator oa0 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha", 1f, 0.5f);
        ObjectAnimator oa1 =ObjectAnimator.ofFloat(mImageViews.get(1),"translationY",200f);
        ObjectAnimator oa2 =ObjectAnimator.ofFloat(mImageViews.get(2),"translationX",200F);
        ObjectAnimator oa3 =ObjectAnimator.ofFloat(mImageViews.get(3),"translationY",-200F);
        ObjectAnimator oa4 =ObjectAnimator.ofFloat(mImageViews.get(4),"translationX",-200F);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.playTogether(oa0,oa1,oa2,oa3,oa4);
        animatorSet.start();
    }

    private void closeAnim() {
        ObjectAnimator oa0 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha",  0.5f,1f);
        ObjectAnimator oa1 =ObjectAnimator.ofFloat(mImageViews.get(1),"translationY",200f,0f);
        ObjectAnimator oa2 =ObjectAnimator.ofFloat(mImageViews.get(2),"translationX",200F,0f);
        ObjectAnimator oa3 =ObjectAnimator.ofFloat(mImageViews.get(3),"translationY",-200F,0f);
        ObjectAnimator oa4 =ObjectAnimator.ofFloat(mImageViews.get(4),"translationX",-200F,0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.playTogether(oa0,oa1,oa2,oa3,oa4);
        animatorSet.start();
    }
}
