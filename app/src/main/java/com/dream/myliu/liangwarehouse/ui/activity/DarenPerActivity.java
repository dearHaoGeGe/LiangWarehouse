package com.dream.myliu.liangwarehouse.ui.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.sql.DaoSingleton;
import com.dream.myliu.liangwarehouse.entity.DarenPerEty;
import com.dream.myliu.liangwarehouse.greendao.DarenPersonDataEty;
import com.dream.myliu.liangwarehouse.greendao.DarenRecmEty;
import com.dream.myliu.liangwarehouse.ui.adapter.ListBaseAdapter;
import com.dream.myliu.liangwarehouse.view.NewGirdView;
import com.dream.myliu.liangwarehouse.volley.GsonRequest;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amethyst on 16/1/19/15/42.
 */
public class DarenPerActivity extends BaseActivity implements View.OnClickListener {
    private NetworkImageView dIHeaderIv;
    private TextView dDarenNameTv, sDarenProTv, darenLikeTv, darenRecommendTv, darenAttentionTv, darenFansTv;
    private NewGirdView newGirdView;
    private VolleySingleton singleton;
    private LayoutInflater inflater;
    private DarenPersonDataEty darenData = null;
    private List<DarenRecmEty> darenRecmEties = new ArrayList<>();
    private DarenRecommendAdapter recomAdapter;
    private String choices[] = {"recommendation", "like", "following", "followed"};
    private ImageLoader imageLoader;
    private String owner_id;

    @Override
    protected int getLayout() {
        return R.layout.activity_daren_person;
    }

    @Override
    protected void initView() {
        dIHeaderIv = findView(R.id.dIHeaderIv);
        dDarenNameTv = findView(R.id.dDarenNameTv);
        sDarenProTv = findView(R.id.sDarenProTv);
        darenLikeTv = findView(R.id.darenLikeTv);
        darenRecommendTv = findView(R.id.darenRecommendTv);
        darenAttentionTv = findView(R.id.darenAttentionTv);
        darenFansTv = findView(R.id.darenFansTv);
//        darenCon = findView(R.id.darenCon);
//        darenWrite = findView(R.id.darenWrite);
        newGirdView = findView(R.id.darenItGV);
        View view1 = LayoutInflater.from(this).inflate(R.layout.recommend_item, null);
//        toolbar.setTitle("达人");
        mToolBarHelper.getTextView().setText("达人");
        toolbar.setNavigationIcon(R.mipmap.actionbar_navigation_back);
        toolbar.setNavigationOnClickListener(this);

    }

    @Override
    protected void initData() {
        owner_id = getIntent().getStringExtra("user_id");
        Log.d("ppppp", owner_id);
        imageLoader = VolleySingleton.getInstance().getImageLoader();
        recomAdapter = new DarenRecommendAdapter(this);
        initRecGird();
    }

    private void initRecGird() {
        singleton = VolleySingleton.getInstance();
        GsonRequest<DarenPerEty> request = new GsonRequest<DarenPerEty>(Contacts.DAREN_RECOM_HTTP_FIRST + "recommendation" + Contacts.DAREN_RECOM_HTTP_SECOND + owner_id + Contacts.DAREN_RECOM_HTTP_THIRD + "1" + Contacts.DAREN_RECOM_HTTP_FOUR, DarenPerEty.class, new Response.Listener<DarenPerEty>() {
            @Override
            public void onResponse(DarenPerEty response) {
                newGirdView.setNumColumns(2);
                darenData = response.getData();
                darenRecmEties = darenData.getGoods();
                recomAdapter.addData(darenRecmEties, true);
                dIHeaderIv.setImageUrl(darenData.getUser_image(), imageLoader);
                dDarenNameTv.setText(darenData.getUser_name().toString() + "");
                sDarenProTv.setText(darenData.getUser_desc().toString() + "");
                newGirdView.setAdapter(recomAdapter);
                toolbar.setTitle(darenData.getUser_name() + "");

                if (darenData != null)
                    DaoSingleton.getInstance().insertDarenPreson(darenData, darenData.getUser_id());
                dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                darenData = DaoSingleton.getInstance().searchDarenPreson(owner_id);
                if (darenData != null) {
                    recomAdapter.addData(darenData.getGoods(), false);
                    newGirdView.setNumColumns(2);
                    darenRecmEties = darenData.getGoods();
                    recomAdapter.addData(darenRecmEties, false);
                    recomAdapter.addData(darenRecmEties, true);
                    dIHeaderIv.setImageUrl(darenData.getUser_image(), imageLoader);
                    dDarenNameTv.setText(darenData.getUser_name().toString() + "");
                    sDarenProTv.setText(darenData.getUser_desc().toString() + "");
                    newGirdView.setAdapter(recomAdapter);

                    dismissDialog();
                } else {
                    toastError();
                }
                recomAdapter.addData(darenData.getGoods(), false);
                newGirdView.setNumColumns(2);
                darenRecmEties = darenData.getGoods();
                recomAdapter.addData(darenRecmEties, true);
                dIHeaderIv.setImageUrl(darenData.getUser_image(), imageLoader);
                dDarenNameTv.setText(darenData.getUser_name().toString() + "");
                sDarenProTv.setText(darenData.getUser_desc().toString() + "");
                newGirdView.setAdapter(recomAdapter);
            }
        });
        singleton.addRequest(request, tag);
        showDialog();
    }

    @Override
    public void onClick(View v) {
        finish();
    }


    public class DarenRecommendAdapter extends ListBaseAdapter<List<DarenRecmEty>> {
        private Context context;
        private ImageLoader imageLoader;

        @Override
        public void addData(List<DarenRecmEty> datas, boolean isRefresh) {
            this.entities = datas;
            notifyDataSetChanged();
        }

        public DarenRecommendAdapter(Context context) {
            this.context = context;
            imageLoader = VolleySingleton.getInstance().getImageLoader();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.recommend_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.recommendDarenIv.setDefaultImageResId(R.mipmap.demo_people);
            holder.recommendDarenIv.setImageUrl(entities.get(position).getGoods_image(), imageLoader);
            holder.recommendDarenIv.setErrorImageResId(R.mipmap.demo_2);
//            holder.darenProTv.setText(entities.get(position).getUser_desc() + "");
//            holder.darenNameTv.setText(entities.get(position).getUser_name() + "");
            return convertView;
        }

        class ViewHolder {
            private NetworkImageView recommendDarenIv;
            private TextView darenNameTv, darenProTv;
            public ViewHolder(View itemView) {
                this.recommendDarenIv = (NetworkImageView) itemView.findViewById(R.id.recommendDarenIv);
            }
        }
    }
}
