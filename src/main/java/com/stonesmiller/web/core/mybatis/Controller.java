package com.stonesmiller.web.core.mybatis;

import com.stonesmiller.web.core.mybatis.dao.IndexConfigDao;
import com.stonesmiller.web.core.mybatis.entity.IndexConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/11/2 20:01
 * Description:
 */
@RestController
@RequestMapping(path = "/index/config")
public class Controller {

    @Autowired
    private IndexConfigDao indexConfigDao;

    public List<IndexConfig> indexConfig() {
        return indexConfigDao.listIndexConfig();
    }
}
