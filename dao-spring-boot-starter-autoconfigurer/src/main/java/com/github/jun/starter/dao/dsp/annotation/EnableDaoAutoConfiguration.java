package com.github.jun.starter.dao.dsp.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: hujw
 * @date: 2019/1/24
 * @description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({DaoAutoConfiguration.class})
public @interface EnableDaoAutoConfiguration {
}
