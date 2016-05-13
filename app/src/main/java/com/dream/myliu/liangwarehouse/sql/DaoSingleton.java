package com.dream.myliu.liangwarehouse.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dream.myliu.liangwarehouse.BaseApplication;
import com.dream.myliu.liangwarehouse.contacts.Contacts;
import com.dream.myliu.liangwarehouse.greendao.CateChildEntity;
import com.dream.myliu.liangwarehouse.greendao.CateCoverEty;
import com.dream.myliu.liangwarehouse.greendao.CateCoverEtyDao;
import com.dream.myliu.liangwarehouse.greendao.CateDataEtyDao;
import com.dream.myliu.liangwarehouse.greendao.CateItemsEty;
import com.dream.myliu.liangwarehouse.greendao.DaoMaster;
import com.dream.myliu.liangwarehouse.greendao.DaoSession;
import com.dream.myliu.liangwarehouse.greendao.DarenData;
import com.dream.myliu.liangwarehouse.greendao.DarenDataDao;
import com.dream.myliu.liangwarehouse.greendao.DarenPersonDataEty;
import com.dream.myliu.liangwarehouse.greendao.DarenPersonDataEtyDao;
import com.dream.myliu.liangwarehouse.greendao.ShareDataEty;
import com.dream.myliu.liangwarehouse.greendao.ShareDataEtyDao;
import com.dream.myliu.liangwarehouse.greendao.UserGoodsCartEty;
import com.dream.myliu.liangwarehouse.greendao.UserGoodsCartEtyDao;

import java.util.List;
import java.util.Map;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Amethyst on 16/1/16/19/54.
 */
public class DaoSingleton {
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private Context context;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DarenDataDao darenDataDao;

    //使用单例模式保证只有一个对象
    private DaoSingleton() {
        this.context = BaseApplication.getmContext();
    }

    public static DaoSingleton getInstance() {
        return DaoSingletonHolder.instance;
    }

    public static final class DaoSingletonHolder {
        private static final DaoSingleton instance = new DaoSingleton();
    }

    public DaoMaster.DevOpenHelper getDevOpenHelper() {
        if (devOpenHelper == null) {
            devOpenHelper = new DaoMaster.DevOpenHelper(context, Contacts.DATABASE_NAME, null);
        }
        return devOpenHelper;
    }

    private SQLiteDatabase getDb() {
        return getDevOpenHelper().getWritableDatabase();
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }


    // TODO: 16/1/20
    public DarenDataDao getDarenDataDao() {
        if (darenDataDao == null) {
            darenDataDao = getDaoSession().getDarenDataDao();
        }
        return darenDataDao;
    }


    private UserGoodsCartEtyDao userGoodsCartEtyDao;

    public UserGoodsCartEtyDao getUserGoodsCartEtyDao() {
        if (userGoodsCartEtyDao == null) {
            userGoodsCartEtyDao = getDaoSession().getUserGoodsCartEtyDao();
        }
        return userGoodsCartEtyDao;
    }

    public void insertUserGoodsCartEty(List<UserGoodsCartEty> userGoodsCartEty) {

        getUserGoodsCartEtyDao().insertOrReplaceInTx(userGoodsCartEty);
    }

    public void insertUserGoodsCartEty(UserGoodsCartEty userGoodsCartEty) {
        //确保当前用户同一商品插入到同一个列表中
        if (queryUserGoodsCartEty(userGoodsCartEty) != null)
            userGoodsCartEty.setCounts(userGoodsCartEty.getCounts() + queryUserGoodsCartEty(userGoodsCartEty).getCounts());
//        getUserGoodsCartEtyDao().delete(queryUserGoodsCartEty(userGoodsCartEty));
        getUserGoodsCartEtyDao().insertOrReplace(userGoodsCartEty);
    }

    public List<UserGoodsCartEty> queryUserGoodsCartEty() {
        return getUserGoodsCartEtyDao().loadAll();
    }


    public UserGoodsCartEty queryUserGoodsCartEty(UserGoodsCartEty userGoodsCartEty) {
        String goodName = userGoodsCartEty.getGood_name();
        String userName = userGoodsCartEty.getUser_name();
        QueryBuilder<UserGoodsCartEty> qb = getDaoSession().getUserGoodsCartEtyDao().queryBuilder();
        qb.where(UserGoodsCartEtyDao.Properties.Good_name.eq(goodName), UserGoodsCartEtyDao.Properties.User_name.eq(userName));
        qb.limit(1);
        if (qb.list().size() > 0)
            return qb.list().get(0);
        else
            return null;
    }

    public void deleteUserGoodsCartEty(UserGoodsCartEty userGoodsCartEty) {
        getUserGoodsCartEtyDao().delete(userGoodsCartEty);
    }


    public void insertDaren(List<DarenData> darenDatas) {
        getDarenDataDao().insertOrReplaceInTx(darenDatas);

    }

