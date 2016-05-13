package com.dream.myliu.liangwarehouse.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.sql.DaoSingleton;
import com.dream.myliu.liangwarehouse.greendao.UserGoodsCartEty;
import com.dream.myliu.liangwarehouse.ui.adapter.CartAdapter;
import com.dream.myliu.liangwarehouse.ui.adapter.DividerItemDecoration;
import com.dream.myliu.liangwarehouse.view.CNiaoToolBar;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Amethyst on 16/1/22/18/09.
 */
public class CartItemActivity extends AbsActivity implements View.OnClickListener, CartAdapter.OnItemClickListener {
    private CNiaoToolBar toolbar;
    private RecyclerView recycler_view;
    private CheckBox checkbox_all;
    private TextView txt_total;
    private Button btn_order;
    private Button btn_del;
    private CartAdapter adapter;
    private List<UserGoodsCartEty> goodsCartEties;
    public static final int ACTION_EDIT=1;
    public static final int ACTION_CAMPLATE=2;
    @Override
    protected int getLayout() {
        return R.layout.activity_cart_item;
    }

    @Override
    protected void initView() {
        recycler_view = findView(R.id.recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(manager);
        checkbox_all = findView(R.id.checkbox_all);
        txt_total = findView(R.id.txt_total);
        btn_order = findView(R.id.btn_order);
        btn_del = findView(R.id.btn_del);
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new CartAdapter(this, checkbox_all,txt_total );
        toolbar = findView(R.id.toolbar);

    }

    public void changeToolbar(){

        toolbar.hideSearchView();
        toolbar.showTitleView();
        toolbar.setTitle("购物车");
        toolbar.getRightButton().setVisibility(View.VISIBLE);
        toolbar.setRightButtonText("编辑");
        toolbar.getRightButton().setOnClickListener(this);
        toolbar.getRightButton().setTag(ACTION_EDIT);

    }


    @Override
    protected void initData() {
        if (goodsCartEties == null) {
            goodsCartEties = new ArrayList<>();
        }
        goodsCartEties = DaoSingleton.getInstance().queryUserGoodsCartEty();
//        Toast.makeText(CartItemActivity.this, "aaaa"+goodsCartEties.size(), Toast.LENGTH_SHORT).show();
        adapter.addData(goodsCartEties);
        adapter.setOnItemClickListener(this);
        txt_total.setText(adapter.getTotalPrice() +"");
        changeToolbar(); // toolbar的改变
        recycler_view.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int action = (int) v.getTag();
        if(ACTION_EDIT == action){
            showDelControl();
        }
        else if(ACTION_CAMPLATE == action){

            hideDelControl();
        }

        switch (v.getId()){
            case R.id.btn_del:
//                adapter.delCart();
        }
    }

    private void showDelControl(){
        toolbar.getRightButton().setText("完成");
        txt_total.setVisibility(View.GONE);
        btn_order.setVisibility(View.GONE);
        btn_del.setVisibility(View.VISIBLE);
        toolbar.getRightButton().setTag(ACTION_CAMPLATE);
        adapter.checkAll_None(false);
        checkbox_all.setChecked(false);
    }

    private void  hideDelControl(){
        txt_total.setVisibility(View.VISIBLE);
        btn_order.setVisibility(View.VISIBLE);

        btn_del.setVisibility(View.GONE);
        toolbar.setRightButtonText("编辑");
        toolbar.getRightButton().setTag(ACTION_EDIT);

        adapter.checkAll_None(true);
        adapter.showTotalPrice();

        checkbox_all.setChecked(true);
    }

    @Override
    public void onItemClick(View view, int position) {
    }

}
