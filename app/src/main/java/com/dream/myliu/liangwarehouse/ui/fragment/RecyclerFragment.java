package com.dream.myliu.liangwarehouse.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Amethyst on 16/1/13/10/05.
 */
public class RecyclerFragment extends Fragment {
    private static final String POSITION = "position";
    private TextView tv;

    public static Fragment newInstance(int position) {
        RecyclerFragment f = null;
        if (f == null) {
            f = new RecyclerFragment();
        }
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tv = new TextView(getActivity());
        tv.setTextSize(35);
        tv.setTextColor(Color.BLUE);
        return tv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        int position = args.getInt(POSITION);
        tv.setText(String.valueOf(position) + "ddddddd");
    }
}
