package com.dream.myliu.liangwarehouse.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amethyst on 16/1/10/21/06.
 */
public class FragmentFactory {
    private static Map<String, BaseFragment> fragmentMap = new HashMap<>();
    private static Map<String, BaseFragment> fragmentMainMap = new HashMap<>();
    public static BaseFragment createMainFragment(int position) {
        BaseFragment f = fragmentMainMap.get(position);
        if (f == null) {
            switch (position) {
                case 0:
                    f = new ShopFragment();
                    break;
                case 1:
                    f = new MzgFragment();
                    break;
                case 2:
                    f = new ShareFragment();
                    break;
                case 3:
                    f = new DarenFragment();
                    break;
                case 4:
                    f = new SelfFragment();
                    break;
                default:
            }
            fragmentMainMap.put(String.valueOf(position), f);
        }
        return f;
    }

    public static Fragment createFragment(int position) {
        BaseFragment f = fragmentMap.get(position);
        if (f == null) {
            switch (position) {
                case 0:
                    f = new CategoryFragment();
                    break;
                case 1:
                    f = new BrandFragment();
                    break;
                case 2:
                    f = new HomeFragment();
                    break;
//                case 3:
//                    f = new SpecialFragment();
//                    break;
                case 3:
                    f = new VideoFragment();
                    break;
                default:
            }
            fragmentMap.put(String.valueOf(position), f);
        }
        return f;
    }
}
