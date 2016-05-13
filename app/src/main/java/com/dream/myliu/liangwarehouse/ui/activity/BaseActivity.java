package com.dream.myliu.liangwarehouse.ui.activity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dream.myliu.liangwarehouse.view.ToolbarHelper;


/**
 * 本类版权所有 刘名言 于 2016年01月05日08点40分
 */
public abstract class BaseActivity extends AbsActivity {
    protected String tag = getClass().getSimpleName();//作为标示符

    protected ToolbarHelper mToolBarHelper;
    protected Toolbar toolbar;

    @Override
    public void setContentView(int layoutResID) {
        mToolBarHelper = new ToolbarHelper(this, layoutResID);
        toolbar = mToolBarHelper.getToolBar();
        setContentView(mToolBarHelper.getContentView());
        /*把 toolbar 设置到Activity 中*/
        setSupportActionBar(toolbar);
        /*自定义的一些操作*/
        onCreateCustomToolBar(toolbar);
    }

    protected  void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
