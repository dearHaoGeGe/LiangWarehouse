package com.dream.myliu.liangwarehouse.entity;

import java.util.List;

/**
 * Created by Amethyst on 16/1/13/05/58.
 */
public class MzgEty {
    private MzgDataEyy data;


    public MzgDataEyy getData() {
        return data;
    }

    public void setData(MzgDataEyy data) {
        this.data = data;
    }

    public class MzgDataEyy {
        private List<String> keys;
        private Object infos;

        public List<String> getKeys() {
            return keys;
        }

        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        public Object getInfos() {
            return infos;
        }

        public void setInfos(Object infos) {
            this.infos = infos;
        }
    }


}
