package com.dream.myliu.liangwarehouse.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.HomeDataEntity;
import com.dream.myliu.liangwarehouse.entity.HomeEntity;
import com.dream.myliu.liangwarehouse.entity.HomeSlideDataEntity;
import com.dream.myliu.liangwarehouse.entity.HomeSlideEntity;
import com.dream.myliu.liangwarehouse.ui.activity.HomeActivity;
import com.dream.myliu.liangwarehouse.ui.adapter.HomeViewAdapter;
import com.dream.myliu.liangwarehouse.ui.adapter.ZoomOutPageTransformer;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by methyst on 16/1/11/10/00.
 */
public class HomeFragment extends BaseFragment {
    private ViewPager mViewPager;
    private HomeViewAdapter vpAdapter;
    private VolleySingleton volleySingleton;
    private View view1, view2, view3;
    private ArrayList<View> views;
    private ImageLoader imageLoader;
    private HomeDataEntity homeDataEntity;
    private HomeSlideEntity homeSlideEntity;
    private List<HomeSlideDataEntity> homeSlideDataEntities;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.homeViewPage);
        view1 = View.inflate(getActivity(), R.layout.home_item, null);
        view2 = View.inflate(getActivity(), R.layout.home_item, null);
        view3 = View.inflate(getActivity(), R.layout.home_item, null);
        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

        for (int i = 0; i < views.size(); i++) {
            final int finalI = i;
            final Intent intent = new Intent(getActivity(), HomeActivity.class);
            views.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (homeSlideDataEntities != null && homeSlideDataEntities.size() != 0) {
                        intent.putExtra("url",homeSlideDataEntities.get(finalI).getTopic_url());
                        startActivity(intent);
                    }
                }
            });
        }

        view1.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
        view2.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });
        view3.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);
            }
        });

    }

    @Override
    protected void initData() {
        imageLoader = VolleySingleton.getInstance().getImageLoader();
        initNetWork();
    }

    //    px即pixels，是绝对像素，有多少个像素点就是多少个像素点。
//    dip即device independent pixel，设备独立像素，无像素无关。
//    二者转换公式如下：
    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initNetWork() {
        volleySingleton = VolleySingleton.getInstance();
        GsonRequest<HomeEntity> request = new GsonRequest<HomeEntity>(Contacts.HOMEHTTP, HomeEntity.class, new Response.Listener<HomeEntity>() {
            @Override
            public void onResponse(HomeEntity response) {
                homeDataEntity = response.getData();
                homeSlideEntity = homeDataEntity.getSlide();
                homeSlideDataEntities = homeSlideEntity.getData();
                ((NetworkImageView) view1.findViewById(R.id.tv_pic)).setImageUrl(homeSlideDataEntities.get(0).getPic_url(), imageLoader);
                ((NetworkImageView) view2.findViewById(R.id.tv_pic)).setImageUrl(homeSlideDataEntities.get(1).getPic_url(), imageLoader);
                ((NetworkImageView) view3.findViewById(R.id.tv_pic)).setImageUrl(homeSlideDataEntities.get(2).getPic_url(), imageLoader);

                ((TextView) view1.findViewById(R.id.tv_title)).setText("高颜值的小憩茶点");
                ((TextView) view1.findViewById(R.id.tv_title)).setTextColor(Color.parseColor("#FF5000"));
                ((TextView) view2.findViewById(R.id.tv_title)).setText("高颜值的小憩茶点");
                ((TextView) view2.findViewById(R.id.tv_title)).setTextColor(Color.parseColor("#49ca65"));
                ((TextView) view3.findViewById(R.id.tv_title)).setText("外貌协会的必备居家神奇");
                ((TextView) view3.findViewById(R.id.tv_title)).setTextColor(Color.parseColor("#16c5c6"));

                ((TextView) view1.findViewById(R.id.tv_desc)).setText("独家视频:科幻迷陈坤的宇宙观\n");
                ((TextView) view2.findViewById(R.id.tv_desc)).setText("WISHLIST清单\\\n");
                ((TextView) view3.findViewById(R.id.tv_desc)).setText("Live in Style\n");
                vpAdapter = new HomeViewAdapter(views);
                mViewPager.setOffscreenPageLimit(views.size());
                mViewPager.setPageMargin(-dip2px(135));
                mViewPager.setAdapter(vpAdapter);
                mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissDialog();
                toastError();
            }
        });
        showDialog();
        volleySingleton.addRequest(request, tag);
    }
}
