package com.dream.myliu.liangwarehouse.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.GoodEty;
import com.dream.myliu.liangwarehouse.greendao.CateDetDataEty;
import com.dream.myliu.liangwarehouse.ui.adapter.GoodsAdapter;
import com.dream.myliu.liangwarehouse.view.CNiaoToolBar;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import c.b.BP;
import c.b.PListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import dmax.dialog.SpotsDialog;


/**
 * Created by Amethyst on 16/1/18/15/34.
 */
public class CateDetailActivity extends AbsActivity implements View.OnClickListener {
    private String goodId;
    private VolleySingleton volleySingleton;
    private CateDetDataEty cateDetDataEty;
    private RecyclerView goodsRecy;
    private GoodsAdapter adapter;
    private LinearLayout goodsLL;
    private TextView addCart, buyGood, noGood;
    private CNiaoToolBar toolbarDetail;

    private SpotsDialog mDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_acte_detail;
    }

    @Override
    protected void initView() {
        goodsRecy = findView(R.id.goodsRecy);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        goodsRecy.setLayoutManager(manager);
        adapter = new GoodsAdapter(this);
        goodsLL = findView(R.id.goodsLL);
        addCart = findView(R.id.addCart);
        addCart.setOnClickListener(this);
        noGood = findView(R.id.noGood);
        findViewById(R.id.buyGood).setOnClickListener(this);
        toolbarDetail = findView(R.id.toolbarDetail);
        toolbarDetail.setNavigationIcon(R.mipmap.actionbar_navigation_back);
        toolbarDetail.setNavigationOnClickListener(this);
        toolbarDetail.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    @Override
    protected void initData() {
        goodId = getIntent().getStringExtra("goodId");
        initNetWork();
    }

    private String getUrl() {
        return Contacts.CATE_DET_HTTP_FIRST + goodId + Contacts.DAREN_RECOM_HTTP_FOUR;
    }

    private void initNetWork() {
        volleySingleton = VolleySingleton.getInstance();
        GsonRequest<GoodEty> request = new GsonRequest<GoodEty>(getUrl(), GoodEty.class, new Response.Listener<GoodEty>() {
            @Override
            public void onResponse(GoodEty response) {
                //private CateDetailEty brand_info;  private List<String> images_item;private GoodsGuideEty good_guide;
                cateDetDataEty = response.getData();
                if (cateDetDataEty != null) {
                    goodsLL.setVisibility(View.VISIBLE);
                    goodsLL.bringToFront();
                    adapter.addData(cateDetDataEty);
                    goodsRecy.setAdapter(adapter);
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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addCart) {
            //                if ("".equals(this.cateDetDataEty.getAble_buy_note())) {
            Intent intent = new Intent(this, CartActivity.class);
            intent.putExtra("img_url", this.cateDetDataEty.getGoods_image());
            intent.putExtra("good_name", this.cateDetDataEty.getGoods_name());
            intent.putExtra("good_price", this.cateDetDataEty.getPrice());
            startActivity(intent);

//                }
//                else {
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("提示").setMessage("大物件物品请用电脑访问/n" + cateDetDataEty.getAble_buy_note() + "购买");
//                    builder.show();
//                    builder.setPositiveButton("确实", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                            builder.setCancelable(true);
//                        }
//                    });
//                }
//                EventBus.getDefault().post(cateDetDataEty);

//            break;
        } else if (R.id.buyGood == v.getId()) {
            BP.pay(this, "hello", "world", 0.2f,
                    true, new PListener() {
                        @Override
                        public void orderId(String s) {

                        }

                        @Override
                        public void succeed() {
                            Toast.makeText(CateDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void fail(int i, String s) {

                            Toast.makeText(CateDetailActivity.this, "fail" + "i:  " + i + "   " + s, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void unknow() {
                            Toast.makeText(CateDetailActivity.this, "支付结果未知, 请稍后手动查询", Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            finish();
        }
//        switch (v.getId()) {
//
//            case R.id.addCart:
////                if ("".equals(this.cateDetDataEty.getAble_buy_note())) {
//                Intent intent = new Intent(this, CartActivity.class);
//                intent.putExtra("img_url", this.cateDetDataEty.getGoods_image());
//                intent.putExtra("good_name", this.cateDetDataEty.getGoods_name());
//                intent.putExtra("good_price", this.cateDetDataEty.getPrice());
//                startActivity(intent);
//
////                }
////                else {
////                    final AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("提示").setMessage("大物件物品请用电脑访问/n" + cateDetDataEty.getAble_buy_note() + "购买");
////                    builder.show();
////                    builder.setPositiveButton("确实", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
//////                            builder.setCancelable(true);
////                        }
////                    });
////                }
////                EventBus.getDefault().post(cateDetDataEty);
//
//                break;
//
//            case R.id.buyGood:
////                showShare();
////                BP.init(this, "1dead0012c0b03ff563f5aec4472dd69");
//                BP.pay(this, "hello", "world", 0.2f,
//                        true, new PListener() {
//                            @Override
//                            public void orderId(String s) {
//
//                            }
//
//                            @Override
//                            public void succeed() {
//                                Toast.makeText(CateDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void fail(int i, String s) {
//
//                                Toast.makeText(CateDetailActivity.this, "fail" + "i:  " + i + "   " + s, Toast.LENGTH_SHORT).show();
//
//                            }
//
//                            @Override
//                            public void unknow() {
//                                Toast.makeText(CateDetailActivity.this, "支付结果未知, 请稍后手动查询", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                break;
    }



private void showShare(){
        ShareSDK.initSDK(this);
        OnekeyShare oks=new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(cateDetDataEty.getGoods_name());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(cateDetDataEty.getGoods_url());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(cateDetDataEty.getGoods_desc());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(cateDetDataEty.getGoods_image());//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(cateDetDataEty.getGoods_url());

// 启动分享GUI
        oks.show(this);
        }

@Override
protected void onDestroy(){
        super.onDestroy();
//        ShareSDK.stopSDK();
        }

        }
