package com.dream.myliu.liangwarehouse.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.MzgWebEty;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

/**
 * Created by Amethyst on 16/1/21/14/14.
 */
public class MzgActivity extends AbsActivity implements View.OnClickListener {
    private WebView webView;
    private Toolbar toolMzg;
    private VolleySingleton singleton;
    private MzgWebEty.MzgWebDataEty mzgWebDataEty;
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
    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
//        Toast.makeText(MzgActivity.this, url, Toast.LENGTH_SHORT).show();

        initNetwork();
    }

    private void initNetwork() {
        singleton = VolleySingleton.getInstance();
        GsonRequest<MzgWebEty> request = new GsonRequest<MzgWebEty>(url, MzgWebEty.class, new Response.Listener<MzgWebEty>() {
            @Override
            public void onResponse(MzgWebEty response) {
                mzgWebDataEty = response.getData();
                String toolbarTitle = mzgWebDataEty.getTopic_info().getTopic_name();
                String content = mzgWebDataEty.getTopic_info().getContent();
//                http://www.iliangcang.com/i/topicapp/201601204130

                webView.loadUrl(mzgWebDataEty.getTopic_info().getTopic_url());
//                webView.loadData( content, "text/html", "UTF-8");
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setUseWideViewPort(true);
                webView.getSettings().setJavaScriptEnabled(true);
                toolMzg.setTitle(toolbarTitle);
                dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissDialog();
            }
        });
        showDialog();
        singleton.addRequest(request, tag);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
