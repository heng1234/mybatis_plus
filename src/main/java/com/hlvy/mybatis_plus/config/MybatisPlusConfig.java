package com.hlvy.mybatis_plus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * MybatisPlusConfig
 *
 * @author heng
 * @date 2019/4/17
 **/
//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan("com.hlvy.mybatis_plus.mapper")
public class MybatisPlusConfig {

    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
    /**
     * SQL执行效率插件 性能分析插件
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor =  new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);//格式化语句
        //performanceInterceptor.setMaxTime(5);//执行时间超过多少秒会抛出异常
        return  performanceInterceptor;
    }

    /**
     * 逻辑删除 3.1版本及以下需要加入
     * @return
     */
   /* @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }*/
    /**
     * 分页插件
     * @return
     */

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
         */
      /*  List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
           *//* @Override
            public Expression getTenantId(boolean where) {
                // 该 where 条件 3.2.0 版本开始添加的，用于分区是否为在 where 条件中使用
                // 此判断用于支持返回多个租户 ID 场景，具体使用查看示例工程
                return new LongValue(1L);
            }*//*

            @Override
            public Expression getTenantId() {
                return new LongValue(5L);//传入的值一般都是配置文件 静态变量或者session中取出
            }

            @Override
            public String getTenantIdColumn() {
                return "manager_id";//多租户字段
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 这里可以判断是否过滤表
            *//*
            if ("user".equals(tableName)) {
                return true;
            }*//*
                return false;
            }
        });
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);

        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
                //你要过滤的userMapper里的方法
                if ("com.hlvy.mybatis_plus.mapper.UserMapper.selectById".equals(ms.getId())) {
                    return true;
                }
                return false;
            }
        });*/
        return  paginationInterceptor;
    }


}