package com.dream.myliu.liangwarehouse.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dream.myliu.liangwarehouse.MainActivity;
import com.dream.myliu.liangwarehouse.R;


/**
 * Created by Amethyst on 16/1/14/09/05.
 */
public class SplashActivity extends BaseActivity {
    private ImageView animaImag;
    AnimationDrawable ad = new AnimationDrawable();

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        animaImag = findView(R.id.splashIv);
        toolbar.setVisibility(View.GONE);

    }
    //如果需要在页面启动就打开需要重写
    // public void onWindowFocusChanged(boolean hasFocus) { }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    //在此方法中启动
    @Override
    protected void initData() {
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        animaImag.setBackgroundResource(R.drawable.splash_animation);
        // 将动画文件绑定到view上，作为背景图片的方式
        ad = (AnimationDrawable) animaImag .getBackground();
        ad.start();
        Toast.makeText(this, "ooo" + ad.getNumberOfFrames(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int duration = 0;
        for (int j = 0; j < ad.getNumberOfFrames(); j++) {
            duration += ad.getDuration(j);     //计算动画播放的时间
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //防止帧动画的内存溢出
                ad.stop();
                ad = null;
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, duration );
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        ad.stop();
//        ad = null;
//    }
}
