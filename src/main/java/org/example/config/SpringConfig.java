package org.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(  // 这个是 Spring 的配置，剩下的都是 MyBatis 的配置
        basePackages = "org.example",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class),
                // 排除 WebConfig，避免非 Web 环境加载 @EnableWebMvc
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class)
        }
)
@MapperScan("org.example.mapper") // 3. 配置Mapper扫描
public class SpringConfig {

    // 1. 配置数据源
    @Bean
    public DataSource dataSource() {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ssmlab_tyut?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

    // 2. 配置SqlSessionFactory
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
}