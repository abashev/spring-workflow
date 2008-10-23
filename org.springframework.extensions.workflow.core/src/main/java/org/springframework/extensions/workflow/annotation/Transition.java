package org.springframework.extensions.workflow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Any method in a {@link org.springframework.extensions.workflow.annotation.State}-annotated class
 * that is also a Spring bean (and is post-processed by the {@link org.springframework.extensions.workflow.support.FlowDefinitionPostProcessor})
 * will be added to the state's transitions. <br/>
 * You can rely on the default naming strategy (see {@link org.springframework.extensions.workflow.support.naming.DefaultTransitionNamingStrategy})
 * or you can specify the {@link #id()} property. The transition proceeds to the
 * state in set in {@link #to()} property. If this property remains at its default
 * value, the transition causes the flow to remain in the current state.<br/>
 * Set the {@link #roles()} property to force role-based security checking. See
 * {@link org.springframework.extensions.workflow.instance.permissions.evaluator.AntlrRoleExpressionEvaluator} for
 * details of the role syntax. If left at its default value, any caller
 * (any or <code>null</code> roles to be more accurate) can perform the transition.<br/>
 * Set the {@link #timeout()} property to a time expression (see
 * {@link org.springframework.extensions.workflow.SimpleTimeoutTrigger} for details about the
 * timeout expressison syntax). N.B. You also need to call the {@link org.springframework.extensions.workflow.instance.session.FlowSession#processTimeouts()}
 * periodically to allow the timeout transitions to happen.<br/>
 * Finally, you can mark the transition method with {@link #internal()} to hide
 * it from the output of the {@link org.springframework.extensions.workflow.instance.FlowInstance#getTransitions()}.
 * Typically, you'd do this for automatic timeout transitions, as demonstrated in
 * this code snippet<code><pre>
 * public class State {
 *     &#64;Transition(internal = true, timeout = "2d")
 *     public void foo() {
 *         // this transition will not appear in the getTransitions() call
 *         // but it will trigger two days from entering this state. 
 *     }
 * }
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Transition {
    /**
     * Set the identity of the transition.
     * @return The transition id
     */
    String id() default "";

    /**
     * Specify the state name the transition should proceed to if it completes
     * successfully (i.e. with no exceptions). If left at its default value,
     * the transition will remain in this state
     * @return The name of the target state
     */
    String to() default "";

    /**
     * Specify the roles that can execut this transition.
     * @return The role expression
     */
    String roles() default "";

    /**
     * Specify the timeout expression after which this transition will automatically
     * execute
     * @return The timeout expression
     */
    String timeout() default "";

    /**
     * Set to <code>true</code> to "hide" the transition from being returned
     * by the {@link org.springframework.extensions.workflow.instance.FlowInstance#getTransitions()}.
     * Typically, you'll combine this property with {@link #timeout()}  
     * @return True iff the transition is to be hidden.
     */
    boolean internal() default false;
}
