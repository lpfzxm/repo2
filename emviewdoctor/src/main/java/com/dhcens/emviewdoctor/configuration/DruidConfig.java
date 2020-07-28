package com.dhcens.emviewdoctor.configuration;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author : zhibao
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druid() {
        return new DruidDataSource();
    }
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //传入我们druid提供的servlet 和访问的地址
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        Map<String, String> beanMap = new HashMap<>();
        beanMap.put("loginUsername", "admin");//登录名
        beanMap.put("loginPassword", "123456");//登录密码
        beanMap.put("allow", "");//允许谁登录，默认或者空 是允许所有的登录
        //还有很多参数有时间研究
        bean.setInitParameters(beanMap);
        return bean;
    }
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        Map<String, String> beanMap = new HashMap<>();
        //排除不过滤请求的url
        beanMap.put("exclusions", "*.js,*.css,/druid/*");
        registrationBean.setInitParameters(beanMap);
        //拦截过滤所有的请求
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
}
