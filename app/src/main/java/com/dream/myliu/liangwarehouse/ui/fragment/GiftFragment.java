package com.dream.myliu.liangwarehouse.ui.fragment;

import android.view.View;

import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.ui.fragment.BaseFragment;
/**
 * Created by Amethyst on 16/1/11/10/04.
 */
public class GiftFragment extends BaseFragment {
//private ScollViewLayout scollViewLayout;
    @Override
    protected int getLayout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initView(View view) {
//        scollViewLayout = (ScollViewLayout) view.findViewById(R.id.scrollLayout);
//
//        scollViewLayout.setScreenHeight(1230f);
//        scollViewLayout.setVisiableViewNumber(5);
//        scollViewLayout.setVisiableChildScale(1.7f, 0.2f, 1f, 0.2f);
////      scollViewLayout.init(getView());
    }

    @Override
    protected void initData() {

    }
}
