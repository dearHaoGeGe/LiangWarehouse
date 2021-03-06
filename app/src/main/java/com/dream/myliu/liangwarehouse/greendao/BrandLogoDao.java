package com.dream.myliu.liangwarehouse.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.dream.myliu.liangwarehouse.greendao.BrandLogo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BRAND_LOGO".
*/
public class BrandLogoDao extends AbstractDao<BrandLogo, Long> {

    public static final String TABLENAME = "BRAND_LOGO";

    /**
     * Properties of entity BrandLogo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Url = new Property(0, String.class, "url", false, "URL");
        public final static Property _id_cate_logo = new Property(1, Long.class, "_id_cate_logo", true, "_ID_CATE_LOGO");
    };


    public BrandLogoDao(DaoConfig config) {
        super(config);
    }
    
    public BrandLogoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BRAND_LOGO\" (" + //
                "\"URL\" TEXT," + // 0: url
                "\"_ID_CATE_LOGO\" INTEGER PRIMARY KEY );"); // 1: _id_cate_logo
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BRAND_LOGO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BrandLogo entity) {
        stmt.clearBindings();
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(1, url);
        }
 
        Long _id_cate_logo = entity.get_id_cate_logo();
        if (_id_cate_logo != null) {
            stmt.bindLong(2, _id_cate_logo);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1);
    }    

    /** @inheritdoc */
    @Override
    public BrandLogo readEntity(Cursor cursor, int offset) {
        BrandLogo entity = new BrandLogo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // url
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1) // _id_cate_logo
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BrandLogo entity, int offset) {
        entity.setUrl(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.set_id_cate_logo(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BrandLogo entity, long rowId) {
        entity.set_id_cate_logo(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BrandLogo entity) {
        if(entity != null) {
            return entity.get_id_cate_logo();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
