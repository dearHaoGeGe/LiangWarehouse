package com.dream.myliu.liangwarehouse.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dream.myliu.liangwarehouse.BaseApplication;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.VideoEntity;
import com.dream.myliu.liangwarehouse.ui.adapter.VedioAdapter;
import com.dream.myliu.liangwarehouse.ui.fragment.BaseFragment;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumingyan on 15/10/28.
 */
public class VideoFragment extends BaseFragment {
    private VolleySingleton volleySingleton = VolleySingleton.getInstance();
    private RecyclerView recyclerView;
    private VedioAdapter vedioAdapter;

    @Override
    protected int getLayout() {
        return R.layout.surfaceview_item;
    }

    /**
     * 初始化页面函数
     *
     * @param view 加载的布局文件
     **/
    @Override
    public void initView(View view) {
        //初始化新闻列表
        recyclerView = (RecyclerView) view.findViewById(R.id.videoRecylcer);
        GridLayoutManager gm = new GridLayoutManager(BaseApplication.getmContext(), 1);
        //设置布局管理器的方向
        gm.setOrientation(LinearLayoutManager.VERTICAL);
        //将布局管理器设置到列表中
        recyclerView.setLayoutManager(gm);

    }

    @Override
    protected void initData() {
        initDataRecycler();
    }

    private void initDataRecycler() {
        //解析网络数据的地址 ,将数据添加到适配器
        StringRequest request = new StringRequest(Contacts.VIDEO_URL_ADDRESS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        VideoEntity videoEntity = gson.fromJson(response, VideoEntity.class);
                        if (videoEntity != null) {

                            List<VideoEntity.VideoListEntity> entities = videoEntity.getVideoList();
                            if (entities != null && entities.size() > 0) {
                                Log.i("-----------------------", videoEntity.getVideoHomeSid() + "");
                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < entities.size() ; i++) {
                                    list.add(entities.get(i).getMp4_url());
                                }
                                vedioAdapter = new VedioAdapter(getActivity());
                                vedioAdapter.addData(entities);
                                recyclerView.setAdapter(vedioAdapter);
//
                            }
                            dismissDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        dismissDialog();
                        toastError();
                    }
                });
        showDialog();
        volleySingleton.addRequest(request);
    }
}
