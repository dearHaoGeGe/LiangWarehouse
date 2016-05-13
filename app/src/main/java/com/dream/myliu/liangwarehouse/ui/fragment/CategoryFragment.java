package com.dream.myliu.liangwarehouse.ui.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ExpandableListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.sql.DaoSingleton;
import com.dream.myliu.liangwarehouse.entity.CateEty;
import com.dream.myliu.liangwarehouse.greendao.CateChildEntity;
import com.dream.myliu.liangwarehouse.greendao.CateCoverEty;
import com.dream.myliu.liangwarehouse.greendao.CateDataEty;
import com.dream.myliu.liangwarehouse.greendao.CateItemsEty;
import com.dream.myliu.liangwarehouse.ui.activity.CategoryActivity;
import com.dream.myliu.liangwarehouse.ui.adapter.CategoryAdapter;
import com.dream.myliu.liangwarehouse.view.NewExpandlistView;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amethyst on 16/1/11/09/56.
 */
public class CategoryFragment extends BaseFragment implements NewExpandlistView.IReflashListener, NewExpandlistView.ILoadListener, ExpandableListView.OnChildClickListener {
    private NewExpandlistView listView;
    private CategoryAdapter adapter;
    private VolleySingleton volleySingleton;
    private CateDataEty cateDataEty;
    private String childCode;
    private List<CateItemsEty> itemsEties;

    @Override
    public void onDestroy() {
        super.onDestroy();
        volleySingleton.removeRequest(tag);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_category;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView(View view) {
        listView = (NewExpandlistView) view.findViewById(R.id.brandExpand);
        adapter = new CategoryAdapter(getActivity());
        listView.setAdapter(adapter);
        //刷新数据
        listView.setOnRefreshListener(this);
        listView.setOnChildClickListener(this);
    }

    @Override
    protected void initData() {
        if (itemsEties == null) {
            itemsEties = new ArrayList<>();
        }
        initNetwork();
    }

    private void initNetwork() {
        volleySingleton = VolleySingleton.getInstance();
        GsonRequest<CateEty> request = new GsonRequest<CateEty>(Contacts.CATEGORYHTTP, CateEty.class, new Response.Listener<CateEty>() {
            @Override
            public void onResponse(CateEty response) {
                itemsEties = response.getData().getItems();
                adapter.addData(itemsEties);
                dismissDialog();
                listView.reflashComplete();
                List<CateCoverEty> cateCoverEties = null;
                Map<Integer, List<CateChildEntity>> cateChildEntities = new HashMap<>();
                //添加数据库
                if (itemsEties != null && itemsEties.size() > 0) {
                    if (cateCoverEties == null)
                        cateCoverEties = new ArrayList<>();
                    //建立1:1的表的关联的关系
                    for (int i = 0; i < itemsEties.size(); i++) {
                        cateCoverEties.add(itemsEties.get(i).getCover());
                    }
                    //建立1:n的表的关联关系  //通过键值对将数据存储来存储数据,
                    //建立二级关系, 将一组数据数据存储到同一key之中,便于存储和查找
                    for (int i = 0; i < itemsEties.size(); i++) {
                        for (int j = 0; j < itemsEties.get(i).getChildren().size(); j++) {
                            cateChildEntities.put(i, itemsEties.get(i).getChildren());
                        }
                    }
                    //插入数据库
                    DaoSingleton.getInstance().insertCateItemsEty(itemsEties, cateCoverEties, cateChildEntities);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //当没有网路时从网络中拉取数据
                itemsEties = DaoSingleton.getInstance().searchCateItemsEty();
//                Log.d("toaaa", itemsEties.get(0).getCover().getUrl());
                if (itemsEties != null && itemsEties.size() > 0) {
                    itemsEties.get(0).getChildren().size();
                    adapter.addData(itemsEties);
                    dismissDialog();
                } else {
                    toastError();
                    dismissDialog();
                }
            }
        });
        showDialog();
        volleySingleton.addRequest(request, tag);
    }

    //刷新数据
    @Override
    public void onReflash() {
        initNetwork();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                int childPosition, long id) {
        //跳转
        childCode = itemsEties.get(groupPosition).getChildren().get(childPosition).getCode();
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
//        Toast.makeText(getActivity(), childCode, Toast.LENGTH_SHORT).show();
        //通过children的code进行网路的拼接
        intent.putExtra("code", childCode);
        startActivity(intent);

        return true;
    }

    @Override
    public void onLoad() {

    }
}
