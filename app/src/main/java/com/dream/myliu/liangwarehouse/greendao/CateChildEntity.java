package com.dream.myliu.liangwarehouse.greendao;

import com.dream.myliu.liangwarehouse.greendao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CATE_CHILD_ENTITY".
 */
public class CateChildEntity {

    private Long _id_child;
    private String code;
    private String name;
    private String parent_id;
    private Integer id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CateChildEntityDao myDao;

    private CateItemsEty cateItemsEty;
    private Long cateItemsEty__resolvedKey;


    public CateChildEntity() {
    }

    public CateChildEntity(Long _id_child) {
        this._id_child = _id_child;
    }

    public CateChildEntity(Long _id_child, String code, String name, String parent_id, Integer id) {
        this._id_child = _id_child;
        this.code = code;
        this.name = name;
        this.parent_id = parent_id;
        this.id = id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCateChildEntityDao() : null;
    }

    public Long get_id_child() {
        return _id_child;
    }

    public void set_id_child(Long _id_child) {
        this._id_child = _id_child;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** To-one relationship, resolved on first access. */
    public CateItemsEty getCateItemsEty() {
        Long __key = this._id_child;
        if (cateItemsEty__resolvedKey == null || !cateItemsEty__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CateItemsEtyDao targetDao = daoSession.getCateItemsEtyDao();
            CateItemsEty cateItemsEtyNew = targetDao.load(__key);
            synchronized (this) {
                cateItemsEty = cateItemsEtyNew;
            	cateItemsEty__resolvedKey = __key;
            }
        }
        return cateItemsEty;
    }

    public void setCateItemsEty(CateItemsEty cateItemsEty) {
        synchronized (this) {
            this.cateItemsEty = cateItemsEty;
            _id_child = cateItemsEty == null ? null : cateItemsEty.get_id_item_cate();
            cateItemsEty__resolvedKey = _id_child;
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
