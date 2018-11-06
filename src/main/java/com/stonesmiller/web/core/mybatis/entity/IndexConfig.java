package com.stonesmiller.web.core.mybatis.entity;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/11/2 20:28
 * Description:
 */
public class IndexConfig {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IndexConfig{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
