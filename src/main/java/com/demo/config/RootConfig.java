package com.demo.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "com.demo.Dao")
@ComponentScan(basePackages = "com.demo",excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {EnableWebMvc.class, Controller.class})})

@PropertySource({"classpath:jdbc.properties"})
public class RootConfig {

        @Value("${driver}")
        private String driver;

        @Value("${url}")
        private String url;

        @Value("${root}")
        private String root;

        @Value("${password}")
        private String password;

        @Bean(name = "datasource")
        public DataSource getDataSource(){
            PooledDataSource dataSource=new PooledDataSource();
            try {
                dataSource.setDriver(driver);
                dataSource.setUrl(url);
                dataSource.setUsername(root);
                dataSource.setPassword(password);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return dataSource;
        }

        @Bean
        public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
            System.out.println("------------------SqlSessionFactoryBean");
            ResourcePatternResolver resourcePatternResolver=new PathMatchingResourcePatternResolver();
            SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(getDataSource());//数据源
            sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mappers/*.xml"));
            return sqlSessionFactoryBean;
        }

}
