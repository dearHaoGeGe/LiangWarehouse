package com.dream.myliu.liangwarehouse;

import android.app.Application;
import android.content.Context;

import c.b.BP;
import cn.jpush.android.api.JPushInterface;

/**
 * 本类版权所有 刘名言 于 2016年01月04日21点20分
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    public static Context getmContext() {
        return mContext;
    }

}
