package com.bodhi.androidsenioranimat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Explode
//        getWindow().setEnterTransition(new Explode());
        //Slide
//        getWindow().setEnterTransition(new Slide());
        //Fade
//        getWindow().setEnterTransition(new Fade());
//        getWindow().setExitTransition(new Fade());
        //Share
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main2);
    }
}
