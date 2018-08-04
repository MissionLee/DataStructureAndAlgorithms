package com.lms.basic.annotation;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 11:34 2018/7/13
 **/
@Resource
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface AnnotationTest {
}
