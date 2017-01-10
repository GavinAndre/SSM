package com.java.gavinandre.dao;

import com.java.gavinandre.pojo.Items;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsDao {

    int insert(@Param("pojo") Items pojo);

    int insertList(@Param("pojos") List<Items> pojo);

    List<Items> select(@Param("pojo") Items pojo);

    Items selectByPrimaryKey(@Param("id") Integer id);

    int update(@Param("pojo") Items pojo);
}
