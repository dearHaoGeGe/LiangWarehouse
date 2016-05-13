package com.dream.myliu.liangwarehouse.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "GOODS_GUIDE_ETY".
 */
public class GoodsGuideEty {

    private String title;
    private String content;
    private Long good_guide_id;

    public GoodsGuideEty() {
    }

    public GoodsGuideEty(Long good_guide_id) {
        this.good_guide_id = good_guide_id;
    }

    public GoodsGuideEty(String title, String content, Long good_guide_id) {
        this.title = title;
        this.content = content;
        this.good_guide_id = good_guide_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGood_guide_id() {
        return good_guide_id;
    }

    public void setGood_guide_id(Long good_guide_id) {
        this.good_guide_id = good_guide_id;
    }

}