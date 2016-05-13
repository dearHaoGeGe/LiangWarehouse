package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.BaseApplication;
import com.dream.myliu.liangwarehouse.greendao.CateDetDataEty;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

/**
 * Created by Amethyst on 16/1/20/23/03.
 */
public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private GoodHeaderImgAdapter adapter;
    private CateDetDataEty cateDetDataEty;
    private ImageLoader imageLoader;
    private LayoutInflater inflater = LayoutInflater.from(BaseApplication.getmContext());
    private Context context;


    public GoodsAdapter(Context context) {
        this.context = context;
        this.imageLoader = VolleySingleton.getInstance().getImageLoader();
        adapter = new GoodHeaderImgAdapter(context);
    }

    public void addData(CateDetDataEty cateDetDataEty){
        this.cateDetDataEty = cateDetDataEty;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE00:
                view = inflater.inflate(R.layout.goods_det_item1, null);
//                if (listener != null) {
//                    view.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            listener.recyItemClickLisenter(v);
//                        }
//                    });
//                }
                return new ViewHolder00(view);
            case TYPE01:
                //商品详情页面的条状
                view = inflater.inflate(R.layout.goods_det_item2, null);
                return new ViewHolder01(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder00){
            adapter.addLosdDatas(cateDetDataEty.getImages_item());
            ((ViewHolder00) holder).goodImgsVp.setAdapter(adapter);
        }else if (holder instanceof ViewHolder01){
            ViewHolder01 holder01 = (ViewHolder01) holder;
            visibily(holder01);
            setContent(holder01);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    private static final int TYPE00 = 0;
    private static final int TYPE01 = 1;

    //获得两种不同的布局类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE00; //商品页面的viewpager实现图片滑动,当点击图片时会跳回到原网页
        }else if (position == 1){
            return TYPE01; //商品的具体介绍
        }
        return 0;
    }

    public static class ViewHolder00 extends RecyclerView.ViewHolder {
        private ViewPager goodImgsVp;
        public ViewHolder00(View itemView) {
            super(itemView);
            goodImgsVp = (ViewPager) itemView.findViewById(R.id.goodImgsVp);
        }
    }

    public static class ViewHolder01 extends RecyclerView.ViewHolder {
        private TextView goodPrices,goodsGod, goodOwnerName,goodGoodsname,goodsDesc,goodRecommend,
                goodsRecommendHeaderTv,goodsBrandInfoNameTv,goodGuideContTv;
        private NetworkImageView goodsBrandLogoIv;
        private ImageView goodsRecommendHeaderIv;
        public ViewHolder01(View itemView) {
            super(itemView);
            goodsRecommendHeaderIv = (ImageView) itemView.findViewById(R.id.goodsRecommendHeaderIv);
            goodsBrandLogoIv = (NetworkImageView) itemView.findViewById(R.id.goodsBrandLogoIv);
            goodPrices = (TextView) itemView.findViewById(R.id.goodPrices);
            goodsGod = (TextView) itemView.findViewById(R.id.goodsGod);
            goodOwnerName = (TextView) itemView.findViewById(R.id.goodOwnerName);
            goodGoodsname = (TextView) itemView.findViewById(R.id.goodGoodsname);
            goodsDesc = (TextView) itemView.findViewById(R.id.goodsDesc);
            goodsRecommendHeaderTv = (TextView) itemView.findViewById(R.id.goodsRecommendHeaderTv);
            goodRecommend = (TextView) itemView.findViewById(R.id.goodRecommend);
            goodsBrandInfoNameTv = (TextView) itemView.findViewById(R.id.goodsBrandInfoNameTv);
            goodGuideContTv = (TextView) itemView.findViewById(R.id.goodGuideContTv);
        }
    }

    private void visibily(ViewHolder01 viewHolder){
        viewHolder.goodPrices.setVisibility(View.VISIBLE);
        viewHolder.goodsGod.setVisibility(View.VISIBLE);
        viewHolder.goodOwnerName.setVisibility(View.VISIBLE);
        viewHolder.goodGoodsname.setVisibility(View.VISIBLE);
        viewHolder. goodsDesc.setVisibility(View.VISIBLE);
        viewHolder.goodsRecommendHeaderTv.setVisibility(View.VISIBLE);
        viewHolder.goodRecommend.setVisibility(View.VISIBLE);
        viewHolder.goodsBrandInfoNameTv.setVisibility(View.VISIBLE);
        viewHolder.goodGuideContTv.setVisibility(View.VISIBLE);
    }

    private void setContent(ViewHolder01 viewHolder){
        viewHolder.goodPrices.setText("$" + cateDetDataEty.getPrice());
        viewHolder.goodsGod.setText("" +cateDetDataEty.getLike_count());
        viewHolder.goodOwnerName.setText(cateDetDataEty.getOwner_name() +"");
        viewHolder.goodGoodsname.setText(cateDetDataEty.getGoods_name() + "");
        viewHolder.goodsDesc.setText(cateDetDataEty.getGoods_desc()+"");
        viewHolder.goodsRecommendHeaderTv.setText("良仓");
        viewHolder.goodRecommend.setText(cateDetDataEty.getRec_reason());
        viewHolder.goodGuideContTv.setText(cateDetDataEty.getGood_guide().getContent());
        viewHolder.goodsBrandInfoNameTv.setText(cateDetDataEty.getBrand_info().getBrand_name());
        viewHolder. goodsRecommendHeaderIv.setImageResource(R.mipmap.liangcang_logo_80);
        viewHolder.goodsBrandLogoIv.setDefaultImageResId(R.mipmap.demo_people);
        viewHolder.goodsBrandLogoIv.setDefaultImageResId(R.mipmap.demo_people);
        viewHolder.goodsBrandLogoIv.setImageUrl(cateDetDataEty.getBrand_info().getBrand_logo(), imageLoader);
    }


}
