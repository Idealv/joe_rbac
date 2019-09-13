package com.joe.rbac.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan("com.joe.rbac.mapper")
public class MybatisPlusConfig {

    @Bean
    @ConditionalOnMissingBean
    public DataScopeInterceptor dataScopeInterceptor(DataSource dataSource){
        return new DataScopeInterceptor(dataSource);
    }

}
