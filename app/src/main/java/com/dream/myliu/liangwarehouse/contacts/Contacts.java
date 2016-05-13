package com.dream.myliu.liangwarehouse.contacts;

/**
 * Created by Amethyst on 16/1/11/14/14.
 */
public class Contacts {
    //订单文件名
    public static final String FINE_NAME = "ordername";

    //分类的网址连接
    public static final String CATEGORYHTTP = "http://iliangcang.com:8200/good/categories?app_key=Android&build=2015120701";
    //分类列表页的跳转的拼接
    public static final String CATE_ITEM_HTTP_FIRST="http://app.iliangcang.com/goods/class?type=100&cat_code=";
    public static final String CATE_ITEM_HTTP_SECOND="&app_key=Android&build=2015120701&count=20&page=";
    public static final String CATE_ITEM_HTTP_THREE="&self_host=1&v=1.0";
    //分类的详情页面的拼接
    public static final String CATE_DET_HTTP_FIRST ="http://app.iliangcang.com/goods?app_key=Android&build=2015120701&goods_id=";
    //231870&v=1.0";

    //分享页面的网址连接拼接
    public static final String SHAREHTTP = "http://app.iliangcang.com/goods/class?app_key=iphone&build=150&count=1000&osVersion=84&page=1&v=2.2.6";


    //杂志的网址拼接
    public static final String MZGHTTP="http://app.iliangcang.com/topic/listinfo?app_key=iphone&build=150&osVersion=84&v=2.2.6";
    //良仓的品牌的网址连接的拼接
    public static final String BRANDHTTP_B= "http://iliangcang.com:8200/brand/list/2?start="; //品牌的前半段
    public static final String BRANDHTTP_H = "&offset=20&app_key=Android&build=2015120701"; //品牌的后半段
    //数据库静态
    public static final String DATABASE_NAME = "liangwarehouse.db";

    //达人网址
    public static final String DARENHTTP="http://app.iliangcang.com/expert?app_key=Android&build=2015120701&count=18&page=1&v=1.0";
    public static final String DAREN_RECOM_HTTP_FIRST="http://app.iliangcang.com/user/";
    public static final String DAREN_RECOM_HTTP_SECOND = "?app_key=Android&build=2015120701&count=18&owner_id=";
    public static final String DAREN_RECOM_HTTP_THIRD = "&page=";
    public static final String DAREN_RECOM_HTTP_FOUR = "&v=1.0";



    public static final String SEARCHHTTPF = "http://app.iliangcang.com/search?app_key=Android&build=2015120701&count=18&keywords=";
    public static final String SEARCHHTTPS= "&page=";
    public static final String SEARCHHTTPT ="&v=1.0";

    //首页的网址地址
    public static final String HOMEHTTP="http://api.iliangcang.com/i/appshophome?app_key=Android&build=2015120701&v=1.0";

    public static final String ORDER="order";

    //视频网址接口,进行拼接
    public static final String VIDEO_URL_ADDRESS = "http://c.m.163.com/nc/video/home/0-500.html";
}
