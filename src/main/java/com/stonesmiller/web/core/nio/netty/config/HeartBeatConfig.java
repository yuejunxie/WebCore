package com.stonesmiller.web.core.nio.netty.config;

import com.stonesmiller.web.core.nio.netty.protocol.CustomProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/8/2 20:04
 * Description:
 */
//@Configuration
public class HeartBeatConfig {

    @Value("${channel.id}")
    private long id;


    @Bean(value = "heartBeat")
    public CustomProtocol heartBeat() {
        return new CustomProtocol(id, "ping");
    }
}
