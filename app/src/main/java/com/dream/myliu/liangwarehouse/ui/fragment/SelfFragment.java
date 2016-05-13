package com.dream.myliu.liangwarehouse.ui.fragment;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.dream.myliu.liangwarehouse.BaseApplication;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.lib.CaptureActivity;
import com.dream.myliu.liangwarehouse.ui.DataCleanManager;
import com.dream.myliu.liangwarehouse.ui.activity.ActivityControl;
import com.dream.myliu.liangwarehouse.ui.activity.LoginActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by Amethyst on 16/1/12/07/27.
 */
public class SelfFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int getLayout() {
        return R.layout.self_item;
    }

    @Override
    protected void initView(View view) {
//        findView(R.id.locationRl, view).setOnClickListener(this);
        findView(R.id.loginRl, view).setOnClickListener(this);
        findView(R.id.contionRl, view).setOnClickListener(this);
        findView(R.id.scanRl, view).setOnClickListener(this);
        findView(R.id.shakeRl, view).setOnClickListener(this);
        findView(R.id.shareRl, view).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.loginRl:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.shareRl:
                showShare();
                break;
            case R.id.contionRl:
                ActivityControl.finishAll();
                System.exit(0);

                break;
//            case R.id.locationRl:
//                break;
            case R.id.scanRl:
                intent = new Intent(getActivity(), CaptureActivity.class);
                getActivity().startActivityForResult(intent, 444);
                break;
            case R.id.shakeRl:
                DataCleanManager.cleanApplicationData();
                Toast.makeText(getActivity(), "清除缓存", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、 微信、人人网和QQ空间使用
        oks.setTitle("第三方分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
// 启动分享GUI
        oks.show(getActivity());
    }
}
