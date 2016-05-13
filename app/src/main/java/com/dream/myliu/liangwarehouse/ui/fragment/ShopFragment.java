package com.dream.myliu.liangwarehouse.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.myliu.liangwarehouse.MainActivity;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.ui.adapter.ShopPagerAdapter;
import com.dream.myliu.liangwarehouse.ui.fragment.BaseFragment;

/**
 * Created by Amethyst on 16/1/11/08/31.
 */
public class ShopFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ShopPagerAdapter adapter;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView(View view) {
        tabLayout = findView(R.id.tabLHome,view);
        viewPager = findView(R.id.viewPHome, view);
    }

    @Override
    protected void initData() {
        adapter = new ShopPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2);
    }
    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
    }
}
