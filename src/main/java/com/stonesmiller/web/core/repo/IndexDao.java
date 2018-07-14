package com.stonesmiller.web.core.repo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/6/28 22:07
 * Description:
 */
@Mapper
public interface IndexDao {

    @Insert("INSERT INTO index_config values (#{id},#{name})")
    void add(@Param("id") String id, @Param("name") String name);
}
