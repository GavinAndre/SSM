package com.java.gavinandre.service;

import com.java.gavinandre.pojo.ItemsCustom;
import com.java.gavinandre.pojo.ItemsQueryVo;

import java.util.List;

/**
 * Created by gavinandre on 17-1-4.
 */
public interface ItemsService {

    //商品的查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
            throws Exception;

    //根据商品id查询商品信息
    public ItemsCustom findItemsById(int id) throws Exception;

    //更新商品信息
    /**
     * 定义service接口，遵循单一职责，将业务参数细化(不要使用包装类型，比如map)
     * @param id 修改商品的id
     * @param itemsCustom 修改商品的信息
     * @throws Exception
     */
    public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
}
