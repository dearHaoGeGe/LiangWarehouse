package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.greendao.DarenData;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.List;

/**
 * Created by Amethyst on 16/1/17/14/24.
 */
public class DarenAdapter extends ListBaseAdapter<List<DarenData>> {
    private Context context;
    private ImageLoader imageLoader;

    @Override
    public void addData(List<DarenData> datas, boolean isRefresh) {
        this.entities = datas;
        notifyDataSetChanged();
    }

    public DarenAdapter(Context context) {
        this.context = context;
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.daren_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.darenHeadIv.setDefaultImageResId(R.mipmap.demo_2);
        holder.darenHeadIv.setImageUrl(entities.get(position).getUser_image(), imageLoader);
        holder.darenHeadIv.setErrorImageResId(R.mipmap.demo_2);
        holder.darenProTv.setText(entities.get(position).getUser_desc() + "");
        holder.darenNameTv.setText(entities.get(position).getUser_name() + "");
        return convertView;
    }

    class ViewHolder {
        private NetworkImageView darenHeadIv;
        private TextView darenNameTv, darenProTv;

        public ViewHolder(View itemView) {
            this.darenHeadIv = (NetworkImageView) itemView.findViewById(R.id.darenHeadIv);
            this.darenNameTv = (TextView) itemView.findViewById(R.id.darenNameTv);
            this.darenProTv = (TextView) itemView.findViewById(R.id.darenProTv);
        }
    }
}
