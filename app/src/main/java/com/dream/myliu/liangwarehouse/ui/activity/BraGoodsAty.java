package com.dream.myliu.liangwarehouse.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.entity.BrandIntentResultEty;
import com.dream.myliu.liangwarehouse.greendao.BrandIntentEty;
import com.dream.myliu.liangwarehouse.ui.adapter.BrandItemAdapter;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

/**
 * Created by Amethyst on 16/1/16/15/28.
 */
public class BraGoodsAty extends BaseActivity  {
    private NetworkImageView logoIv;
    private RecyclerView brandItemRv;
    private BrandItemAdapter adapter;
    private VolleySingleton volleySingleton;
    private String url;
    private BrandIntentEty brandIntentEty;
    @Override
    protected int getLayout() {
        return R.layout.activity_brand_goods;
    }

    @Override
    protected void initView() {
        toolbar.setNavigationIcon(R.mipmap.actionbar_navigation_back);
        mToolBarHelper.getTextView().setText("品牌产品");
        brandItemRv = findView(R.id.brandItemRv);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        ((GridLayoutManager)manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 0){
                    return 2;
                }
                return 1;

//                if (position % 5 == 0){
//                    return 4;
//                }else if (position %  == 1){
//                    return 3;
//                }
//                return 1;


            }
        });
        brandItemRv.setLayoutManager(manager);
        adapter = new BrandItemAdapter(this);
    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("item_id");
        initNetWork();
    }

    private void initNetWork(){
        volleySingleton = VolleySingleton.getInstance();
        GsonRequest<BrandIntentResultEty> request = new GsonRequest<BrandIntentResultEty>(url, BrandIntentResultEty.class, new Response.Listener<BrandIntentResultEty>() {
            @Override
            public void onResponse(BrandIntentResultEty response) {
                brandIntentEty = response.getData();
                if (brandIntentEty != null){
                    adapter.addData(brandIntentEty);
                    brandItemRv.setAdapter(adapter);

                }
                dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastError();
                dismissDialog();

            }
        });
        showDialog();
        volleySingleton.addRequest(request, tag);
    }
}
