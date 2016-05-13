package com.dream.myliu.liangwarehouse.view;

import com.dream.myliu.liangwarehouse.entity.MzgDateEty;
import com.dream.myliu.liangwarehouse.entity.MzgEty;
import com.dream.myliu.liangwarehouse.entity.MzgInfosEty;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Amethyst on 16/1/13/14/57.
 */
public class ParseJason {

    public static final List<MzgDateEty> jsonMzg(String str) {
        MzgEty data = null;
        JSONArray mzgKeys = null;
        JSONObject mzgInfo;
        List<MzgDateEty> dateDatas  = null;
        List<String> keys = new ArrayList<>();
        if (mzgKeys == null){
            mzgKeys = new JSONArray();
        }
        if (dateDatas == null){
            dateDatas = new ArrayList<>();
        }
        try {
            JSONObject obj = new JSONObject(str);
            if (obj.has("data")) {
                JSONObject mzgData = obj.getJSONObject("data");
                if (mzgData.has("keys")) {
                    mzgKeys = mzgData.getJSONArray("keys");
                    for (int i = 0; i < mzgKeys.length(); i++) {
                        keys.add((String) mzgKeys.get(i));
                    }
                }
                if (mzgData.has("infos")) {
                    mzgInfo = mzgData.getJSONObject("infos");

                    if (mzgKeys != null && mzgKeys.length() > 0) {
                        for (int i = 0; i < mzgKeys.length(); i++) {
                            if (mzgInfo.has(mzgKeys.getString(i))) {
                                JSONArray aaa = mzgInfo.getJSONArray(keys.get(i));
                                List<MzgInfosEty> ccc = new ArrayList<>();
                                MzgDateEty date = new MzgDateEty(ccc, (String) mzgKeys.get(i));
                                for (int j = 0; j < aaa.length(); j++) {
                                    JSONObject bbb = aaa.getJSONObject(j);
                                    MzgInfosEty mInfos = new MzgInfosEty();
                                    mInfos.setNav_title((String) bbb.get("nav_title"));
                                    mInfos.setTaid((String) bbb.get("taid"));
                                    mInfos.setTopic_name((String) bbb.get("topic_name"));
                                    mInfos.setCat_id((String) bbb.get("cat_id"));
                                    mInfos.setAuthor_id((String) bbb.get("author_id"));
                                    mInfos.setTopic_url((String) bbb.get("topic_url"));
                                    mInfos.setAccess_url((String) bbb.get("access_url"));
                                    mInfos.setCover_img((String) bbb.get("cover_img"));
                                    mInfos.setCover_img_new((String) bbb.get("cover_img_new"));
                                    mInfos.setHit_number((Integer) bbb.get("hit_number"));
                                    mInfos.setAddtime((String) bbb.get("addtime"));
                                    mInfos.setAuthor_name((String) bbb.get("author_name"));
                                    mInfos.setCat_name((String) bbb.get("cat_name"));

                                    ccc.add(mInfos);
                                }
                                dateDatas.add(date);
                            }

                        }
                    }
                }

            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateDatas;

    }

}
