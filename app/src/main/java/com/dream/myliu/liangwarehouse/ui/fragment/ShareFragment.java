package com.dream.myliu.liangwarehouse.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.sql.DaoSingleton;
import com.dream.myliu.liangwarehouse.entity.ShareEty;
import com.dream.myliu.liangwarehouse.greendao.ShareDataEty;
import com.dream.myliu.liangwarehouse.ui.adapter.ShareAdapter;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Amethyst on 16/1/12/07/15.
 */
public class ShareFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private ShareEty shareEty;
    private ShareAdapter adapter;
    private VolleySingleton singleton;
    private List<ShareDataEty> dataEty = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_share;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        singleton.removeRequest(tag);
    }

    @Override
    protected void initView(View view) {
        gridView = findView(R.id.gridView, view);
        adapter = new ShareAdapter(getContext());
        gridView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        singleton = VolleySingleton.getInstance();
        GsonRequest<ShareEty> request = new GsonRequest<ShareEty>(Contacts.SHAREHTTP, ShareEty.class, new Response.Listener<ShareEty>() {
            @Override
            public void onResponse(ShareEty response) {
                dataEty = response.getData();
                response.getData().size();
                adapter.initData(dataEty);
                if (dataEty != null && dataEty.size() > 0) {
                    if (DaoSingleton.getInstance().searchShare() != null && DaoSingleton.getInstance().searchShare().size() > 0){
                    }else {
                        DaoSingleton.getInstance().insertShare(dataEty);
                    }
                }
                dismissDialog();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataEty = DaoSingleton.getInstance().loadShareAll();
                if (dataEty != null && dataEty.size() > 0) {
                    adapter.initData(dataEty);
                    dismissDialog();
                } else {
                    toastError();
                    dismissDialog();
                }
            }
        });
        showDialog();
        singleton.addRequest(request, tag);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
        dataEty.get(position).getGoods_id();

    }
}
