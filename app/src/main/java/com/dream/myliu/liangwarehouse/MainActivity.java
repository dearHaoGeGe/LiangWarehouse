package com.dream.myliu.liangwarehouse;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dream.myliu.liangwarehouse.ui.activity.BaseActivity;
import com.dream.myliu.liangwarehouse.ui.activity.CartItemActivity;
import com.dream.myliu.liangwarehouse.ui.activity.SearchActivity;
import com.dream.myliu.liangwarehouse.ui.adapter.MainViewPagerAdapter;
import com.dream.myliu.liangwarehouse.view.NoScrollViewPager;

import c.b.BP;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private MainViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    private ViewFlipper flipper;
    private int oldPosition = -1;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //订阅事件
        //注册eventBus,用于接收fragment发送的数据
        EventBus.getDefault().register(this);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_main_activity);
        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager_main_activity);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
         Slide slide = new Slide();
//        slide.setSlideEdge();
//        BP.init(this, "1dead0012c0b03ff563f5aec4472dd69"); //Bmob第三方支付的初始化
        //操作toolbar
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolBarHelper.getTextView().setText(adapter.getTitles()[0]);
        flipper = mToolBarHelper.getmFlipper();
        flipper.setVisibility(View.GONE);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.push_down_in);
        flipper.setAnimation(animation);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                toolbar.getMenu().clear();
                switch (tab.getPosition()) {
                    case 0:
                        mToolBarHelper.getTextView().setText(adapter.getTitles()[0]);
                        mToolBarHelper.getTextView().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        mToolBarHelper.getSearchIv().setVisibility(View.INVISIBLE);
                        mToolBarHelper.getSearchIv().setImageResource(R.mipmap.actionbar_navigation_menu);
                        toolbar.inflateMenu(R.menu.toolbar_menu);
                        flipper.setVisibility(View.GONE);
                        break;
                    case 1:
                        flipper.setVisibility(View.VISIBLE);
                        mToolBarHelper.getTextView().setText(adapter.getTitles()[1]);
                        mToolBarHelper.getTextView().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrow_down, 0);
                        mToolBarHelper.getTextView().setCompoundDrawablePadding(10);
                        mToolBarHelper.getSearchIv().setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        flipper.setVisibility(View.GONE);
                        mToolBarHelper.getTextView().setText(adapter.getTitles()[2]);
                        mToolBarHelper.getTextView().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        toolbar.inflateMenu(R.menu.toolbar_menu);
                        mToolBarHelper.getSearchIv().setVisibility(View.VISIBLE);
                        mToolBarHelper.getSearchIv().setImageResource(R.mipmap.search_icon);
                        mToolBarHelper.getSearchIv().setOnClickListener(MainActivity.this);
                        break;
                    case 3:
                        flipper.setVisibility(View.GONE);
                        mToolBarHelper.getTextView().setText(adapter.getTitles()[3]);
                        mToolBarHelper.getTextView().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        toolbar.inflateMenu(R.menu.toolbar_menu);
                        mToolBarHelper.getSearchIv().setVisibility(View.VISIBLE);
                        mToolBarHelper.getSearchIv().setImageResource(R.mipmap.search_icon);
                        break;
                    case 4:
                        mToolBarHelper.getSearchIv().setVisibility(View.INVISIBLE);
                        flipper.setVisibility(View.GONE);
                        mToolBarHelper.getTextView().setText(adapter.getTitles()[4] + "设置");
                        mToolBarHelper.getTextView().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setViewFillper(String date) {
        flipper.addView(getTextViewFilpper(date));
    }

    private TextView getTextViewFilpper(String date) {
        TextView textView = null;
        if (textView == null)
            textView = new TextView(this);
        textView.setTextSize(16);
        textView.setText(date);
        textView.setTextColor(Color.GRAY);
        return textView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.cart_menu:
                intent = new Intent(this, CartItemActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
            case 2:
                intent = new Intent(this, CartItemActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, CartItemActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, CartItemActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); //取消注册
        ShareSDK.stopSDK();
        JPushInterface.stopPush(this);
    }


    //EventBus
    @Subscribe(threadMode = ThreadMode.PostThread)
    public void onEvent(String firstVisibleItem) {
        //获取日期
        String str = firstVisibleItem.substring(0, 6);
        String newPos;
        //用于字符的截取,或得当前的日期
        if (str.equals("TODAY0")) {
            str = "TODAY";
            //获取位置
            newPos = firstVisibleItem.substring(5);
        } else {
            newPos = firstVisibleItem.substring(6);
        }
        setViewFillper(str);
        //当前位置与原位置比较,用来加载不同的动画
        if (Integer.parseInt(newPos) > oldPosition) {
            flipper.setInAnimation(getApplicationContext(), R.anim.push_up_in);
            flipper.setOutAnimation(getApplicationContext(), R.anim.push_up_out);
            flipper.startFlipping();
            flipper.showNext();
            oldPosition = Integer.parseInt(newPos);
            flipper.stopFlipping();
        } else if (Integer.parseInt(newPos) <= oldPosition) {
            flipper.setInAnimation(getApplicationContext(), R.anim.push_down_in);
            flipper.setOutAnimation(getApplicationContext(), R.anim.push_down_out);
            flipper.startFlipping();
            flipper.showNext();
            oldPosition = Integer.parseInt(newPos);
            flipper.stopFlipping();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}
