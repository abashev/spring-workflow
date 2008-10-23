package org.springmodules.so.core.annotation;

import org.springmodules.so.core.instance.FlowInstanceDescriptor;
import org.springmodules.so.core.instance.DefaultFlowInstanceDescriptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Flow {
    String id() default "";
    Class<? extends FlowInstanceDescriptor> flowInstanceDescriptorClass() default DefaultFlowInstanceDescriptor.class;
}
