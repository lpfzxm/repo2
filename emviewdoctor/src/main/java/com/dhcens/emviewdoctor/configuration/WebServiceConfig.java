package com.dhcens.emviewdoctor.configuration;


import com.dhcens.emviewdoctor.service.RedisWebService;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * 发布wbservice服务
 * @author zhibao
 */

@Configuration
public class WebServiceConfig {

    @Bean(name = "cxfServlet")
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Autowired
    public RedisWebService redisWebService;
   @Bean
    public Endpoint endpointUserService() {
        EndpointImpl endpoint = new EndpointImpl(springBus(),redisWebService);
        endpoint.publish("/redisservice");
        return endpoint;
    }

}
