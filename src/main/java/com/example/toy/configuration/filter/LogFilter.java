package com.example.toy.configuration.filter;

import com.example.toy.utils.Consts;
import com.example.toy.utils.ServiceLogger;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServiceLogger serviceLogger = ServiceLogger.builder().request(request).appType("toy").appName("project").build();
        request.setAttribute(Consts.REQUEST_ATTRIBUTE_SERVICE_LOGGER, serviceLogger);

        chain.doFilter(request, response);

        serviceLogger.writeLog();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
