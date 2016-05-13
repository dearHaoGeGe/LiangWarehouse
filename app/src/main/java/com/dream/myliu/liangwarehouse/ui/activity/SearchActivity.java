package com.dream.myliu.liangwarehouse.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.SearchEty;
import com.dream.myliu.liangwarehouse.greendao.SearchData;
import com.dream.myliu.liangwarehouse.ui.adapter.SearchAdapter;
import com.dream.myliu.liangwarehouse.utils.StringUtil;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/17/13/17.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    //http://app.iliangcang.com/goods?app_key=Android&build=2015120701&goods_id=249314&v=1.0
    //搜索的字符串的拼接 ../点击进入到详情页
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private VolleySingleton singleton;
    private EditText etSearch;
    private TextView searchThing, searchUser;
    private String inputText;
    private int page = 1;
    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        recyclerView = findView(R.id.searchGoodsShow);
        adapter = new SearchAdapter(this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        etSearch = findView(R.id.etSearch);
        searchThing = findView(R.id.searchThing);
//        searchUser = findView(R.id.searchUser);
//        searchUser.setOnClickListener(this);
        searchThing.setOnClickListener(this);
        mToolBarHelper.getTextView().setText("搜索");
        toolbar.setNavigationIcon(R.mipmap.actionbar_navigation_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void initData() {


    }
    public void getQueryData(String url){
        singleton = VolleySingleton.getInstance();
        GsonRequest<SearchEty> request = new GsonRequest<SearchEty>(url, SearchEty.class, new Response.Listener<SearchEty>() {
            @Override
            public void onResponse(SearchEty response) {
                List<SearchData> darenDatas = new ArrayList<>();
                darenDatas.addAll(response.getData());
                adapter.initData(darenDatas);
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
        singleton.addRequest(request, tag);
        recyclerView.setAdapter(adapter);
    }

    private String getEditText(){
        return StringUtil.utf8Encode(etSearch.getText().toString().trim(), "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchGoodsShow:
                if (etSearch.getText() != null && etSearch.getText().toString().trim().length() > 0){
                    getQueryData(Contacts.SEARCHHTTPF + getEditText() + Contacts.SEARCHHTTPS + page + Contacts.SEARCHHTTPT);
//                    page++;
                }
                break;
            case R.id.searchThing:
                if (etSearch.getText() != null && etSearch.getText().toString().trim().length() > 0){
                    getQueryData(Contacts.SEARCHHTTPF + getEditText() + Contacts.SEARCHHTTPS + page + Contacts.SEARCHHTTPT);
//                    page++;
                }
                break;
        }
    }
}
