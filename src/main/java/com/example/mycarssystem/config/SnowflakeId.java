package com.example.mycarssystem.config;

import org.hibernate.annotations.IdGeneratorType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(SnowflakeIdGenerator.class) // 指向生成器逻辑
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface SnowflakeId {
}
