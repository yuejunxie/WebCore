package com.stonesmiller.web.core.nio.netty.protocol;

import java.io.Serializable;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/8/2 19:51
 * Description:
 */
public class CustomProtocol implements Serializable {

    private long id;

    private String content;

    public CustomProtocol() {
    }

    public CustomProtocol(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
