package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.ui.fragment.FragmentFactory;

/**
 * Created by Amethyst on 16/1/11/20/27.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] titles = {"商店","杂志","分享", "达人", "个人"};
    private int[] imageview = {R.drawable.selector_shop,R.drawable.selector_mgz,R.drawable.selector_share, R.drawable.selector_daren, R.drawable.selector_self};

    public MainViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createMainFragment(position);
    }

    @Override
    public int getCount() {
        return titles != null && titles.length > 0 ? titles.length : 0;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.iv_tv_vertical_item,null);
        ImageView image = (ImageView) view.findViewById(R.id.ivTvVer);
        TextView textView = (TextView) view.findViewById(R.id.tvIvVer);
        textView.setText(titles[position]);
        image.setImageResource(imageview[position]);
        if (position == 0) {
            view.setSelected(true);
        }
        return view;
    }

    public String[] getTitles(){
        return titles;
    }

}
