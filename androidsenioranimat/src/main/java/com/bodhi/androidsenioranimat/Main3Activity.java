package com.bodhi.androidsenioranimat;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 矢量动画
*/
public class Main3Activity extends AppCompatActivity {

    private boolean isBirdChecked=false;
    private boolean isTrimPathChecked=false;

    @BindView(R.id.vector_ic)
    ImageView vector_ic;

    @BindView(R.id.vector_back_animat)
    ImageView vector_back_animat;

    @BindView(R.id.vector_bird_animat)
    ImageView vector_bird_animat;

    @BindView(R.id.vector_trimPath_animat)
    ImageView vector_trimPath_animat;

    @OnClick(R.id.vector_back_animat)
    void onClickVectorBack(){
        Drawable drawable = vector_back_animat.getDrawable();
        ((Animatable) drawable).start();
    }

    @OnClick(R.id.vector_bird_animat)
    void onClickVectorBird(){
        isBirdChecked=!isBirdChecked;
        int[] state = {android.R.attr.state_checked*(isBirdChecked?1:-1)};
        vector_bird_animat.setImageState(state,true);
    }

    @OnClick(R.id.vector_trimPath_animat)
    void onClickVectorTrimPath(){
        isTrimPathChecked=!isTrimPathChecked;
        int[] state = {android.R.attr.state_checked*(isTrimPathChecked?1:-1)};
        vector_trimPath_animat.setImageState(state,true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

    }
}
