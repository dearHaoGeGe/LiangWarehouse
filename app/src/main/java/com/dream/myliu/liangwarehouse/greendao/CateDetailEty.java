package com.dream.myliu.liangwarehouse.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CATE_DETAIL_ETY".
 */
public class CateDetailEty {

    private String brand_id;
    private String brand_name;
    private String brand_desc;
    private String brand_logo;

    public CateDetailEty() {
    }

    public CateDetailEty(String brand_id, String brand_name, String brand_desc, String brand_logo) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.brand_desc = brand_desc;
        this.brand_logo = brand_logo;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_desc() {
        return brand_desc;
    }

    public void setBrand_desc(String brand_desc) {
        this.brand_desc = brand_desc;
    }

    public String getBrand_logo() {
        return brand_logo;
    }

    public void setBrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }

}
