package com.dashobard.adp;

/**
 * Copyright © 2018. All rights reserved.
 *
 * @Author: FreezeSoul
 * @CreateDate: 2018/10/5 11:04
 * @Description: BootstrapService
 * @Version: V1.0
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackages="com.dashboard.adp")
@MapperScan("com.dashboard.adp.dao")
public class BootstrapService {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapService.class, args);
    }
}
