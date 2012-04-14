package org.springframework.extensions.workflow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add parameter annotation to mark argument as property of FlowInstanceDescriptor.
 *
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface DescriptorProperty {
    String value() default "";
}
