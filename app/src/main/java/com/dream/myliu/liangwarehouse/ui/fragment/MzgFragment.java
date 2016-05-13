package com.dream.myliu.liangwarehouse.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.entity.MzgDateEty;
import com.dream.myliu.liangwarehouse.ui.activity.MzgActivity;
import com.dream.myliu.liangwarehouse.ui.adapter.MzgAdapter;
import com.dream.myliu.liangwarehouse.view.ParseJason;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Amethyst on 16/1/12/06/58.
 */
public class MzgFragment extends BaseFragment {
    private ExpandableListView expandlistView;
    private List<MzgDateEty> mzgDateEties = new ArrayList<>();
    private MzgAdapter adapter;
    private Handler handler;
    private Animation animation;
    private String date;
    private int oldPosition = -1, newPosition;


    @Override
    protected int getLayout() {
        return R.layout.fragment_mzg;
    }

    @Override
    protected void initView(View view) {
        expandlistView = findView(R.id.expandListMzg, view);
    }

    @Override
    protected void initData() {
        adapter = new MzgAdapter(getActivity());
        expandlistView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    String result = (String) msg.obj;
                    adapter = new MzgAdapter(getContext());
                    mzgDateEties = ParseJason.jsonMzg(result);
                    adapter.initData(ParseJason.jsonMzg(result));
                    expandlistView.setAdapter(adapter);
//                    expandlistView.setAnimation(animation);
                }
                //展开组
                for (int i = 0; i < mzgDateEties.size(); i++) {
                    expandlistView.expandGroup(i);
                }
                return false;
            }
        });
        new NetWorkThread(handler, "http://www.mengxianyi.net/one/homepage.json")
                .start();

        expandlistView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                newPosition = groupPosition;
                return true;
            }
        });

        expandlistView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final  ExpandableListView listView = (ExpandableListView) view;
                int npos = view.pointToPosition(0, 0);// 其实就是firstVisibleItem
                long pos = listView.getExpandableListPosition(npos);
                int groupPos = ExpandableListView.getPackedPositionGroup(pos);

                if (mzgDateEties.size() > 0) {
                    if (oldPosition != groupPos && groupPos >= 0) {
                        String date = null;
                            date = mzgDateEties.get(groupPos).getKey().substring(5);
                        //发送事件 //发送位置及当前的日期
                        EventBus.getDefault().post(date + groupPos);
                        oldPosition = groupPos;
                    }
                }
            }
        });

        expandlistView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        expandlistView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //跳转的网址
//                925&user_id=521732222&v=1.0
                //网址拼接的当时
                String url = "http://app.iliangcang.com/topic/view?app_key=Android&build=2015120701&sig=521732222%7Ce8599001c2e3ac76dd3ffb689786318f578c1c01&taid="
                + mzgDateEties.get(groupPosition).getDateEty().get(childPosition).getTaid() + "&user_id=" + mzgDateEties.get(groupPosition).getDateEty().get(childPosition).getAuthor_id() + "&v=1.0";
                Intent intent = new Intent(getActivity(), MzgActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                return false;
            }
        });


    }

    public String getDate() {
        return date;
    }

    public class NetWorkThread extends Thread {

        private Handler handler;
        private String url;

        public NetWorkThread(Handler handler, String url) {
            super();
            this.handler = handler;
            this.url = url;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                URL u = new URL(Contacts.MZGHTTP);
                HttpURLConnection connection = (HttpURLConnection) u
                        .openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(is));
                    String line = null;
                    String result = new String();
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    handler.sendMessage(msg);
                    is.close();
                    connection.disconnect();
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
