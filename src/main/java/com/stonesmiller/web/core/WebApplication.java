package com.stonesmiller.web.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/6/28 13:11
 * Description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
