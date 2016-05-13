package com.dream.myliu.liangwarehouse.ui.activity;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 由Amethyst于2016年01月05日10点30分创建
 * 用于管理activity 避免出现内存泄漏 不能释放当前的activity
 */
public class ActivityControl {
    private static final Map<String, Activity> activityMap = new HashMap<>();

    public static void addAty(String key, Activity aty) {
        activityMap.put(key, aty);
    }

    public static boolean removeAty(String key) {
        Activity aty = activityMap.remove(key);
        if (aty != null) {
            return true;
        }
        return false;
    }

    public static void finishAll() {
        Set<String> activities = activityMap.keySet();
        for (String key : activities) {
            activityMap.get(key).finish();
        }
    }
}
