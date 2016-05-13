package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/20/23/45.
 */
public class GoodHeaderImgAdapter extends PagerAdapter {
    private List<String> images;
    private Context context;
    private ImageLoader imageLoader;

    public GoodHeaderImgAdapter(Context context) {
        this.context = context;
        imageLoader = VolleySingleton.getInstance().getImageLoader();

    }

    public void addRefreshData(List<String> images, boolean isRefresh) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }

        this.images.addAll(images);
        notifyDataSetChanged();

    }

    public void addRefreshData(List<String> images) {
        addRefreshData(images, true);
    }

    public void addLosdDatas(List<String> images) {
        addRefreshData(images, false);
    }


    @Override
    public int getCount() {
        return images != null && images.size() > 0 ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final String image = images.get(position % images.size());
        NetworkImageView imageView = new NetworkImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "image" + position % images.size(), Toast.LENGTH_SHORT).show();
            }
        });
        imageView.setErrorImageResId(R.mipmap.demo_people);
        imageView.setDefaultImageResId(R.mipmap.liangcang_logo_80);
        imageView.setImageUrl(images.get(position % images.size()), imageLoader);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
