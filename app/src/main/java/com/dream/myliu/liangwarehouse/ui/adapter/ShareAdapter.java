package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.greendao.ShareDataEty;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.List;

/**
 * Created by Amethyst on 16/1/13/09/26.
 */
public class ShareAdapter extends BaseAdapter {
    List<ShareDataEty> shareDataEties;
    private Context context;
    private ImageLoader imageLoader;

    public ShareAdapter(Context context) {
        this.context = context;
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public void initData(List<ShareDataEty> shareDataEties) {
        this.shareDataEties = shareDataEties;
        notifyDataSetChanged();
    }

    private boolean isDataEmpty() {
        return shareDataEties != null && shareDataEties.size() > 0;
    }

    @Override
    public int getCount() {
        return isDataEmpty() ? shareDataEties.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return isDataEmpty() ? shareDataEties.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.networkimage_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setDefaultImageResId(R.mipmap.demo_2);
        holder.imageView.setImageUrl(shareDataEties.get(position).getGoods_image(), imageLoader);

        return convertView;
    }

    class ViewHolder {
        private NetworkImageView imageView;
        public ViewHolder(View itemView) {
            this.imageView = (NetworkImageView) itemView.findViewById(R.id.goneNvIv);
            this.imageView.setVisibility(View.VISIBLE);
        }
    }
}
