package com.example.coshop.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

import static com.example.coshop.constants.HeaderConstants.*;

@Component
public class RequestLogger {



    private String getClientIP(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader(KEY_REQUEST_CLIENT_IP)).orElse(request.getRemoteAddr());
    }

    public Object aroundRequestMapping(HttpServletRequest request, ProceedingJoinPoint joinPoint) throws Throwable {
        request.setAttribute(KEY_REQUEST_LOGGER_DO_WRITE_LOG, true);
        request.setAttribute(KEY_REQUEST_LOGGER_CLIENT_IP, getClientIP(request));

        Long startMilli = Instant.now().toEpochMilli();
        Object result = joinPoint.proceed();
        Long endMilli = Instant.now().toEpochMilli();
        request.setAttribute(KEY_REQUEST_LOGGER_ELAPSED_MILLI,startMilli-endMilli);

        return result;

    }
}
