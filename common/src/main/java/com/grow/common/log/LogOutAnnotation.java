package com.grow.common.log;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogOutAnnotation {
    String url() default "";

    boolean request() default true;
}
