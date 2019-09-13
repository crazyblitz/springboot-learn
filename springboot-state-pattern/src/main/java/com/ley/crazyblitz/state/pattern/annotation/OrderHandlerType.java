package com.ley.crazyblitz.state.pattern.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderHandlerType {

    String value() default "";
}