    public List<DarenData> loadDarenAll() {
        return getDaoSession().getDarenDataDao().loadAll();

    }

    //查询
    public List<DarenData> searchDaren() {
        if (getDaoSession().getDarenDataDao().loadAll() != null) {
            return getDaoSession().getDarenDataDao().loadAll();
        }
        return null;
    }

    //删除
    public void deleteDaren() {
        if (searchDaren() != null) {
            getDaoSession().getShareDataEtyDao().deleteAll();
        }
    }

    // 达人界面的增删改查
    private DarenPersonDataEtyDao darenPersonDataEtyDao;

    private DarenPersonDataEtyDao getDarenPersonDataEtyDao() {
        if (darenPersonDataEtyDao == null) {
            darenPersonDataEtyDao = getDaoSession().getDarenPersonDataEtyDao();
        }
        return darenPersonDataEtyDao;
    }


    public void insertDarenPreson(DarenPersonDataEty darenPersonDataEty, String user_id) {
        getDarenPersonDataEtyDao().insert(darenPersonDataEty);
    }


    //查询
    public DarenPersonDataEty searchDarenPreson(String user_id) {
        if (getDaoSession().getDarenPersonDataEtyDao().loadAll() != null) {
            return getDarenPersonDataEtyDao().queryBuilder().
                    where(DarenPersonDataEtyDao.Properties.User_id.eq(user_id)).unique();
        }
        return null;
    }

    //删除
    public void deleteDarenPreson(String user_id) {
        if (searchDarenPreson(user_id) != null) {
            getDarenPersonDataEtyDao().deleteAll();
        }

    }

    //分类功能的增删改查
    private CateDataEtyDao cateDataEtyDao;

    public CateDataEtyDao getCateDataEtyDao() {
        if (cateDataEtyDao == null) {
            cateDataEtyDao = getDaoSession().getCateDataEtyDao();
        }
        return cateDataEtyDao;
    }

    public void deleteCateData() {
        if (searchShare() != null) {
            getCateDataEtyDao().deleteAll();
        }
    }

    public List<CateItemsEty> searchCateItemsEty() {
        List<CateItemsEty> cateItems = getDaoSession().getCateItemsEtyDao().loadAll();
        for (int i = 0; i < cateItems.size(); i++) {
            CateCoverEty coverEty = searchCateCoverEty(cateItems.get(i).getCover_id());
            if (coverEty != null) {
                cateItems.get(i).setCover(coverEty);
            }
        }

        return cateItems;
    }

    public CateCoverEty searchCateCoverEty(long coverId) {
        QueryBuilder<CateCoverEty> qb = getDaoSession().getCateCoverEtyDao().queryBuilder();
        qb.where(CateCoverEtyDao.Properties.Cover_id.eq(coverId));
        qb.limit(1);
        if (qb.list().size() > 0)
            return qb.list().get(0);
        else
            return null;

    }

    public void insertCateItemsEty(List<CateItemsEty> cateItemsEties, List<CateCoverEty> cateCoverEties,Map<Integer, List<CateChildEntity>> cateChildEntities) {
        if (searchCateItemsEty().size() == 0) {
            for (int i = 0; i < cateCoverEties.size(); i++) {
                //插入cover
                long coverId = getDaoSession().getCateCoverEtyDao().insert(cateCoverEties.get(i));
                CateItemsEty cateItemsEty = cateItemsEties.get(i);
                cateItemsEty.setCover_id(coverId);
                //二级列表的添加
                for (int j = 0; j < cateChildEntities.get(i).size(); j++) {
                    getDaoSession().getCateChildEntityDao().insert(cateChildEntities.get(i).get(j));
                }
                getDaoSession().getCateItemsEtyDao().insert(cateItemsEty);
//                getDaoSession().getCateItemsEtyDao().insertInTx(cateItemsEties);
            }
        }
    }

    //分享功能的增删改查操作
    private ShareDataEtyDao shareDataEtyDao;

    public ShareDataEtyDao getShareDataEtyDao() {
        if (shareDataEtyDao == null) {
            shareDataEtyDao = getDaoSession().getShareDataEtyDao();
        }
        return shareDataEtyDao;
    }

    //插入
    public void insertShare(List<ShareDataEty> shareDataEties) {
        deleteShare();
        getDaoSession().getShareDataEtyDao().insertInTx(shareDataEties);
    }

    //查询全部
    public List<ShareDataEty> loadShareAll() {
        return getShareDataEtyDao().loadAll();
    }


    //查询
    public List<ShareDataEty> searchShare() {

        if (getDaoSession().getShareDataEtyDao().loadAll() != null) {
            return getDaoSession().getShareDataEtyDao().loadAll();
        }
        return null;
    }

    //删除
    public void deleteShare() {
        if (searchShare() != null) {
            getDaoSession().getShareDataEtyDao().deleteAll();
        }
    }
}
