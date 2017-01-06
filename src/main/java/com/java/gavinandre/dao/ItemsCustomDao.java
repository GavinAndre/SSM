package com.java.gavinandre.dao;

import com.java.gavinandre.pojo.ItemsCustom;
import com.java.gavinandre.pojo.ItemsQueryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gavinandre on 17-1-4.
 */
@Repository
public interface ItemsCustomDao {
    // 商品查询列表
    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
            throws Exception;
}
