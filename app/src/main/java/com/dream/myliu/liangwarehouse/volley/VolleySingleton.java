package com.dream.myliu.liangwarehouse.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dream.myliu.liangwarehouse.BaseApplication;

/**
 * Created by Risky on 15/10/21.
 */
public class VolleySingleton {
    private RequestQueue queue;
    private ImageLoader imageLoader;
    private volatile static VolleySingleton instance;

    ///静态内部类实现单例
    private static class VolleySingletonHolder {
        private static final VolleySingleton instance = new VolleySingleton();
    }

    public void cancelQuest(Object tag) {
        //用于取消网络请求
        VolleySingleton.getInstance().getQueue().cancelAll(tag);
    }

    private VolleySingleton() {
        queue = getQueue();
        imageLoader = new ImageLoader(queue, new DoubleCache());
    }

    public static VolleySingleton getInstance() {
        return VolleySingletonHolder.instance;
    }

    private RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(BaseApplication.getmContext());
        }
        return queue;
    }

    public static final String TAG = "VolleySingleton";

    public <T> void addRequest(Request<T> request) {
        request.setTag(TAG);
        queue.add(request);
    }

    //tag用于取消网络请求
    public <T> void addRequest(Request<T> request, Object tag) {
        request.setTag(tag);
        queue.add(request);
    }

    public void addRequest(String url, Response.Listener<String> listener,
                           Response.ErrorListener error) {
        StringRequest stringRequest = new StringRequest(url, listener, error);
        addRequest(stringRequest);
    }

    public <T> void addRequest(String url, Class<T> clazz, Response.Listener<T> success, Response.ErrorListener failed) {
        GsonRequest<T> request = new GsonRequest<T>(url, clazz, success, failed);
        addRequest(request);
    }

    public void removeRequest(Object tag) {
        queue.cancelAll(tag);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}
