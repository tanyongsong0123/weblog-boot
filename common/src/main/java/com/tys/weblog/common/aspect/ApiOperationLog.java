package com.tys.weblog.common.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//运行时生效
@Target({ElementType.METHOD})//方法上使用
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";
}
