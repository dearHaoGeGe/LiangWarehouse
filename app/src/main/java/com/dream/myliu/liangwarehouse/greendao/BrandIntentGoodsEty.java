package com.dream.myliu.liangwarehouse.greendao;

import java.util.List;
import com.dream.myliu.liangwarehouse.greendao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "BRAND_INTENT_GOODS_ETY".
 */
public class BrandIntentGoodsEty {

    private String description;
    private Long id;
    private Integer brand_id;
    private String sale_by;
    private Long price;
    private String name;
    private Long brand_intent_img_id;
    private Long brand_intent_goods_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient BrandIntentGoodsEtyDao myDao;

    private BrandIntentGoodsEty brandIntentGoodsEty;
    private Long brandIntentGoodsEty__resolvedKey;

    private List<BrandIntentGoodsImgEty> images;

    public BrandIntentGoodsEty() {
    }

    public BrandIntentGoodsEty(Long brand_intent_goods_id) {
        this.brand_intent_goods_id = brand_intent_goods_id;
    }

    public BrandIntentGoodsEty(String description, Long id, Integer brand_id, String sale_by, Long price, String name, Long brand_intent_img_id, Long brand_intent_goods_id) {
        this.description = description;
        this.id = id;
        this.brand_id = brand_id;
        this.sale_by = sale_by;
        this.price = price;
        this.name = name;
        this.brand_intent_img_id = brand_intent_img_id;
        this.brand_intent_goods_id = brand_intent_goods_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBrandIntentGoodsEtyDao() : null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public String getSale_by() {
        return sale_by;
    }

    public void setSale_by(String sale_by) {
        this.sale_by = sale_by;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrand_intent_img_id() {
        return brand_intent_img_id;
    }

    public void setBrand_intent_img_id(Long brand_intent_img_id) {
        this.brand_intent_img_id = brand_intent_img_id;
    }

    public Long getBrand_intent_goods_id() {
        return brand_intent_goods_id;
    }

    public void setBrand_intent_goods_id(Long brand_intent_goods_id) {
        this.brand_intent_goods_id = brand_intent_goods_id;
    }

    /** To-one relationship, resolved on first access. */
    public BrandIntentGoodsEty getBrandIntentGoodsEty() {
        Long __key = this.brand_intent_goods_id;
        if (brandIntentGoodsEty__resolvedKey == null || !brandIntentGoodsEty__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BrandIntentGoodsEtyDao targetDao = daoSession.getBrandIntentGoodsEtyDao();
            BrandIntentGoodsEty brandIntentGoodsEtyNew = targetDao.load(__key);
            synchronized (this) {
                brandIntentGoodsEty = brandIntentGoodsEtyNew;
            	brandIntentGoodsEty__resolvedKey = __key;
            }
        }
        return brandIntentGoodsEty;
    }

    public void setBrandIntentGoodsEty(BrandIntentGoodsEty brandIntentGoodsEty) {
        synchronized (this) {
            this.brandIntentGoodsEty = brandIntentGoodsEty;
            brand_intent_goods_id = brandIntentGoodsEty == null ? null : brandIntentGoodsEty.getBrand_intent_goods_id();
            brandIntentGoodsEty__resolvedKey = brand_intent_goods_id;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<BrandIntentGoodsImgEty> getImages() {
        if (images == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BrandIntentGoodsImgEtyDao targetDao = daoSession.getBrandIntentGoodsImgEtyDao();
            List<BrandIntentGoodsImgEty> imagesNew = targetDao._queryBrandIntentGoodsEty_Images(brand_intent_goods_id);
            synchronized (this) {
                if(images == null) {
                    images = imagesNew;
                }
            }
        }
        return images;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetImages() {
        images = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}