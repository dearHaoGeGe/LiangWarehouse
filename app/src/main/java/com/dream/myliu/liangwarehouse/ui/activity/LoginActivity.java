package com.dream.myliu.liangwarehouse.ui.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.myliu.liangwarehouse.R;
import com.mob.tools.utils.UIHandler;

import java.io.IOException;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * Created by Amethyst on 16/1/15/01/36.
 */
public class LoginActivity extends AbsActivity implements View.OnClickListener, SurfaceHolder.Callback, MediaPlayer.OnCompletionListener, PlatformActionListener, Handler.Callback {
    private ImageView loginIv;
    private TextView mobileLogin, emailLogin;
    private SurfaceView mSurfaceView;// 视频界面
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mediaPlayer;// 播放器

    //第三方登录需要使用的
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR= 4;
    private static final int MSG_AUTH_COMPLETE = 5;
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    public void initView() {
        ShareSDK.initSDK(this);
        loginIv = (ImageView) findViewById(R.id.loginIv);
        findViewById(R.id.qqLogin).setOnClickListener(this);
        findViewById(R.id.weChatLogin).setOnClickListener(this);
        findView(R.id.sinaLogin).setOnClickListener(this);
        findViewById(R.id.sinaLogin).setOnClickListener(this);
        mSurfaceView = (SurfaceView) findViewById(R.id.loginSurface);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qqLogin:
//                authorize(new QQ(this));
                PlatformActionListener listener = new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        //授权成功
                        String id = platform.getDb().getUserId();
                        String name = platform.getDb().getUserName();
                        String imageUrl = platform.getDb().getUserIcon();
                        Log.d("message", "id:" + id + "name:" + name + "url:" + imageUrl);
                        Log.d("hashmap=======", "" + hashMap);
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        //授权失败
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        //取消授权
                    }
                };
                Platform qq = ShareSDK.getPlatform(LoginActivity.this, QQ.NAME);
                qq.setPlatformActionListener(listener);
                qq.showUser(null);
                qq.authorize();
                break;
            case R.id.weChatLogin:
                authorize(new Wechat(this));
                break;
            case R.id.sinaLogin:
                PlatformActionListener listenerSina = new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        //授权成功
                         String id = platform.getDb().getUserId();
                        String name = platform.getDb().getUserName();
                        String imageUrl = platform.getDb().getUserIcon();
                        Log.d("message", "id:" + id + "name:" + name + "url:" + imageUrl);
                        Log.d("hashmap=======", "" + hashMap);
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        //授权失败
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        //取消授权
                    }
                };
                Platform sina = ShareSDK.getPlatform(LoginActivity.this, SinaWeibo.NAME);
                sina.setPlatformActionListener(listenerSina);
                sina.showUser(null);
                sina.authorize();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        //销毁,避免一直持有内存
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        ShareSDK.stopSDK();
        // Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(LoginActivity.this, Uri.parse("android.resource://com.dream.myliu.liangwarehouse/" + R.raw.videoviewdemo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
        mediaPlayer.setDisplay(mSurfaceHolder);
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnCompletionListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        }).start();
        loginIv.setVisibility(View.GONE);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
//        mediaPlayer.reset();
//        try {
//            mediaPlayer.setDataSource(LoginActivity.this, Uri.parse("android.resource://com.dream.myliu.liangwarehouse/" + R.raw.login_video));
//            mediaPlayer.prepareAsync();
//            mediaPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mediaPlayer.start();  //保证循环播放
    }



    private void authorize(Platform plat) {
        if(plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName(), userId, null);
                return;
            }
        }
        plat.setPlatformActionListener(this);
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    public void onComplete(Platform platform, int action,
                           HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            login(platform.getName(), platform.getDb().getUserId(), res);
        }
        System.out.println(res);
        System.out.println("------User Name ---------" + platform.getDb().getUserName());
        System.out.println("------User ID ---------" + platform.getDb().getUserId());
    }

    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        t.printStackTrace();
    }

    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }

    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(this,"ppp", Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_LOGIN: {

                String text = getString(Integer.parseInt("logining"), msg.obj);
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                System.out.println("---------------");

//				Builder builder = new Builder(this);
//				builder.setTitle(R.string.if_register_needed);
//				builder.setMessage(R.string.after_auth);
//				builder.setPositiveButton(R.string.ok, null);
//				builder.create().show();
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(this, "auth_cancel", Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_CANCEL--------");
            }
            break;
            case MSG_AUTH_ERROR: {
                Toast.makeText(this, "auth_error", Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_ERROR--------");
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Toast.makeText(this, "auth_complete", Toast.LENGTH_SHORT).show();
                System.out.println("--------MSG_AUTH_COMPLETE-------");
            }
            break;
        }
        return false;
    }
}
