package com.stonesmiller.web.core.mybatis.config.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mysql.cj.jdbc.Driver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/11/2 20:02
 * Description:
 */
@Configuration
@MapperScan("com.stonesmiller.web.core.mybatis")
public class DataSourceConfiguration {

    @Bean(name = "dataSource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setDriver(new Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("10011001");
        return dataSource;
    }


}
