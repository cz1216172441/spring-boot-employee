package com.thoughtworks.springbootemployee.auth;

import com.thoughtworks.springbootemployee.exception.AuthFailedException;
import com.thoughtworks.springbootemployee.service.AuthService;
import com.thoughtworks.springbootemployee.util.RequestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAspect {

    private final AuthService authService;

    @Autowired
    public AuthAspect(AuthService authService) {
        this.authService = authService;
    }

    @Around("execution(* com.thoughtworks.springbootemployee.controller.*.*(..))")
    public Object authValidate(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("token validating...");
        String token = RequestUtils.getToken();
        if (authService.authValidate(token)) {
            System.out.println("auth success!!!");
            return joinPoint.proceed();
        } else {
            System.out.println("error token!!!");
            throw new AuthFailedException();
        }
    }

}
