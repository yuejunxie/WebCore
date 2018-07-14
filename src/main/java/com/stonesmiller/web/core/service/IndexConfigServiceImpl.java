package com.stonesmiller.web.core.service;

import com.stonesmiller.web.core.repo.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/6/28 22:13
 * Description:
 */
@Service
public class IndexConfigServiceImpl {

    @Autowired
    private IndexDao indexDao;


    public void addIndexConfig(String id, String name) {
        indexDao.add(id, name);
    }
}
