package com.dream.myliu.liangwarehouse.sql;

import java.util.List;

/**
 * Created by Amethyst on 16/1/17/17/13.
 */
public interface DBReadSaveInterface<T> {
    /**
     * 通过标签存储数据到数据库
     * @param entities 解析完毕的实体类集合
     * @param tag 数据的标记
     */
    void saveData(List<T> entities,String tag);

    /**
     * 根据标记读取数据库
     * @param tag 数据的标记
     * @return 返回值为读取之后的集合
     */
    List<T> readData(String tag);
}
