package com.dream.myliu.liangwarehouse.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.dream.myliu.liangwarehouse.greendao.CateDataEty;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CATE_DATA_ETY".
*/
public class CateDataEtyDao extends AbstractDao<CateDataEty, Long> {

    public static final String TABLENAME = "CATE_DATA_ETY";

    /**
     * Properties of entity CateDataEty.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property Has_more = new Property(1, Boolean.class, "has_more", false, "HAS_MORE");
        public final static Property Num_items = new Property(2, Integer.class, "num_items", false, "NUM_ITEMS");
    };

    private DaoSession daoSession;


    public CateDataEtyDao(DaoConfig config) {
        super(config);
    }
    
    public CateDataEtyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CATE_DATA_ETY\" (" + //
                "\"_ID\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"HAS_MORE\" INTEGER," + // 1: has_more
                "\"NUM_ITEMS\" INTEGER);"); // 2: num_items
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CATE_DATA_ETY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CateDataEty entity) {
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
    }

    @Override
    protected void attachEntity(CateDataEty entity) {
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
    public CateDataEty readEntity(Cursor cursor, int offset) {
        CateDataEty entity = new CateDataEty( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0, // has_more
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2) // num_items
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CateDataEty entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHas_more(cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0);
        entity.setNum_items(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CateDataEty entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(CateDataEty entity) {
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
