package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String[] args) {
        Schema schema = new Schema(1,
                "com.dream.myliu.liangwarehouse.greendao");
        //品牌的跳转页的logo图标
//        addBLogo(schema);
//        addBCategory(schema);
        //懒人实体类
        addDarenData(schema);
        addSearchEty(schema);
        addDarenPreson(schema);
        addCateEty(schema);
        addSpecilData(schema);
        addShareDataEty(schema);
        addBandEty(schema);
        addCateListDataEty(schema);
        addCateDetailEty(schema);
        addBrandIntentEty(schema);

        addGoodsCart(schema);
        addGoodsOrder(schema);
        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addGoodsCart(Schema schema){
        Entity entity = schema.addEntity("UserGoodsCartEty");
        entity.addStringProperty("user_name");
        entity.addStringProperty("good_name");
        entity.addStringProperty("url_img");
        entity.addLongProperty("good_num");
        entity.addLongProperty("_id").primaryKey().autoincrement();
        entity.addLongProperty("counts");
        entity.addBooleanProperty("isChecked");
        entity.addFloatProperty("onePrice");
    }


    private static void addGoodsOrder(Schema schema){
        Entity entity = schema.addEntity("GoodsOrderEty");
        entity.addStringProperty("user_name");
        entity.addStringProperty("good_name");
        entity.addStringProperty("url_img");
        entity.addLongProperty("_id").primaryKey().autoincrement();
        entity.addLongProperty("counts");
        entity.addFloatProperty("onePrice");

    }

    private static void addCateListDataEty(Schema schema) {
        Entity entity = schema.addEntity("CateListDataEty");
        entity.addStringProperty("goods_id");
        entity.addStringProperty("goods_image");
        entity.addStringProperty("goods_name");
        entity.addStringProperty("price");
        entity.addStringProperty("owner_id");
        entity.addStringProperty("comment_count");
        entity.addStringProperty("like_count");
        entity.addStringProperty("liked");
        entity.addStringProperty("sale_by");
        entity.addStringProperty("goods_url");
        entity.addStringProperty("is_gift");
        entity.addStringProperty("discount_price");
        entity.addStringProperty("coupon_flag");
        entity.addStringProperty("promotion_imgurl");
        Entity entity1 = schema.addEntity("CateDetailEty");
        entity1.addStringProperty("brand_id");
        entity1.addStringProperty("brand_name");
        entity1.addStringProperty("brand_desc");
        entity1.addStringProperty("brand_logo");
    }

    private static void addCateDetailEty(Schema schema) {
        Entity entity = schema.addEntity("CateDetDataEty");
        entity.addStringProperty("goods_id");
        entity.addStringProperty("goods_image");
        entity.addStringProperty("goods_url");
        entity.addStringProperty("goods_name");
        entity.addStringProperty("goods_desc");
        entity.addStringProperty("price");
        entity.addStringProperty("comment_count");
        entity.addStringProperty("like_count");
        entity.addIntProperty("liked");
        entity.addStringProperty("discount_price");
        entity.addBooleanProperty("coupon_flag");
        entity.addIntProperty("owner_id");
        entity.addStringProperty("owner_name");
        entity.addStringProperty("owner_desc");
        entity.addStringProperty("is_daren");
        entity.addStringProperty("owner_image");
        entity.addStringProperty("rec_reason");
        entity.addIntProperty("servicer_id");
        entity.addStringProperty("category_id");
        entity.addStringProperty("able_buy_note");
        //用于实现CateDetBrandEty类
        entity.addLongProperty("good_brand_id");
        entity.addLongProperty("good_guide_id");

        Entity cateDetBrandEty = schema.addEntity("GoodsBrandInfoEty");
        cateDetBrandEty.addStringProperty("brand_id");
        cateDetBrandEty.addStringProperty("brand_name");
        cateDetBrandEty.addStringProperty("brand_desc");
        cateDetBrandEty.addStringProperty("brand_logo");
        cateDetBrandEty.addLongProperty("good_brand_id").primaryKey();

        Entity cateDetGuideEty = schema.addEntity("GoodsGuideEty");
        cateDetGuideEty.addStringProperty("title");
        cateDetGuideEty.addStringProperty("content");
        cateDetGuideEty.addLongProperty("good_guide_id").primaryKey();

    }

    private static void addBandEty(Schema schema) {
        //品牌实体类
        //data层
        Entity brandDataEty = schema.addEntity("BrandDataEty");
        brandDataEty.addLongProperty("_id").primaryKey();
        brandDataEty.addBooleanProperty("has_more");
        brandDataEty.addIntProperty("num_items");
        brandDataEty.addLongProperty("next_start");

        //Item层
        Entity brandItemsEty = schema.addEntity("BrandItem");
        brandItemsEty.addStringProperty("description");
        Property brandItemsPro = brandItemsEty.addLongProperty("_id_b_it").primaryKey().getProperty();
        brandItemsEty.addStringProperty("name");
        brandItemsEty.addIntProperty("id");

        //建立关联
        brandItemsEty.addToOne(brandDataEty, brandItemsPro);
        brandDataEty.addToMany(brandItemsEty, brandItemsPro, "items").setName("items");
        brandItemsEty.addLongProperty("_id_brand_cate");
        brandItemsEty.addLongProperty("_id_cate_logo");

//        //logo
        Entity brandLogo = schema.addEntity("BrandLogo");
        brandLogo.addStringProperty("url");
        Property brandLogoPro = brandLogo.addLongProperty("_id_cate_logo").primaryKey().getProperty();
        //建立关联
//        Property brandLogo_item_pro = brandItemsEty.addLongProperty("_id_cate_logo").getProperty();
//        brandItemsEty.addToOne(brandLogo, brandLogo_item_pro, "logo");

        //category
        Entity brandCategory = schema.addEntity("BrandCategory");
        brandCategory.addStringProperty("name");
//        Property bCatePro = brandCategory.addLongProperty("_id_brand_cate").primaryKey().getProperty();
//        brandCategory.addIntProperty("id");
//      brandItemsEty.addLongProperty("name");
        //建立关联
//        Property brandCatePro = brandItemsEty.addLongProperty("_id_brand_cate").getProperty();
//        brandItemsEty.addToOne(brandCategory, brandCatePro, "category");
    }

    private static void addBrandIntentEty(Schema schema){
        Entity entity = schema.addEntity("BrandIntentEty");
        entity.addLongProperty("id").primaryKey();
        entity.addStringProperty("name");
        entity.addLongProperty("logo_id");
        entity.addStringProperty("description");

        Entity  brandIntenLogo = schema.addEntity("BrandIntentLogoEty");
        brandIntenLogo.addStringProperty("url");
        brandIntenLogo.addLongProperty("logo_id").primaryKey();

        Entity brandIntentGoodsEty = schema.addEntity("BrandIntentGoodsEty");
        brandIntentGoodsEty.addStringProperty("description");
        brandIntentGoodsEty.addLongProperty("id");
        brandIntentGoodsEty.addIntProperty("brand_id");
        brandIntentGoodsEty.addStringProperty("sale_by");
        brandIntentGoodsEty.addLongProperty("price");
        brandIntentGoodsEty.addStringProperty("name");
        brandIntentGoodsEty.addLongProperty("brand_intent_img_id");
        Property property  = brandIntentGoodsEty.addLongProperty("brand_intent_goods_id").primaryKey().getProperty();
        brandIntentGoodsEty.addToOne(brandIntentGoodsEty, property);
        entity.addToMany(brandIntentGoodsEty, property, "goods").setName("goods");

        Entity brandIntentGoodsImgEty = schema.addEntity("BrandIntentGoodsImgEty");
        brandIntentGoodsImgEty.addStringProperty("url");
        Property goodImgPro = brandIntentGoodsImgEty.addLongProperty("brand_intent_img_id").primaryKey().getProperty();

        brandIntentGoodsImgEty.addToOne(brandIntentGoodsEty, goodImgPro);
        brandIntentGoodsEty.addToMany(brandIntentGoodsImgEty, goodImgPro, "images").setName("images");
    }

    private static void addSpecilData(Schema schema) {
        Entity specilData = schema.addEntity("SpecilData");
        specilData.addStringProperty("taid");
        specilData.addStringProperty("topic_name");
        specilData.addStringProperty("cat_id");
        specilData.addStringProperty("author_id");
        specilData.addStringProperty("topic_url");
        specilData.addStringProperty("access_url");
        specilData.addStringProperty("cover_img");
        specilData.addStringProperty("addtime");
        specilData.addStringProperty("is_show_list");
        specilData.addStringProperty("publish_type");
        specilData.addStringProperty("publish_time");
        specilData.addStringProperty("is_published");
        specilData.addStringProperty("cover_img_new");
        specilData.addStringProperty("hit_number");
        specilData.addStringProperty("goods_number");
        specilData.addStringProperty("cat_name");
    }

    private static void addCateEty(Schema schema) {
        //分类功能的实体类
        Entity cateDataEty = schema.addEntity("CateDataEty");
        cateDataEty.addLongProperty("_id").primaryKey();
        cateDataEty.addBooleanProperty("has_more");
        cateDataEty.addIntProperty("num_items");

        Entity cateItemsEty = schema.addEntity("CateItemsEty");
        Property cateItems = cateItemsEty.addLongProperty("_id_item_cate").primaryKey().getProperty();
        cateItemsEty.addStringProperty("name");
        cateItemsEty.addIntProperty("parent_id");
        cateItemsEty.addStringProperty("english_name");
        cateItemsEty.addIntProperty("id");
        cateItemsEty.addLongProperty("cover_id");
        cateItemsEty.addLongProperty("_id_child");
        cateItemsEty.addToOne(cateDataEty, cateItems);
        cateDataEty.addToMany(cateItemsEty, cateItems).setName("items");

        //数组中有单个属性
        Entity coverEty = schema.addEntity("CateCoverEty");
        coverEty.addStringProperty("url");
        coverEty.addLongProperty("cover_id").primaryKey().getProperty();

//        //数组的关联,多表关联
        Entity cateChildEntity = schema.addEntity("CateChildEntity");
        Property cateChildPro = cateChildEntity.addLongProperty("_id_child").primaryKey().getProperty();
        cateChildEntity.addStringProperty("code");
        cateChildEntity.addStringProperty("name");
        cateChildEntity.addStringProperty("parent_id");
        cateChildEntity.addIntProperty("id");

        cateChildEntity.addToOne(cateItemsEty, cateChildPro);
        cateItemsEty.addToMany(cateChildEntity, cateChildPro, "child").setName("children");

    }


    private static void addSearchEty(Schema schema) {
        Entity entity = schema.addEntity("SearchData");
        entity.addLongProperty("_id").primaryKey().unique().autoincrement();
        entity.addStringProperty("goods_id");
        entity.addStringProperty("goods_image");
        entity.addStringProperty("goods_name");
        entity.addStringProperty("price");
        entity.addStringProperty("owner_id");
        entity.addStringProperty("comment_count");
        entity.addIntProperty("liked");
        entity.addStringProperty("sale_by");
        entity.addStringProperty("like_count");
    }


    private static void addDarenData(Schema schema) {
        Entity entity = schema.addEntity("DarenData");
        entity.addLongProperty("_id").primaryKey().unique();
        entity.addStringProperty("user_id");
        entity.addStringProperty("user_name");
        entity.addStringProperty("is_daren");
        entity.addStringProperty("user_image");
        entity.addStringProperty("user_desc");
    }

    private static void addShareDataEty(Schema schema) {
        Entity entity = schema.addEntity("ShareDataEty");
        entity.addLongProperty("_id_share").primaryKey().unique().autoincrement();
        entity.addStringProperty("goods_id");
        entity.addStringProperty("goods_image");
        entity.addStringProperty("goods_name");
        entity.addIntProperty("price");
        entity.addStringProperty("owner_id");
        entity.addStringProperty("comment_count");
        entity.addStringProperty("like_count");
        entity.addIntProperty("liked");
        entity.addStringProperty("sale_by");
        entity.addStringProperty("goods_url");
        entity.addIntProperty("is_gift");
        entity.addIntProperty("discount_price");
        entity.addBooleanProperty("coupon_flag");
        entity.addStringProperty("promotion_imgurl");
    }


    private static void addDarenPreson(Schema schema) {
        Entity darenPerson = schema.addEntity("DarenPersonDataEty");
        darenPerson.addStringProperty("user_id");
        darenPerson.addStringProperty("user_name");
        darenPerson.addStringProperty("is_daren");
        darenPerson.addStringProperty("email");
        darenPerson.addStringProperty("user_image");
        darenPerson.addStringProperty("user_desc");
        darenPerson.addIntProperty("friend");
        darenPerson.addStringProperty("like_count");
        darenPerson.addStringProperty("recommendation_count");
        darenPerson.addStringProperty("following_count");
        darenPerson.addStringProperty("followed_count");
        darenPerson.addStringProperty("template_id");
        Property darenPenPro = darenPerson.addLongProperty("_id_daren").primaryKey().autoincrement().getProperty();


        Entity recomment = schema.addEntity("DarenRecmEty");
        recomment.setTableName("recomment_daren");
        recomment.addStringProperty("goods_id");
        recomment.addStringProperty("goods_image");
        recomment.addStringProperty("goods_name");
        recomment.addStringProperty("price");
        recomment.addStringProperty("owner_id");
        recomment.addStringProperty("comment_count");
        recomment.addStringProperty("like_count");
        recomment.addIntProperty("liked");
        Property darenRecommentPro = recomment.addLongProperty("_id_recomment").primaryKey().getProperty();
        recomment.addToOne(darenPerson, darenRecommentPro);
        darenPerson.addToMany(recomment, darenRecommentPro).setName("goods");


    }

//    private static void addBCategory(Schema schema) {
//        Entity entity = schema.addEntity("BCategory");
//        entity.addLongProperty("_id").primaryKey();
//        entity.addIntProperty("id");
//        entity.addStringProperty("name");
//
//    }
//
//    private static void addBLogo(Schema schema) {
//        Entity entity = schema.addEntity("BLogo");
//        entity.addLongProperty("_id").primaryKey();
//        entity.addIntProperty("url");
//    }
}
