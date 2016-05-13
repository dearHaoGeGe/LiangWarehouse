package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.greendao.CateChildEntity;
import com.dream.myliu.liangwarehouse.greendao.CateItemsEty;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/11/11/43.
 */
public class CategoryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CateItemsEty> itemsEties;
    private ImageLoader imageLoader;

    public CategoryAdapter(Context context) {
        this.context = context;
        this.imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public void addData(List<CateItemsEty> itemsEties) {
        if (this.itemsEties == null) {
            this.itemsEties = new ArrayList<>();
        }
        this.itemsEties = itemsEties;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return itemsEties != null && itemsEties.size() > 0 ? itemsEties.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemsEties.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_iv_cate, null);
            viewHolder = new GroupViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setDefaultImageResId(R.mipmap.icon_default);
        viewHolder.imageView.setErrorImageResId(R.mipmap.icon_error);
        CateItemsEty itemsEty = itemsEties.get(groupPosition);
        viewHolder.imageView.setImageUrl(itemsEty.getCover().getUrl(), imageLoader);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_viewitem_cate, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        CateItemsEty itemsEty = itemsEties.get(groupPosition);
        List<CateChildEntity> childEntities = null;
        if (childEntities == null) {
            childEntities = new ArrayList<>();
        }
        childEntities = itemsEty.getChildren();

        if (childEntities != null && childEntities.size() > 0) {
            for (int i = 0; i < childEntities.size(); i++) {
                holder.textView.setText(childEntities.get(childPosition).getName().toString());
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        private NetworkImageView imageView;

        public GroupViewHolder(View view) {
            imageView = (NetworkImageView) view.findViewById(R.id.cateGroupIv);
        }
    }

    class ChildViewHolder {
        private TextView textView;

        public ChildViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.cateChildTv);
        }
    }

}
