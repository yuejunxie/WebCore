package com.stonesmiller.web.core.mybatis.dao;

import com.stonesmiller.web.core.mybatis.entity.IndexConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/11/2 20:53
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexConfigDaoTest {

    @Autowired
    private IndexConfigDao dao;

    @Test
    public void listIndexConfig() {
        List<IndexConfig> indexConfigs = dao.listIndexConfig();
        indexConfigs.forEach(indexConfig -> System.out.println(indexConfig));

    }
}