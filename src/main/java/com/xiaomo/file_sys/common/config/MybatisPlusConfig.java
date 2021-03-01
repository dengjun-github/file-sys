package com.xiaomo.file_sys.common.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: MybatisPlusConfig
 * Created by mc on 2020/2/18 11:56
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页拦截器
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

   /* *//**
     * 逻辑删除
     * @return
     *//*
    @Bean
    public ISqlInjector IsqlInjector(){
        return new LogicSqlInjector();
    }

    *//**
     * 逻辑删除策略 bean注入方式
     * @return
     *//*
    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("1");
        conf.setLogicNotDeleteValue("0");
        // 自增主键
        conf.setIdType(0);
        return conf;
    }*/

}
