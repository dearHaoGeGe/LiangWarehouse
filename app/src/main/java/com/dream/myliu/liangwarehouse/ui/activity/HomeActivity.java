package com.dream.myliu.liangwarehouse.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.entity.MzgWebEty;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

/**
 * Created by Amethyst on 16/2/26/11/45.
 */
public class HomeActivity extends AbsActivity implements View.OnClickListener {
        private WebView webView;
        private Toolbar toolMzg;
        private VolleySingleton singleton;
//        private MzgWebEty.MzgWebDataEty mzgWebDataEty;
        private String url;

        @Override
        protected int getLayout() {
            return R.layout.mzg_activity;
        }

        @Override
        protected void initView() {
            toolMzg = findView(R.id.toolMzg);
            toolMzg.setNavigationIcon(R.mipmap.actionbar_navigation_back);
            toolMzg.setTitleTextColor(Color.GRAY);
            toolMzg.setTitle("");
            webView = findView(R.id.webView);
            setSupportActionBar(toolMzg);
            toolMzg.setNavigationOnClickListener(this);
            url = getIntent().getStringExtra("url");
        }

        @Override
        protected void initData() {
//            url = getIntent().getStringExtra("url");
//            initNetwork();
            webView.loadUrl(url);
//                webView.loadData( content, "text/html", "UTF-8");
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setJavaScriptEnabled(true);
            toolMzg.setTitle("首页详情");
        }


    @Override
    public void onClick(View v) {
        finish();
    }
}
