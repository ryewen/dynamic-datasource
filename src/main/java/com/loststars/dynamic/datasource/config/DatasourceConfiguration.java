package com.loststars.dynamic.datasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.loststars.dynamic.datasource.util.DynamicDataSource;
import com.loststars.dynamic.datasource.util.MultipleDataSourceHelper;

@Configuration
public class DatasourceConfiguration {
    
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dynamicDatasource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(MultipleDataSourceHelper.MASTER, masterDataSource());
        dataSourceMap.put(MultipleDataSourceHelper.SLAVE, slaveDataSource());
        DynamicDataSource dds = new DynamicDataSource(); //路由
        dds.setTargetDataSources(dataSourceMap);
        dds.setDefaultTargetDataSource(masterDataSource());
        return dds;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 给mybatis指定上面配置好的动态数据源
        sqlSessionFactoryBean.setDataSource(dynamicDatasource());
        // 自己配置mybatis的话，这个必须要指定mapper位置，在application.yml里配置的不会生效了
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDatasource());
    }
}
