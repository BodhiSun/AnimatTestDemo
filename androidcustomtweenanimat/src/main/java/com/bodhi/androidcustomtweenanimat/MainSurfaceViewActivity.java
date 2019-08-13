package com.bodhi.androidcustomtweenanimat;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainSurfaceViewActivity extends AppCompatActivity {

    @BindView(R.id.surface_anim_view)
    SurfaceAnimView surface_anim_view;

    @OnClick(R.id.tv_add)
    void onClickAdd(){
        SurfaceBean surfaceBean =new SurfaceBean(BitmapFactory.decodeResource(getResources(),R.mipmap.smile),surface_anim_view);
        surface_anim_view.addBean(surfaceBean);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_surface_view);
        ButterKnife.bind(this);

        surface_anim_view.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        surface_anim_view.stop();
    }
}
