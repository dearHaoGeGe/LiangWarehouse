package com.dream.myliu.liangwarehouse.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.BrandDataEty;
import com.dream.myliu.liangwarehouse.entity.BrandEty;
import com.dream.myliu.liangwarehouse.entity.BrandItem;
import com.dream.myliu.liangwarehouse.ui.activity.BraGoodsAty;
import com.dream.myliu.liangwarehouse.ui.adapter.BrandAdapter;
import com.dream.myliu.liangwarehouse.ui.fragment.BaseFragment;
import com.dream.myliu.liangwarehouse.view.NewListView;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/11/09/57.
 */
public class BrandFragment extends BaseFragment implements AdapterView.OnItemClickListener, NewListView.ILoadListener, NewListView.IReflashListener {
    private BrandAdapter adapter;
    private NewListView listView;
    private VolleySingleton volleySingleton;
    private List<BrandItem> brandItems ;
    private int start = 0;//用于网址的拼接字段

    @Override
    public void onDestroy() {
        super.onDestroy();
        volleySingleton.removeRequest(tag);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView(View view) {
        adapter = new BrandAdapter(getActivity());
        listView = findView(R.id.listView, view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this); //页面跳转
        listView.setOnLoadListener(this);
        listView.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        if (brandItems == null)
        brandItems = new ArrayList<>();
        initNetwork(getUrl(start),true);
    }

    private void initNetwork(String url, final boolean isfreshLoad) {
        volleySingleton = VolleySingleton.getInstance();
        GsonRequest<BrandEty> request = new GsonRequest<BrandEty>(url, BrandEty.class, new Response.Listener<BrandEty>() {
            @Override
            public void onResponse(BrandEty response) {
                BrandDataEty dataEty = response.getData();
                if (dataEty.getItems() != null && dataEty.getItems().size() > 0) {
                    brandItems.addAll(dataEty.getItems());
                }
                if (brandItems != null && brandItems.size() > 0) {
                    adapter.addData(dataEty.getItems(), isfreshLoad);
                }
                listView.reflashComplete();

                dismissDialog();
                listView.loadComplete();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BraGoodsAty.class);
        intent.putExtra("item_id","http://iliangcang.com:8200/brand/"+ brandItems.get(position).getId() +"?app_key=Android&build=2015120701");
        startActivity(intent);
        //页面跳转 字符拼接
    }

    private String getUrl(int start) {
        return Contacts.BRANDHTTP_B + start + Contacts.BRANDHTTP_H;
    }

    @Override
    public void onLoad() {
        start = start+20;
        initNetwork(getUrl(start), false);
    }

    @Override
    public void onReflash() {
        start = 0;
        initNetwork(getUrl(start),true);
    }
}
