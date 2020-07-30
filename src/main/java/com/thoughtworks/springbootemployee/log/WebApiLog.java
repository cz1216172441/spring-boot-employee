package com.thoughtworks.springbootemployee.log;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebApiLog {

    String name();

}
