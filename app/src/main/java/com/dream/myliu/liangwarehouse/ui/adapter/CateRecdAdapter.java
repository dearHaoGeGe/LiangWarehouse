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
import com.dream.myliu.liangwarehouse.greendao.CateListDataEty;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/20/08/26.
 * 分类功能的物品列表页面
 * <p/>
 * //基页面网址
 * http://iliangcang.com:8200/good/categories?app_key=Android&build=2015120701
 * //列表页跳转
 * http://app.iliangcang.com/goods/class?type=100&cat_code=00020012&app_key=Android&build=2015120701&count=20&page=1&self_host=1&v=1.0
 * //详情页跳转
 *
 */
public class CateRecdAdapter extends RecyclerView.Adapter<CateRecdAdapter.CateViewHolder> {
    private Context context;
    private List<CateListDataEty> cateListDataEties;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CateRecdAdapter(Context context) {
        imageLoader = VolleySingleton.getInstance().getImageLoader();
        this.context = context;
        if (cateListDataEties == null){
            this.cateListDataEties = new ArrayList<>();
        }
    }

    private boolean isEntityEmpty() {
        return cateListDataEties != null && cateListDataEties.size() > 0;
    }

    public void addData(List<CateListDataEty> cateListDataEties) {
        this.cateListDataEties.addAll(cateListDataEties);
        notifyDataSetChanged();
    }



    @Override
    public CateRecdAdapter.CateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView  = LayoutInflater.from(context).inflate(R.layout.cate_activity_item, null);

        return new CateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CateRecdAdapter.CateViewHolder holder, final int position) {
        holder.cateGoodName.setText(cateListDataEties.get(position).getGoods_name() + "");
        holder.cateGoodDes.setText(cateListDataEties.get(position).getBrand_info().getBrand_name()+"");
        holder.catePrice.setText("￥" +cateListDataEties.get(position).getPrice() +"");
        holder.cateNum.setText(""+cateListDataEties.get(position).getLike_count());
        holder.cateGoodIv.setDefaultImageResId(R.mipmap.demo_people);
        holder.cateGoodIv.setErrorImageResId(R.mipmap.demo_2);
        holder.cateGoodIv.setImageUrl(cateListDataEties.get(position).getGoods_image(), imageLoader);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.itemView, position );
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return isEntityEmpty() ? cateListDataEties.size() : 0;
    }


    //
//    @Override
//    public CateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//    }
//
    class CateViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView cateGoodIv;
        private TextView cateGoodName, cateGoodDes, cateNum, catePrice;

        public CateViewHolder(View itemView) {
            super(itemView);
            cateGoodIv = (NetworkImageView) itemView.findViewById(R.id.cateGoodIv);
            cateGoodName = (TextView) itemView.findViewById(R.id.cateGoodName);
            cateGoodDes = (TextView) itemView.findViewById(R.id.cateGoodDes);
            cateNum = (TextView) itemView.findViewById(R.id.cateNum);
            catePrice = (TextView) itemView.findViewById(R.id.catePrice);
        }
    }

    //添加监听接口
    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }

}
