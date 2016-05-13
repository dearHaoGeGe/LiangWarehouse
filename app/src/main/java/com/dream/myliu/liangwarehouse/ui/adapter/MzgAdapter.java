package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.entity.MzgDateEty;
import com.dream.myliu.liangwarehouse.entity.MzgInfosEty;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/13/00/14.
 */
public class MzgAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private Animation animation;
    private List<MzgDateEty> mzgDateEties;
    private ImageLoader imageLoader;
    private int groupPos = -1, childPos = -1;
    private int groChildPos = -1;

    public MzgAdapter(Context mContext) {
        this.mContext = mContext;
        if (mzgDateEties == null) {
            mzgDateEties = new ArrayList<>();
        }

        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public void initData(List<MzgDateEty> mzgDateEties) {
        this.mzgDateEties.addAll(mzgDateEties);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mzgDateEties != null && mzgDateEties.size() > 0 ? mzgDateEties.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mzgDateEties.get(groupPosition).getDateEty() != null && mzgDateEties.get(groupPosition).getDateEty().size() > 0 ? mzgDateEties.get(groupPosition).getDateEty().size() : 0;
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
        animation = AnimationUtils.loadAnimation(mContext, R.anim.group_list_item);
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mzg_group_item, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        convertView.startAnimation(animation);
        if (groupPosition > groupPos)
            convertView.startAnimation(animation);
        else {
            convertView.clearAnimation();
        }
        groupPos = groupPosition;
//        convertView.startAnimation(animation);
        if (mzgDateEties != null && mzgDateEties.size() > groupPosition) {
            holder.mzgGouTime.setText(mzgDateEties.get(groupPosition).getKey().toString().substring(5));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        animation = AnimationUtils.loadAnimation(mContext, R.anim.child_list_item);
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mzg_child_item, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        MzgInfosEty infosEty = null;
        infosEty = mzgDateEties.get(groupPosition).getDateEty().get(childPosition);

        holder.mzgCatName.setText("#" + infosEty.getCat_name());
        holder.mzgTopicName.setText(infosEty.getTopic_name());
        holder.mzgCoverIv.setImageUrl(infosEty.getCover_img(), imageLoader);
        holder.mzgCoverIv.setDefaultImageResId(R.mipmap.ic_present_7);
        if ( groupPosition >= groChildPos && childPosition > childPos)
            convertView.startAnimation(animation);
        else {
            convertView.clearAnimation();
        }
        childPos = childPosition;
        groChildPos = groupPosition;
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class GroupViewHolder {
        private TextView mzgGouTime;

        public GroupViewHolder(View itemView) {
            this.mzgGouTime = (TextView) itemView.findViewById(R.id.mzgGouTime);
        }
    }

    class ChildViewHolder {
        private TextView mzgTopicName, mzgCatName;
        private NetworkImageView mzgCoverIv;

        public ChildViewHolder(View itemView) {

            this.mzgTopicName = (TextView) itemView.findViewById(R.id.mzgTopicName);
            this.mzgTopicName.setTypeface(Typeface.MONOSPACE);
            this.mzgCatName = (TextView) itemView.findViewById(R.id.mzgCatName);
            this.mzgCoverIv = (NetworkImageView) itemView.findViewById(R.id.mzgCoverIv);
        }
    }
}
