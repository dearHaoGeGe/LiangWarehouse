package com.dream.myliu.liangwarehouse.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USER_GOODS_CART_ETY".
 */
public class UserGoodsCartEty {

    private String user_name;
    private String good_name;
    private String url_img;
    private Long good_num;
    private Long _id;
    private Long counts;
    private Boolean isChecked;
    private Float onePrice;

    public UserGoodsCartEty() {
    }

    public UserGoodsCartEty(Long _id) {
        this._id = _id;
    }

    public UserGoodsCartEty(String user_name, String good_name, String url_img, Long good_num, Long _id, Long counts, Boolean isChecked, Float onePrice) {
        this.user_name = user_name;
        this.good_name = good_name;
        this.url_img = url_img;
        this.good_num = good_num;
        this._id = _id;
        this.counts = counts;
        this.isChecked = isChecked;
        this.onePrice = onePrice;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public Long getGood_num() {
        return good_num;
    }

    public void setGood_num(Long good_num) {
        this.good_num = good_num;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Float getOnePrice() {
        return onePrice;
    }

    public void setOnePrice(Float onePrice) {
        this.onePrice = onePrice;
    }

}
