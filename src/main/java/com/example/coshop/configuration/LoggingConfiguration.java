package com.example.coshop.configuration;

import com.example.coshop.annotation.EnableLogging;
import com.example.coshop.interceptor.KafkaHandlerInterceptor;
import com.example.coshop.interceptor.LoggingHandlerInterceptor;
import com.example.coshop.interceptor.MdcHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* EnableLogging Class Check
*
* */
@ConditionalOnBean(annotation = EnableLogging.class)
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoggingConfiguration implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private KafkaProducerConfig kafkaProducerConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        registry.addInterceptor(new MdcHandlerInterceptor());
        registry.addInterceptor(new KafkaHandlerInterceptor(kafkaProducerConfig));
        registry.addInterceptor(new LoggingHandlerInterceptor());

    }
}
