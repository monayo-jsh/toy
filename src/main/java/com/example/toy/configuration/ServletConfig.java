package com.example.toy.configuration;

import com.example.toy.configuration.filter.LogFilter;
import com.example.toy.configuration.filter.RequestWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public FilterRegistrationBean<RequestWrapperFilter> requestWrapperFilter() {
        FilterRegistrationBean<RequestWrapperFilter> requestWrapperFilter = new FilterRegistrationBean<>();
        requestWrapperFilter.setFilter(new RequestWrapperFilter());
        requestWrapperFilter.setOrder(Integer.MIN_VALUE);
        requestWrapperFilter.addUrlPatterns("/api/*");
        return requestWrapperFilter;
    }

    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> logFilter = new FilterRegistrationBean<>();
        logFilter.setFilter(new LogFilter());
        logFilter.setOrder(Integer.MIN_VALUE + 1);
        logFilter.addUrlPatterns("/api/*");
        return logFilter;
    }

}
