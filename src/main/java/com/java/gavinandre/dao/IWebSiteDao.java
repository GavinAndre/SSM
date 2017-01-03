package com.java.gavinandre.dao;

import com.java.gavinandre.pojo.WebSite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IWebSiteDao {

    int insert(@Param("pojo") WebSite pojo);

    int insertList(@Param("pojos") List< WebSite> pojo);

    List<WebSite> select(@Param("pojo") WebSite pojo);

    WebSite selectByPrimaryKey(int id);

    int update(@Param("pojo") WebSite pojo);

}
