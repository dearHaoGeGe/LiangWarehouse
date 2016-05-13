package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.tool.Binding;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.dream.myliu.liangwarehouse.BaseApplication;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.databinding.BrandItemBinding;
import com.dream.myliu.liangwarehouse.entity.BrandItem;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/11/18/28.
 */
public class BrandAdapter extends ListBaseAdapter<List<BrandItem>> {
    private Context context;
    private ImageLoader imageLoader;
    public BrandAdapter(Context context) {
        this.context = context;
        imageLoader = VolleySingleton.getInstance().getImageLoader();
        ImageView imageView;
    }

    @Override
    public void addData(List datas, boolean isRefresh) {
        if (this.entities == null) {
            this.entities = new ArrayList<>();
        }
        if (isRefresh) {
            if (entities != null && entities.size() > 0) {
                entities.remove(datas);
            }   entities.clear();
        }
        this.entities.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.brand_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BrandItem item = entities.get(position);
        Glide.with(this.context).load(item.getLogo().getUrl()).into(viewHolder.binding.brandNetIv);
        viewHolder.binding.setBrandItem(item);
        //Glide图片加载的使用   //使用数据绑定  Data Binding完成图片的加载方式

        return convertView;
    }

    @Override
    public void addLoadDatas(List<BrandItem> ts) {
        super.addLoadDatas(ts);
    }

    @Override
    public void addRefreshData(List<BrandItem> ts) {
        super.addRefreshData(ts);
    }

    public class ViewHolder {
        BrandItemBinding binding;
        public ViewHolder(View viewItem) {
            binding = DataBindingUtil.bind(viewItem);
        }
    }
}
