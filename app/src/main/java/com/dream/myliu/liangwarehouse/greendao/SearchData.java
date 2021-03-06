package com.dream.myliu.liangwarehouse.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SEARCH_DATA".
 */
public class SearchData {

    private Long _id;
    private String goods_id;
    private String goods_image;
    private String goods_name;
    private String price;
    private String owner_id;
    private String comment_count;
    private Integer liked;
    private String sale_by;
    private String like_count;

    public SearchData() {
    }

    public SearchData(Long _id) {
        this._id = _id;
    }

    public SearchData(Long _id, String goods_id, String goods_image, String goods_name, String price, String owner_id, String comment_count, Integer liked, String sale_by, String like_count) {
        this._id = _id;
        this.goods_id = goods_id;
        this.goods_image = goods_image;
        this.goods_name = goods_name;
        this.price = price;
        this.owner_id = owner_id;
        this.comment_count = comment_count;
        this.liked = liked;
        this.sale_by = sale_by;
        this.like_count = like_count;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public String getSale_by() {
        return sale_by;
    }

    public void setSale_by(String sale_by) {
        this.sale_by = sale_by;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

}
