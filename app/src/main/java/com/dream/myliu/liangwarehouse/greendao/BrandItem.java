package com.dream.myliu.liangwarehouse.greendao;

import com.dream.myliu.liangwarehouse.greendao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "BRAND_ITEM".
 */
public class BrandItem {

    private String description;
    private Long _id_b_it;
    private String name;
    private Integer id;
    private Long _id_brand_cate;
    private Long _id_cate_logo;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient BrandItemDao myDao;

    private BrandDataEty brandDataEty;
    private Long brandDataEty__resolvedKey;


    public BrandItem() {
    }

    public BrandItem(Long _id_b_it) {
        this._id_b_it = _id_b_it;
    }

    public BrandItem(String description, Long _id_b_it, String name, Integer id, Long _id_brand_cate, Long _id_cate_logo) {
        this.description = description;
        this._id_b_it = _id_b_it;
        this.name = name;
        this.id = id;
        this._id_brand_cate = _id_brand_cate;
        this._id_cate_logo = _id_cate_logo;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBrandItemDao() : null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long get_id_b_it() {
        return _id_b_it;
    }

    public void set_id_b_it(Long _id_b_it) {
        this._id_b_it = _id_b_it;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long get_id_brand_cate() {
        return _id_brand_cate;
    }

    public void set_id_brand_cate(Long _id_brand_cate) {
        this._id_brand_cate = _id_brand_cate;
    }

    public Long get_id_cate_logo() {
        return _id_cate_logo;
    }

    public void set_id_cate_logo(Long _id_cate_logo) {
        this._id_cate_logo = _id_cate_logo;
    }

    /** To-one relationship, resolved on first access. */
    public BrandDataEty getBrandDataEty() {
        Long __key = this._id_b_it;
        if (brandDataEty__resolvedKey == null || !brandDataEty__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BrandDataEtyDao targetDao = daoSession.getBrandDataEtyDao();
            BrandDataEty brandDataEtyNew = targetDao.load(__key);
            synchronized (this) {
                brandDataEty = brandDataEtyNew;
            	brandDataEty__resolvedKey = __key;
            }
        }
        return brandDataEty;
    }

    public void setBrandDataEty(BrandDataEty brandDataEty) {
        synchronized (this) {
            this.brandDataEty = brandDataEty;
            _id_b_it = brandDataEty == null ? null : brandDataEty.get_id();
            brandDataEty__resolvedKey = _id_b_it;
        }
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