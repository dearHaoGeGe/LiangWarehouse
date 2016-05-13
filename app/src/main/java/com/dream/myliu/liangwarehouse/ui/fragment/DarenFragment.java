package com.dream.myliu.liangwarehouse.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.sql.DaoSingleton;
import com.dream.myliu.liangwarehouse.entity.DarenEty;
import com.dream.myliu.liangwarehouse.greendao.DarenData;
import com.dream.myliu.liangwarehouse.ui.activity.DarenPerActivity;
import com.dream.myliu.liangwarehouse.ui.adapter.DarenAdapter;
import com.dream.myliu.liangwarehouse.view.NewGirdView;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/12/07/25.
 */
public class DarenFragment extends BaseFragment implements NewGirdView.OnRefreshListener, AdapterView.OnItemClickListener {
    private List<DarenData> darenDatas = new ArrayList<>();
    private NewGirdView gridView;
    private DarenEty darenEty;
    private DarenAdapter adapter;
    private VolleySingleton singleton;
    private static LinearLayout head;

    @Override
    protected int getLayout() {
        return R.layout.fragment_daren;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        singleton.removeRequest(tag);
    }

    @Override
    protected void initView(View view) {
        gridView = findView(R.id.darenGirdView, view);
        gridView.setOnItemClickListener(this);
        adapter = new DarenAdapter(getContext());
    }

    @Override
    protected void initData() {
        singleton = VolleySingleton.getInstance();
        GsonRequest<DarenEty> request = new GsonRequest<DarenEty>(Contacts.DARENHTTP, DarenEty.class, new Response.Listener<DarenEty>() {
            @Override
            public void onResponse(DarenEty response) {
                darenDatas = response.getData();
                adapter.addData(darenDatas, true);
                if (darenDatas != null)
                    DaoSingleton.getInstance().insertDaren(darenDatas);
                dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                darenDatas = DaoSingleton.getInstance().loadDarenAll();

                if (darenDatas != null && darenDatas.size() > 0){
                    adapter.addData(darenDatas, false);
                    dismissDialog();
                }
                else {
                    toastError();
                }


            }
        });
        showDialog();
        singleton.addRequest(request, tag);
        gridView.setAdapter(adapter);
        gridView.getFooterView();

    }


    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onMore() {

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), DarenPerActivity.class);
        intent.putExtra("user_id", darenDatas.get(position).getUser_id());
        startActivity(intent);
    }
}
