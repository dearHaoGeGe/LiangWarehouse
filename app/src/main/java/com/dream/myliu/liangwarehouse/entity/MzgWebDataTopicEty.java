package com.dream.myliu.liangwarehouse.entity;

/**
 * Created by Amethyst on 16/1/21/14/25.
 */
public class MzgWebDataTopicEty {
    private String taid;
    private String topic_name;
    private String content;
    private String topic_url;

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
