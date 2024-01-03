package com.example.coshop.interceptor;

import com.example.coshop.dto.log.LogDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static com.example.coshop.constants.HeaderConstant.*;

public class LoggingHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(KEY_REQUEST_LOGGER_REQUEST_INCOMING_DATETIME, LocalDateTime.now().toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private String byteArraytoString(byte[] buf) {
        if (buf.length > 0) {
            return new String(buf, 0, buf.length, StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        /**
         * Annotation Check
         * @ExcludeLogging
         * @InCludeLogging
         * */
        if (request.getAttribute(KEY_REQUEST_LOGGER_DO_WRITE_LOG) == null){
            return;
        }

        ContentCachingRequestWrapper cachingRequestWrapper = (ContentCachingRequestWrapper) request;
        ContentCachingResponseWrapper cachingResponseWrapper = (ContentCachingResponseWrapper) response;

        LogDto logMessage = LogDto.builder()
                .traceId(response.getHeader(KEY_RESPONSE_HEADER_TRACE_ID))
                .clientIp(request.getAttribute(KEY_REQUEST_LOGGER_CLIENT_IP).toString())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .statusCode(String.valueOf(response.getStatus()))
                .time((String) request.getAttribute(KEY_REQUEST_LOGGER_REQUEST_INCOMING_DATETIME))
                .elapsedTimeMillis((Long) request.getAttribute(KEY_REQUEST_LOGGER_ELAPSED_MILLI))
                .requestBody(byteArraytoString(cachingRequestWrapper.getContentAsByteArray()))
                .response(byteArraytoString(cachingResponseWrapper.getContentAsByteArray()))
                .build();

        }

    }

