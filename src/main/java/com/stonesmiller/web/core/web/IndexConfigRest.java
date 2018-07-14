package com.stonesmiller.web.core.web;

import com.stonesmiller.web.core.service.IndexConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/6/28 22:14
 * Description:
 */
@RestController
@RequestMapping(path = "/index", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IndexConfigRest {

    @Autowired
    private IndexConfigServiceImpl indexConfigService;

    @GetMapping("/config")
    public String getMapp() {
        indexConfigService.addIndexConfig("yyy", "name");
        return "name";
    }

}
