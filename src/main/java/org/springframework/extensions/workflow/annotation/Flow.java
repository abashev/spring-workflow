package org.springframework.extensions.workflow.annotation;

import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;
import org.springframework.extensions.workflow.instance.DefaultFlowInstanceDescriptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * This annotation is used to identify classes that represent a flow. Together
 * with the {@link org.springframework.extensions.workflow.support.FlowDefinitionPostProcessor},
 * the beans with this annotations are turned into appropriate {@link org.springframework.extensions.workflow.FlowDefinition}s.
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Flow {
    /**
     * Set the flow's identity. If empty, the {@link org.springframework.extensions.workflow.support.naming.DefaultStateAndFlowNamingStrategy}
     * used by the <code>FlowDefinitionPostProcessor</code> will extract a name
     * from the class with this annotation.
     * @return The flow identity
     */
    String id() default "";

    /**
     * Set the FlowInstanceDescriptor implementation that represents they payload
     * of the flow.
     * @return The FlowInstanceDescriptor implementation
     */
    Class<? extends FlowInstanceDescriptor> flowInstanceDescriptorClass() default DefaultFlowInstanceDescriptor.class;
}
