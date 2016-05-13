package com.dream.myliu.liangwarehouse.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.sql.DaoSingleton;
import com.dream.myliu.liangwarehouse.greendao.CateDetDataEty;
import com.dream.myliu.liangwarehouse.greendao.UserGoodsCartEty;
import com.dream.myliu.liangwarehouse.utils.ToastUtils;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.Random;


/**
 * Created by Amethyst on 16/1/21/15/48.
 * 购物车添加界面
 */
public class CartActivity extends Activity implements View.OnClickListener {
    private NetworkImageView cartIv;
    private CateDetDataEty dataEty;

    private TextView cartName, cartPrice, etxt_num;
    private long num = 1;
    private Button chooseBtn, btn_sub, btn_add;
    private ImageLoader imageLoader;
    private int numMax = new Random().nextInt(20);

    private String img_url, good_name, good_price;

    private UserGoodsCartEty userGoodsCartEty;//购物车实体类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        imageLoader = VolleySingleton.getInstance().getImageLoader();
        //获取activity中传递过来的值
        img_url = getIntent().getStringExtra("img_url");
        good_name = getIntent().getStringExtra("good_name");
        good_price = getIntent().getStringExtra("good_price");
        cartIv = (NetworkImageView) findViewById(R.id.cartIv);
        cartIv.setDefaultImageResId(R.mipmap.demo_people);
        cartIv.setErrorImageResId(R.mipmap.demo_2);
        cartName = (TextView) findViewById(R.id.cartName);
        cartPrice = (TextView) findViewById(R.id.cartPrice);
        etxt_num = (TextView) findViewById(R.id.etxt_num);
        userGoodsCartEty = new UserGoodsCartEty();//创建购物车实体类
        this.cartIv.setImageUrl(img_url, imageLoader);
        this.cartName.setText(good_name + "");
        this.cartPrice.setText("价格:" + good_price + "");

        chooseBtn = (Button) findViewById(R.id.chooseBtn);
        chooseBtn.setOnClickListener(this);

        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(this);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                //设置一个1 到20的随机数,模拟库存
                if (num <= numMax)
                    etxt_num.setText(++num + "");
                else {
                    Toast.makeText(CartActivity.this, "库存不够", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_sub:
                if (num > 1)
                    etxt_num.setText(--num + "");
                break;
            case R.id.chooseBtn:
                //添加购物车

                //建立一个购物车的实体类
                userGoodsCartEty.setCounts(num); //
                userGoodsCartEty.setGood_name(good_name);
                userGoodsCartEty.setGood_num(num);
                userGoodsCartEty.setIsChecked(false);
                userGoodsCartEty.setUrl_img(img_url);
                userGoodsCartEty.setUser_name("uuu");
                userGoodsCartEty.setOnePrice(Float.valueOf(good_price));
                DaoSingleton.getInstance().insertUserGoodsCartEty(userGoodsCartEty);//将选择的物品填入到购物清单中
                ToastUtils.show(this, "已经添加到购物车");
                finish();
                break;
        }
    }

    //实现onTouthEvent触屏函数但点击屏幕时销毁activityonTouchEvent()
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return false;
    }
}
