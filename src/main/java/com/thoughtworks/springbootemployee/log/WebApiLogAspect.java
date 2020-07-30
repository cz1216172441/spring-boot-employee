package com.thoughtworks.springbootemployee.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class WebApiLogAspect {

    @Pointcut("execution(* com.thoughtworks.springbootemployee.controller.*.*(..))")
    public void webLog() {}

    @Before(value = "webLog() && @annotation(webApiLog)")
    public void doBefore(JoinPoint joinPoint, WebApiLog webApiLog) {
        log(joinPoint, webApiLog, "%s %s api have been invoked and take params ( %s )%n");
    }

    @AfterThrowing(value = "webLog() && @annotation(webApiLog)", throwing = "e")
    public void doThrow(JoinPoint joinPoint, WebApiLog webApiLog, Exception e) {
        log(joinPoint, webApiLog, "%s %s api have thrown exception and take params ( %s )%n");
        e.printStackTrace();
    }

    private void log(JoinPoint joinPoint, WebApiLog webApiLog, String template) {
        Date now = new Date();
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : args) {
            stringBuilder.append(object.toString());
        }
        System.out.printf(template, now.toString(), webApiLog.name(), stringBuilder.toString());
    }


}
