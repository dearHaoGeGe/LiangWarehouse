package com.dream.myliu.liangwarehouse.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.dream.myliu.liangwarehouse.greendao.SpecilData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SPECIL_DATA".
*/
public class SpecilDataDao extends AbstractDao<SpecilData, Void> {

    public static final String TABLENAME = "SPECIL_DATA";

    /**
     * Properties of entity SpecilData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Taid = new Property(0, String.class, "taid", false, "TAID");
        public final static Property Topic_name = new Property(1, String.class, "topic_name", false, "TOPIC_NAME");
        public final static Property Cat_id = new Property(2, String.class, "cat_id", false, "CAT_ID");
        public final static Property Author_id = new Property(3, String.class, "author_id", false, "AUTHOR_ID");
        public final static Property Topic_url = new Property(4, String.class, "topic_url", false, "TOPIC_URL");
        public final static Property Access_url = new Property(5, String.class, "access_url", false, "ACCESS_URL");
        public final static Property Cover_img = new Property(6, String.class, "cover_img", false, "COVER_IMG");
        public final static Property Addtime = new Property(7, String.class, "addtime", false, "ADDTIME");
        public final static Property Is_show_list = new Property(8, String.class, "is_show_list", false, "IS_SHOW_LIST");
        public final static Property Publish_type = new Property(9, String.class, "publish_type", false, "PUBLISH_TYPE");
        public final static Property Publish_time = new Property(10, String.class, "publish_time", false, "PUBLISH_TIME");
        public final static Property Is_published = new Property(11, String.class, "is_published", false, "IS_PUBLISHED");
        public final static Property Cover_img_new = new Property(12, String.class, "cover_img_new", false, "COVER_IMG_NEW");
        public final static Property Hit_number = new Property(13, String.class, "hit_number", false, "HIT_NUMBER");
        public final static Property Goods_number = new Property(14, String.class, "goods_number", false, "GOODS_NUMBER");
        public final static Property Cat_name = new Property(15, String.class, "cat_name", false, "CAT_NAME");
    };


    public SpecilDataDao(DaoConfig config) {
        super(config);
    }
    
    public SpecilDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SPECIL_DATA\" (" + //
                "\"TAID\" TEXT," + // 0: taid
                "\"TOPIC_NAME\" TEXT," + // 1: topic_name
                "\"CAT_ID\" TEXT," + // 2: cat_id
                "\"AUTHOR_ID\" TEXT," + // 3: author_id
                "\"TOPIC_URL\" TEXT," + // 4: topic_url
                "\"ACCESS_URL\" TEXT," + // 5: access_url
                "\"COVER_IMG\" TEXT," + // 6: cover_img
                "\"ADDTIME\" TEXT," + // 7: addtime
                "\"IS_SHOW_LIST\" TEXT," + // 8: is_show_list
                "\"PUBLISH_TYPE\" TEXT," + // 9: publish_type
                "\"PUBLISH_TIME\" TEXT," + // 10: publish_time
                "\"IS_PUBLISHED\" TEXT," + // 11: is_published
                "\"COVER_IMG_NEW\" TEXT," + // 12: cover_img_new
                "\"HIT_NUMBER\" TEXT," + // 13: hit_number
                "\"GOODS_NUMBER\" TEXT," + // 14: goods_number
                "\"CAT_NAME\" TEXT);"); // 15: cat_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SPECIL_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SpecilData entity) {
        stmt.clearBindings();
 
        String taid = entity.getTaid();
        if (taid != null) {
            stmt.bindString(1, taid);
        }
 
        String topic_name = entity.getTopic_name();
        if (topic_name != null) {
            stmt.bindString(2, topic_name);
        }
 
        String cat_id = entity.getCat_id();
        if (cat_id != null) {
            stmt.bindString(3, cat_id);
        }
 
        String author_id = entity.getAuthor_id();
        if (author_id != null) {
            stmt.bindString(4, author_id);
        }
 
        String topic_url = entity.getTopic_url();
        if (topic_url != null) {
            stmt.bindString(5, topic_url);
        }
 
        String access_url = entity.getAccess_url();
        if (access_url != null) {
            stmt.bindString(6, access_url);
        }
 
        String cover_img = entity.getCover_img();
        if (cover_img != null) {
            stmt.bindString(7, cover_img);
        }
 
        String addtime = entity.getAddtime();
        if (addtime != null) {
            stmt.bindString(8, addtime);
        }
 
        String is_show_list = entity.getIs_show_list();
        if (is_show_list != null) {
            stmt.bindString(9, is_show_list);
        }
 
        String publish_type = entity.getPublish_type();
        if (publish_type != null) {
            stmt.bindString(10, publish_type);
        }
 
        String publish_time = entity.getPublish_time();
        if (publish_time != null) {
            stmt.bindString(11, publish_time);
        }
 
        String is_published = entity.getIs_published();
        if (is_published != null) {
            stmt.bindString(12, is_published);
        }
 
        String cover_img_new = entity.getCover_img_new();
        if (cover_img_new != null) {
            stmt.bindString(13, cover_img_new);
        }
 
        String hit_number = entity.getHit_number();
        if (hit_number != null) {
            stmt.bindString(14, hit_number);
        }
 
        String goods_number = entity.getGoods_number();
        if (goods_number != null) {
            stmt.bindString(15, goods_number);
        }
 
        String cat_name = entity.getCat_name();
        if (cat_name != null) {
            stmt.bindString(16, cat_name);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public SpecilData readEntity(Cursor cursor, int offset) {
        SpecilData entity = new SpecilData( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // taid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // topic_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cat_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // topic_url
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // access_url
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // cover_img
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // addtime
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // is_show_list
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // publish_type
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // publish_time
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // is_published
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // cover_img_new
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // hit_number
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // goods_number
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // cat_name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SpecilData entity, int offset) {
        entity.setTaid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTopic_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCat_id(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAuthor_id(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTopic_url(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAccess_url(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCover_img(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAddtime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIs_show_list(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setPublish_type(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPublish_time(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIs_published(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCover_img_new(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setHit_number(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setGoods_number(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setCat_name(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(SpecilData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(SpecilData entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
