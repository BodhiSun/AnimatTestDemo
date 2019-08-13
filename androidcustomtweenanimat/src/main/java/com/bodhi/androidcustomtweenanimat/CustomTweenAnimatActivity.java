package com.bodhi.androidcustomtweenanimat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomTweenAnimatActivity extends AppCompatActivity {

    @BindView(R.id.layout)
    LinearLayout layout;

    @OnClick(R.id.layout)
    void onClickCustomTween(){
        layout.startAnimation(new CustomTweenAnimation(layout.getX()/3,layout.getY()/3,3500));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tween_animat);
        ButterKnife.bind(this);
    }
}
