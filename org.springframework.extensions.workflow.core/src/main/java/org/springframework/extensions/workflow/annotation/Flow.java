package org.springframework.extensions.workflow.annotation;

import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;
import org.springframework.extensions.workflow.instance.DefaultFlowInstanceDescriptor;

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
