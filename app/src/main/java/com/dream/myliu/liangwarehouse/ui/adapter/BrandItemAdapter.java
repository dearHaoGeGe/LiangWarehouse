package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.BaseApplication;
import com.dream.myliu.liangwarehouse.greendao.BrandIntentEty;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;


/**
 * Created by Amethyst on 16/1/21/09/58.
 *
 * http://iliangcang.com:8200/brand/408?app_key=Android&build=2015120701
 * http://app.iliangcang.com/goods?app_key=Android&build=2015120701&goods_id=250873&sig=521732222%7Ce8599001c2e3ac76dd3ffb689786318f578c1c01&user_id=521732222&v=1.0
 */
public class BrandItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private BrandIntentEty brandIntentEty;
    private ImageLoader imageLoader;
    private LayoutInflater inflater = LayoutInflater.from(BaseApplication.getmContext());
    private Context context;


    public BrandItemAdapter(Context context) {
        this.context = context;
        this.imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public void addData(BrandIntentEty brandIntentEty) {
        this.brandIntentEty = brandIntentEty;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE00:
                view = inflater.inflate(R.layout.brand_item_header, null);
                return new ViewHolder00(view);
            case TYPE01:
                //商品详情页面的条状
                view = inflater.inflate(R.layout.cate_activity_item, null);
                return new ViewHolder01(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder00) {
            ViewHolder00 holder00 = (ViewHolder00) holder;
            holder00.brandItemHeadTv.setText(brandIntentEty.getName() + "");
            holder00.brandItemHeadIv.setDefaultImageResId(R.mipmap.demo_2);
            holder00.brandItemHeadIv.setErrorImageResId(R.mipmap.demo_people);
            holder00.brandItemHeadIv.setImageUrl(brandIntentEty.getLogo().getUrl(), imageLoader);
        } else if (holder instanceof ViewHolder01) {
            ViewHolder01 holder01 = (ViewHolder01) holder;
            setContent(holder01, position);
        }
    }

    @Override
    public int getItemCount() {
        return brandIntentEty.getGoods().size() + 1;
    }

    private static final int TYPE00 = 0;
    private static final int TYPE01 = 1;

    //获得两种不同的布局类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE00; //品牌的产品的头像为第一个位置
        } else {
            return TYPE01; //详情列表其他
        }
    }

    public static class ViewHolder00 extends RecyclerView.ViewHolder {
        private NetworkImageView brandItemHeadIv;
        private TextView brandItemHeadTv;

        public ViewHolder00(View itemView) {
            super(itemView);
            brandItemHeadIv = (NetworkImageView) itemView.findViewById(R.id.brandItemHeadIv);
            brandItemHeadTv = (TextView) itemView.findViewById(R.id.brandItemHeadTv);
        }
    }

    public static class ViewHolder01 extends RecyclerView.ViewHolder {
        private NetworkImageView cateGoodIv;
        private TextView cateGoodName,cateGoodDes,cateNum,catePrice;

        public ViewHolder01(View itemView) {
            super(itemView);
            cateGoodIv = (NetworkImageView) itemView.findViewById(R.id.cateGoodIv);
            cateGoodDes = (TextView) itemView.findViewById(R.id.cateGoodDes);
            cateGoodDes.setVisibility(View.GONE);
            cateGoodName = (TextView) itemView.findViewById(R.id.cateGoodName);
            cateNum= (TextView) itemView.findViewById(R.id.cateNum);
            catePrice = (TextView) itemView.findViewById(R.id.catePrice);
        }
    }

    private void setContent(ViewHolder01 viewHolder, int position) {
        viewHolder.cateGoodIv.setDefaultImageResId(R.mipmap.demo_2);
        viewHolder.cateGoodIv.setErrorImageResId(R.mipmap.demo_people);
        viewHolder.cateGoodIv.setImageUrl(brandIntentEty.getGoods().get(position - 1).getImages().get(0).getUrl(), imageLoader);
        viewHolder.cateGoodName.setText(brandIntentEty.getGoods().get(position - 1).getName() + "");
        viewHolder.cateNum.setText("" + brandIntentEty.getGoods().get(position - 1).getBrand_id());
        viewHolder.catePrice.setText(brandIntentEty.getGoods().get(position - 1).getPrice() + "");
    }
}
