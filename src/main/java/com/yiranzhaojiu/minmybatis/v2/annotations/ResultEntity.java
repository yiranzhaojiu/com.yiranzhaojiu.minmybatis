package com.yiranzhaojiu.minmybatis.v2.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ResultEntity {
    Class value();
}
