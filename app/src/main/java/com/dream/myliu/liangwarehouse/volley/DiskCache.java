package com.dream.myliu.liangwarehouse.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class DiskCache implements ImageLoader.ImageCache {

    static String cacheDir = "/sdcard/Download/";
//    static String cacheDir = SDCardHelper.getSdCardPath();

    @Override
    public Bitmap getBitmap(String url) {
        url = MD5Util.getMD5Str(url);
        return BitmapFactory.decodeFile(cacheDir + url+".jpg");
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        File file = new File(cacheDir);
        if (!file.exists()) {
            file.mkdir();
        }
        url = MD5Util.getMD5Str(url);
        File imageFile = new File(cacheDir, url+".jpg");
        if (!imageFile.exists()) {
            FileOutputStream fileOutputStream = null;
            try {
                imageFile.createNewFile();
                fileOutputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    CloseHelper.close(fileOutputStream);
                }
            }
        }
    }
}
