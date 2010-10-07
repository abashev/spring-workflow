package org.springframework.extensions.workflow.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * This annotation identifies a state. The {@link org.springframework.extensions.workflow.support.FlowDefinitionPostProcessor}
 * will detect all Spring beans with this annotation and prepare the appropriate
 * {@link org.springframework.extensions.workflow.StateDefinition} beans.<br/>
 * Set the {@link #start()} and {@link #end()} property to <code>true</code> if the state
 * is a starting or ending state, respectively. Set the {@link #id()} to the identity of
 * the state or take advantage of the automatic naming implemented in the
 * {@link org.springframework.extensions.workflow.support.naming.DefaultStateAndFlowNamingStrategy}.<br/> 
 * If you have more than 1 flow, you must set the {@link #flowId()} property to the
 * flow's identity; with exactly 1 flow, you may leave it at its default value.
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface State {
    /**
     * The identity of a flow this state belongs to
     * @return The flow identity
     */
    String flowId() default "";

    /**
     * The identity of this state; if not set, the id will be assigned automatically
     * @return The state identity
     */
    String id() default "";

    /**
     * Indicates if this state is a start state. Note that you cannot have more than
     * one start state in a flow.
     * @return True iff this state is the start state
     */
    boolean start() default false;

    /**
     * Indicates if this state is an end state. Again, you cannot have more than one
     * end state in a flow.
     * @return True iff this state is the end state
     */
    boolean end() default false;
}
