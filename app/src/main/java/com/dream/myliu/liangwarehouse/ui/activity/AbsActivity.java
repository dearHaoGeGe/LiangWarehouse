package com.dream.myliu.liangwarehouse.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import c.b.BP;
import dmax.dialog.SpotsDialog;

/**
 * Created by Amethyst on 16/1/16/15/28.
 */
public abstract class AbsActivity extends AppCompatActivity {
    protected String tag = getClass().getSimpleName();//作为标示符
    private SpotsDialog mDialog;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ActivityControl.addAty(tag, this);

        mDialog = new SpotsDialog(this, "loading...");
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControl.removeAty(tag);
    }

    /**
     * 简化findViewById()方法
     *
     * @param resId 组件id
     * @param <T>   View类型
     * @return
     */
    protected <T extends View> T findView(int resId) {
        T t = (T) findViewById(resId);
        return t;
    }

    protected void initPresenter() {

    }

    //返回值为所要加载的布局文件的id
    protected abstract int getLayout();

    //View的初始化控件工作
    protected abstract void initView();

    //数据初始化
    protected abstract void initData();

    protected void toastError() {
        Toast.makeText(this, "数据加载失败*_*!", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示dialog
     */
    protected void showDialog() {
        mDialog.show();
    }

    /**
     * 隐藏dialog
     */
    protected void dismissDialog() {
        mDialog.dismiss();
    }
}
