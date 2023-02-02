package com.example.toy.utils;

import com.example.toy.restfull.controllers.exception.CustomException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.stream.Collectors;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.json.simple.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.util.ObjectUtils;

@Slf4j
public class ServiceLogger {

    private final HttpServletRequest request;

    private String appType;
    private String appName;
    private String clientIp;

    private long reqTime;
    private long resTime;

    private String resultCode;
    private String resultMessage;

    private StructuredArgument requestLog;
    private StructuredArgument responseLog;

    @Builder
    public ServiceLogger(ServletRequest request, String appType, String appName) {
        this.request = (HttpServletRequest) request;
        this.appType = appType;
        this.appName = appName;
        this.clientIp = this.request.getRemoteAddr();

        this.reqTime = System.currentTimeMillis();
    }

    public void writeLog() {
        this.resTime = System.currentTimeMillis();
        this.requestLog = makeRequestLog();
        this.responseLog = makeResponseLog();

        log.info("success", this.requestLog, this.responseLog);
    }

    private StructuredArgument makeRequestLog() {
        JSONObject requestLog = new JSONObject();
        requestLog.put("header", makeRequestHeaderJSON());
        requestLog.put("body", makeRequestBodyJSON());
        requestLog.put("time", new Timestamp(this.reqTime).toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        return StructuredArguments.keyValue("request", requestLog);
    }

    private JSONObject makeRequestBodyJSON() {
        if (ObjectUtils.isEmpty(this.request)) return null;

        String method = this.request.getMethod().toUpperCase();

        if(HttpMethod.POST.name().equals(method)) {
            try {
                return CommonUtil.objectMapper.readValue(this.request.getReader().lines().collect(Collectors.joining()), JSONObject.class);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }

            return null;
        }

        JSONObject jsonParameter = new JSONObject();
        for(Enumeration<String> parameterNames = this.request.getParameterNames(); parameterNames.hasMoreElements();)
        {
            String name = parameterNames.nextElement();
            jsonParameter.put(name, this.request.getParameter(name));
        }
        return jsonParameter;
    }

    private JSONObject makeRequestHeaderJSON() {
        if(ObjectUtils.isEmpty(this.request)) return null;

        JSONObject header = new JSONObject();
        for(Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements();) {
            String key = headerNames.nextElement();
            header.put(key, request.getHeader(key));
        }
        return header;
    }

    private StructuredArgument makeResponseLog() {
        return StructuredArguments.keyValue("response", makeResponseJSON());
    }

    private Object makeResponseJSON() {
        JSONObject response = new JSONObject();
        response.put("code", this.resultCode);
        response.put("message", this.resultMessage);
        response.put("time", new Timestamp(this.reqTime).toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        response.put("elapsed", String.format("%s ms", this.resTime - this.reqTime));
        return response;
    }

    public void setResult(CustomException ex) {
        this.resultCode = ex.getCode();
        this.resultMessage = ex.getMessage();
    }
}
