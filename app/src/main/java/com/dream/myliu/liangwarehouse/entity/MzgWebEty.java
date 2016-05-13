package com.dream.myliu.liangwarehouse.entity;

/**
 * Created by Amethyst on 16/1/21/14/23.
 */
public class MzgWebEty {
    private MzgWebDataEty data;

    public MzgWebDataEty getData() {
        return data;
    }

    public void setData(MzgWebDataEty data) {
        this.data = data;
    }

    public class MzgWebDataEty{
        private MzgWebDataTopicEty topic_info;

        public MzgWebDataTopicEty getTopic_info() {
            return topic_info;
        }

        public void setTopic_info(MzgWebDataTopicEty topic_info) {
            this.topic_info = topic_info;
        }
    }

}
