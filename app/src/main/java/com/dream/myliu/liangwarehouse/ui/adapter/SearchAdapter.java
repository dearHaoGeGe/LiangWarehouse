package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.greendao.SearchData;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/17/21/53.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<SearchData> datas;
    private Context context;
    private ImageLoader imageLoader;
    public SearchAdapter(Context context){
        this.context = context;
        if (datas == null){
            datas = new ArrayList<>();
        }
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }
    public void initData(List<SearchData> searchDatas){
        this.datas.addAll(searchDatas);

    }
    private boolean isDatasEmpty(){
        return datas != null && datas.size() > 0;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_goods,null);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        if (datas.get(position).getGoods_image() != null){
            holder.searchGood.setImageUrl(datas.get(position).getGoods_image(), imageLoader);
            holder.searchGood.setErrorImageResId(R.mipmap.icon_error);
            holder.searchGood.setDefaultImageResId(R.mipmap.demo_2);
        }
    }

    @Override
    public int getItemCount() {
        return isDatasEmpty() ? datas.size() : 0;
    }
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (datas.get(position).getGoods_image() != null){
//            holder.
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return isDatasEmpty() ? datas.size() : 0;
//    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        private ImageView girdLiangcang;
        private NetworkImageView searchGood;
        public SearchViewHolder(View itemView) {
            super(itemView);
            girdLiangcang = (ImageView) itemView.findViewById(R.id.girdLiangcang);
            searchGood = (NetworkImageView) itemView.findViewById(R.id.searchGood);
        }
    }

}
