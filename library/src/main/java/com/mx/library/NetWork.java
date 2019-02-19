package com.mx.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//jvm在运行时，通过反射获取注解的值
public @interface NetWork {
    NetType netType() default NetType.AUTO;
}
