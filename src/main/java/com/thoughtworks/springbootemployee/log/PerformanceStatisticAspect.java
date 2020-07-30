package com.thoughtworks.springbootemployee.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceStatisticAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceStatisticAspect.class);

    @Around("execution(* com.thoughtworks.springbootemployee.controller.*.*(..))")
    public Object logAPIPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        String method = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object object = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String log = String.format("method<%s.%s> cost time: %d", className, method, endTime - beginTime);
        LOGGER.warn(log);
        return object;
    }

}
