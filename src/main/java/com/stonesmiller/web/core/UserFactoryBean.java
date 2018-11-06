package com.stonesmiller.web.core;

import org.springframework.beans.factory.FactoryBean;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/22 14:08
 * Description:
 */
public class UserFactoryBean implements FactoryBean<String> {

    @Override
    public String getObject() throws Exception {
        return "user";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}
