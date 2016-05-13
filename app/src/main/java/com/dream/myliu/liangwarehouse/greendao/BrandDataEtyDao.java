package com.dream.myliu.liangwarehouse.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.dream.myliu.liangwarehouse.greendao.BrandDataEty;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BRAND_DATA_ETY".
*/
public class BrandDataEtyDao extends AbstractDao<BrandDataEty, Long> {

    public static final String TABLENAME = "BRAND_DATA_ETY";

    /**
     * Properties of entity BrandDataEty.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property Has_more = new Property(1, Boolean.class, "has_more", false, "HAS_MORE");
        public final static Property Num_items = new Property(2, Integer.class, "num_items", false, "NUM_ITEMS");
        public final static Property Next_start = new Property(3, Long.class, "next_start", false, "NEXT_START");
    };

    private DaoSession daoSession;


    public BrandDataEtyDao(DaoConfig config) {
        super(config);
    }
    
    public BrandDataEtyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BRAND_DATA_ETY\" (" + //
                "\"_ID\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"HAS_MORE\" INTEGER," + // 1: has_more
                "\"NUM_ITEMS\" INTEGER," + // 2: num_items
                "\"NEXT_START\" INTEGER);"); // 3: next_start
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BRAND_DATA_ETY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BrandDataEty entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        Boolean has_more = entity.getHas_more();
        if (has_more != null) {
            stmt.bindLong(2, has_more ? 1L: 0L);
        }
 
        Integer num_items = entity.getNum_items();
        if (num_items != null) {
            stmt.bindLong(3, num_items);
        }
 
        Long next_start = entity.getNext_start();
        if (next_start != null) {
            stmt.bindLong(4, next_start);
        }
    }

    @Override
    protected void attachEntity(BrandDataEty entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BrandDataEty readEntity(Cursor cursor, int offset) {
        BrandDataEty entity = new BrandDataEty( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0, // has_more
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // num_items
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // next_start
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BrandDataEty entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHas_more(cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0);
        entity.setNum_items(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setNext_start(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BrandDataEty entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BrandDataEty entity) {
        if(entity != null) {
            return entity.get_id();
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
