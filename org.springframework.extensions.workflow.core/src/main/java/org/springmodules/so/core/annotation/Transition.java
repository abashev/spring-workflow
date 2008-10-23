package org.springmodules.so.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Transition {
    String id() default "";
    String to() default "";
    String roles() default "";
    String timeout() default "";
    boolean internal() default false;
}
