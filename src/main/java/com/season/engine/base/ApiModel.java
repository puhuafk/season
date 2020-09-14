package com.season.engine.base;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiModel {

    String name() default "";

    String description() default "";
}
