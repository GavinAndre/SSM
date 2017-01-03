package com.java.gavinandre.service.impl;

import com.java.gavinandre.dao.IWebSiteDao;
import com.java.gavinandre.pojo.WebSite;
import com.java.gavinandre.service.IWebSiteService;
import com.java.gavinandre.pojo.WebSite;
import com.java.gavinandre.service.IWebSiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("webSiteService")
public class WebSiteServiceImpl implements IWebSiteService {

    @Resource
    private IWebSiteDao webSiteDao;

    public WebSite getUserById(int id) {
        return this.webSiteDao.selectByPrimaryKey(id);
    }

    public int insert(WebSite pojo){
        return webSiteDao.insert(pojo);
    }

    public int insertList(List< WebSite> pojos){
        return webSiteDao.insertList(pojos);
    }

    public List<WebSite> select(WebSite pojo){
        return webSiteDao.select(pojo);
    }

    public int update(WebSite pojo){
        return webSiteDao.update(pojo);
    }

}
