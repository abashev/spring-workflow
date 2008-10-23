package org.springframework.extensions.workflow.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface State {
    String flowId() default "";
    String id() default "";
    boolean start() default false;
    boolean end() default false;
}
