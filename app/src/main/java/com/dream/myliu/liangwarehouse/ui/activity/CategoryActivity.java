package com.dream.myliu.liangwarehouse.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.CateChildEty;
import com.dream.myliu.liangwarehouse.greendao.CateListDataEty;
import com.dream.myliu.liangwarehouse.ui.adapter.CateRecdAdapter;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Amethyst on 16/1/20/08/18.
 */
public class CategoryActivity extends AbsActivity implements CateRecdAdapter.OnItemClickListener {
    private RecyclerView cateRecy;
    private String code;
    private List<CateListDataEty> cateListDataEties;
    private VolleySingleton volleySingleton;
    private int page = 1;

    private CateRecdAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void initView() {
        cateRecy = findView(R.id.cateRecy);
        LinearLayoutManager manager = new GridLayoutManager(this, 2);
        cateRecy.setLayoutManager(manager);
        adapter = new CateRecdAdapter(this);
        cateRecy.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {
        code = getIntent().getStringExtra("code");
        if (cateListDataEties == null)
            cateListDataEties = new ArrayList<>();
        initNetWork();
    }

    private String getUrl(int page) {
        return Contacts.CATE_ITEM_HTTP_FIRST + code + Contacts.CATE_ITEM_HTTP_SECOND + page + Contacts.CATE_ITEM_HTTP_THREE;

    }

    private void initNetWork() {
        volleySingleton = VolleySingleton.getInstance();
        GsonRequest<CateChildEty> request = new GsonRequest<CateChildEty>(getUrl(page), CateChildEty.class, new Response.Listener<CateChildEty>() {
            @Override
            public void onResponse(CateChildEty response) {
                cateListDataEties = response.getData();
                adapter.addData(cateListDataEties);
                cateRecy.setAdapter(adapter);
                Toast.makeText(CategoryActivity.this, "net" + cateListDataEties.size(), Toast.LENGTH_SHORT).show();
                dismissDialog();
//                listView.reflashComplete();
//                if (DaoSingleton.getInstance().searchCateData() == null)
//                    DaoSingleton.getInstance().insertCateData(cateDataEty);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                cateDataEty = DaoSingleton.getInstance().searchCateData();
//                if (cateDataEty != null) {
//                    Toast.makeText(getActivity(), "" + cateDataEty.getItems().size(), Toast.LENGTH_SHORT).show();
////                    adapter.addData(cateDataEty);
//                } else {
//                    Toast.makeText(getActivity(), "222", Toast.LENGTH_SHORT).show();
//                }
                toastError();
                dismissDialog();

            }
        });
        showDialog();
        volleySingleton.addRequest(request, tag);
    }


    @Override
    public void onItemClick(View view, int position) {
//        cateListDataEties.get(position).getGoods_id()
        Intent intent = new Intent(CategoryActivity.this, CateDetailActivity.class);
        intent.putExtra("goodId", cateListDataEties.get(position).getGoods_id());
        startActivity(intent);
    }
}
