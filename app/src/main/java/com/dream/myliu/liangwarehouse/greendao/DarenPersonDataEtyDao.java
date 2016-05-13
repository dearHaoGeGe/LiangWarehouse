package com.dream.myliu.liangwarehouse.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.dream.myliu.liangwarehouse.greendao.DarenPersonDataEty;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAREN_PERSON_DATA_ETY".
*/
public class DarenPersonDataEtyDao extends AbstractDao<DarenPersonDataEty, Long> {

    public static final String TABLENAME = "DAREN_PERSON_DATA_ETY";

    /**
     * Properties of entity DarenPersonDataEty.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property User_id = new Property(0, String.class, "user_id", false, "USER_ID");
        public final static Property User_name = new Property(1, String.class, "user_name", false, "USER_NAME");
        public final static Property Is_daren = new Property(2, String.class, "is_daren", false, "IS_DAREN");
        public final static Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public final static Property User_image = new Property(4, String.class, "user_image", false, "USER_IMAGE");
        public final static Property User_desc = new Property(5, String.class, "user_desc", false, "USER_DESC");
        public final static Property Friend = new Property(6, Integer.class, "friend", false, "FRIEND");
        public final static Property Like_count = new Property(7, String.class, "like_count", false, "LIKE_COUNT");
        public final static Property Recommendation_count = new Property(8, String.class, "recommendation_count", false, "RECOMMENDATION_COUNT");
        public final static Property Following_count = new Property(9, String.class, "following_count", false, "FOLLOWING_COUNT");
        public final static Property Followed_count = new Property(10, String.class, "followed_count", false, "FOLLOWED_COUNT");
        public final static Property Template_id = new Property(11, String.class, "template_id", false, "TEMPLATE_ID");
        public final static Property _id_daren = new Property(12, Long.class, "_id_daren", true, "_ID_DAREN");
    };

    private DaoSession daoSession;


    public DarenPersonDataEtyDao(DaoConfig config) {
        super(config);
    }
    
    public DarenPersonDataEtyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAREN_PERSON_DATA_ETY\" (" + //
                "\"USER_ID\" TEXT," + // 0: user_id
                "\"USER_NAME\" TEXT," + // 1: user_name
                "\"IS_DAREN\" TEXT," + // 2: is_daren
                "\"EMAIL\" TEXT," + // 3: email
                "\"USER_IMAGE\" TEXT," + // 4: user_image
                "\"USER_DESC\" TEXT," + // 5: user_desc
                "\"FRIEND\" INTEGER," + // 6: friend
                "\"LIKE_COUNT\" TEXT," + // 7: like_count
                "\"RECOMMENDATION_COUNT\" TEXT," + // 8: recommendation_count
                "\"FOLLOWING_COUNT\" TEXT," + // 9: following_count
                "\"FOLLOWED_COUNT\" TEXT," + // 10: followed_count
                "\"TEMPLATE_ID\" TEXT," + // 11: template_id
                "\"_ID_DAREN\" INTEGER PRIMARY KEY AUTOINCREMENT );"); // 12: _id_daren
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAREN_PERSON_DATA_ETY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DarenPersonDataEty entity) {
        stmt.clearBindings();
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(1, user_id);
        }
 
        String user_name = entity.getUser_name();
        if (user_name != null) {
            stmt.bindString(2, user_name);
        }
 
        String is_daren = entity.getIs_daren();
        if (is_daren != null) {
            stmt.bindString(3, is_daren);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String user_image = entity.getUser_image();
        if (user_image != null) {
            stmt.bindString(5, user_image);
        }
 
        String user_desc = entity.getUser_desc();
        if (user_desc != null) {
            stmt.bindString(6, user_desc);
        }
 
        Integer friend = entity.getFriend();
        if (friend != null) {
            stmt.bindLong(7, friend);
        }
 
        String like_count = entity.getLike_count();
        if (like_count != null) {
            stmt.bindString(8, like_count);
        }
 
        String recommendation_count = entity.getRecommendation_count();
        if (recommendation_count != null) {
            stmt.bindString(9, recommendation_count);
        }
 
        String following_count = entity.getFollowing_count();
        if (following_count != null) {
            stmt.bindString(10, following_count);
        }
 
        String followed_count = entity.getFollowed_count();
        if (followed_count != null) {
            stmt.bindString(11, followed_count);
        }
 
        String template_id = entity.getTemplate_id();
        if (template_id != null) {
            stmt.bindString(12, template_id);
        }
 
        Long _id_daren = entity.get_id_daren();
        if (_id_daren != null) {
            stmt.bindLong(13, _id_daren);
        }
    }

    @Override
    protected void attachEntity(DarenPersonDataEty entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12);
    }    

    /** @inheritdoc */
    @Override
    public DarenPersonDataEty readEntity(Cursor cursor, int offset) {
        DarenPersonDataEty entity = new DarenPersonDataEty( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // user_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // user_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // is_daren
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // email
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // user_image
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // user_desc
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // friend
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // like_count
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // recommendation_count
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // following_count
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // followed_count
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // template_id
            cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12) // _id_daren
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DarenPersonDataEty entity, int offset) {
        entity.setUser_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUser_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIs_daren(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUser_image(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUser_desc(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFriend(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setLike_count(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRecommendation_count(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFollowing_count(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setFollowed_count(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTemplate_id(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.set_id_daren(cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DarenPersonDataEty entity, long rowId) {
        entity.set_id_daren(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DarenPersonDataEty entity) {
        if(entity != null) {
            return entity.get_id_daren();
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