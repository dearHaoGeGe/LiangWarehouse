package com.dream.myliu.liangwarehouse.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;

/**
 * Created by Amethyst on 16/1/10/18/27.
 */
public abstract class BaseFragment extends Fragment {
    protected String tag = getClass().getSimpleName();
    private SpotsDialog mDialog;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

//        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog = new SpotsDialog(context, "loading...");
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    protected <T extends View> T findView(int resId, View view) {
        T t = (T) view.findViewById(resId);
        return t;
    }

    protected abstract int getLayout();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected void toastError() {
        Toast.makeText(context, "数据加载失败*_*!", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示dialog
     */
    protected void showDialog() {
        mDialog.show();
    }

    /**
     * 隐藏dialog
     */
    protected void dismissDialog() {
        mDialog.dismiss();
    }




}
