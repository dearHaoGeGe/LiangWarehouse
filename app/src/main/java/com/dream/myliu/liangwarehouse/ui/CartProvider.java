package com.dream.myliu.liangwarehouse.ui;

import android.content.Context;

import com.dream.myliu.liangwarehouse.entity.OrderItemEty;
import com.dream.myliu.liangwarehouse.entity.ShoppingCartEty;
import com.dream.myliu.liangwarehouse.utils.JSONUtil;
import com.dream.myliu.liangwarehouse.utils.PreferencesUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class CartProvider {


    public static final String CART_JSON = "cart_json";

    private List<ShoppingCartEty> datas = null;


    private Context mContext;



    public CartProvider(Context context) {
        mContext = context;
        datas = new ArrayList<>();
        listToSparse();
    }


    public void put(ShoppingCartEty cart) {
        ShoppingCartEty temp = datas.get(cart.getId().intValue());
        if (temp != null) {
            temp.setOrderCount(temp.getOrderCount() + 1);
        } else {
            temp = cart;
            temp.setOrderCount(1);
        }

        datas.add(cart.getId().intValue(), temp);

        commit();

    }


    public void put(OrderItemEty wares) {
        OrderItemEty cart = convertData(wares);
        put(cart);
    }

    public void update(ShoppingCartEty cart) {
        datas.add(cart.getId().intValue(), cart);
        commit();
    }

    public void delete(ShoppingCartEty cart) {
        datas.remove(cart.getId().intValue());
        commit();
    }

    public List<ShoppingCartEty> getAll() {
        return getDataFromLocal();
    }

    public void commit() {
        List<ShoppingCartEty> carts = sparseToList();
        PreferencesUtils.putString(mContext, CART_JSON, JSONUtil.toJSON(carts));
    }


    private List<ShoppingCartEty> sparseToList() {
        int size = datas.size();
        List<ShoppingCartEty> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(datas.get(i));
        }
        return list;
    }


    private void listToSparse() {
        List<ShoppingCartEty> carts = getDataFromLocal();
        if (carts != null && carts.size() > 0) {
            for (ShoppingCartEty cart :carts) {
                datas.add(cart.getId().intValue(), cart);
            }
        }

    }

    public List<ShoppingCartEty> getDataFromLocal() {

        String json = PreferencesUtils.getString(mContext, CART_JSON);
        List<ShoppingCartEty> carts = null;
        if (json != null) {
            carts = JSONUtil.fromJson(json, new TypeToken<List<ShoppingCartEty>>() {
            }.getType());
        }
        return carts;

    }


    public OrderItemEty convertData(OrderItemEty item) {
        ShoppingCartEty cart = new ShoppingCartEty();
        cart.setId(item.getId());
        cart.setOrderUser(item.getOrderUser());
        cart.setOrderImg(item.getOrderImg());
        cart.setOrderName(item.getOrderName());
        cart.setOrderPrice(item.getOrderPrice());
        return cart;
    }


}
