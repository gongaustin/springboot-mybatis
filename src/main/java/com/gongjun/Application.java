package com.gongjun;

import com.gongjun.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
/**
 * EnableAutoConguration是和自动化配置相关的东西。
 * Spring应用上下文的自动化配置，尝试去猜测和配置你需要的bean。
 * 这些被自动化配置的类通常在classpath路径下，或者是你自己定义的bean。
 * 可以使用exclude来排除那些你不想被自动化配置的类。
 * 被@AutoConfiguration注解的类所在的包有着特殊的意义，
 * 通常被认为是默认的包，并对其及下属的包进行扫描。
 * 被自动配置的类通常是在@Configuration里面配置的类。
 */
@EnableAutoConfiguration
/**
 * 使用@SpringbootApplication注解
 * 可以解决根类或者配置类（我自己的说法，就是main所在类）头上注解过多的问题，
 * 一个@SpringbootApplication相当于@Configuration,@EnableAutoConfiguration和 @ComponentScan
 * 并具有他们的默认属性值
 * */
@SpringBootApplication
/**
 * 其实很简单，@ComponentScan告诉Spring 哪个packages 的用注解标识的类
 * 会被spring自动扫描并且装入bean容器。
 * */
@ComponentScan
@MapperScan("com.gongjun.mapper")
public class Application {
    private static Logger logger = Logger.getLogger(Application.class);

    @Bean
    /**
     * 把配置文件的信息，读取并自动封装成实体类
     * 还可以把@ConfigurationProperties还可以直接定义在@bean的注解上，
     * 这是bean实体类就不用@Component和@ConfigurationProperties了
     * */
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("SpringBoot Start Success");
    }

    /**
     * Start
     */
//    public static void main(String[] args) {
//        初始化Spring环境
//        AnnotationConfigApplicationContext acc = new AnnotationConfigApplicationContext(Application.class);
//        acc.getBean(UserService.class);
//
//    }



}
