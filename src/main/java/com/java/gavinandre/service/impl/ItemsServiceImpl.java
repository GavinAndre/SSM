package com.java.gavinandre.service.impl;

import com.java.gavinandre.dao.ItemsCustomDao;
import com.java.gavinandre.dao.ItemsDao;
import com.java.gavinandre.pojo.Items;
import com.java.gavinandre.pojo.ItemsCustom;
import com.java.gavinandre.pojo.ItemsQueryVo;
import com.java.gavinandre.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gavinandre on 17-1-4.
 */
@Service("ItemsService")
public class ItemsServiceImpl implements ItemsService {

    @Resource
    private ItemsCustomDao itemsCustomDao;

    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsCustomDao.findItemsList(itemsQueryVo);
    }

    @Resource
    private ItemsDao itemsDao;

    @Override
    public ItemsCustom findItemsById(int id) throws Exception {
        Items items = itemsDao.selectByPrimaryKey(id);

        //在这里以后随着需求的变化，需要查询商品的其它相关信息，返回到controller
        //所以这个时候用到扩展类更好，如下
        ItemsCustom itemsCustom = new ItemsCustom();
        //将items的属性拷贝到itemsCustom
        BeanUtils.copyProperties(items, itemsCustom);

        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //在service中一定要写业务代码


        //对于关键业务数据的非空校验
        if (id == null) {
            //抛出异常，提示调用接口的用户，id不能唯恐
            //...
        }

        itemsDao.update(itemsCustom);
    }
}
