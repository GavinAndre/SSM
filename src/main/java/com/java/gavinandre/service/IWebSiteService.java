package com.java.gavinandre.service;

import com.java.gavinandre.pojo.WebSite;
import com.java.gavinandre.pojo.WebSite;

import java.util.List;

/**
 * Created by gavinandre on 16-12-30.
 */
public interface IWebSiteService {
    public int insert(WebSite pojo);

    public int insertList(List< WebSite> pojos);

    public List<WebSite> select(WebSite pojo);

    WebSite getUserById(int id);

    public int update(WebSite pojo);

}
