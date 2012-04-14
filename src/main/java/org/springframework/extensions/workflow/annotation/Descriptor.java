package org.springframework.extensions.workflow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Argument of a transition method (e.g. method in a state with the
 * {@link org.springframework.extensions.workflow.annotation.Transition} annotaiton)
 * with this annotation will be set to the flow's current payload
 * ({@link org.springframework.extensions.workflow.annotation.Descriptor} subclass).<br/>
 * Typical usage is <code><pre>
 * &#64;Flow(flowInstanceDescriptorClass = X.class)
 * public class Flow {
 * }
 *
 * &#64;State
 * public class State {
 *     public void foo(&#64;Descriptor X x) {
 *         // the value of x is injected automatically upon calling flowInstance.performTransition("foo");
 *     }
 * }
 * </pre></code>
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Descriptor {
}
