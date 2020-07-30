package com.thoughtworks.springbootemployee.log;

import org.aspectj.lang.JoinPoint;
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
        Date now = new Date();
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : args) {
            stringBuilder.append(object.toString());
        }
        System.out.printf("%s %s api have been invoked and take params ( %s )%n",
                now.toString(), webApiLog.name(), stringBuilder.toString());
    }

}
