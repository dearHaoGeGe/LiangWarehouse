package com.dream.myliu.liangwarehouse.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dream.myliu.liangwarehouse.ui.fragment.FragmentFactory;

/**
 * Created by liumingyan on 15/12/8.
 */
public class ShopPagerAdapter extends FragmentPagerAdapter {
    //tab文字定义
    private String[] titles = {"分类", "品牌", "首页", "视频"};

    public ShopPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return titles != null && titles.length > 0 ? titles.length : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
