package org.springframework.extensions.workflow.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * The workflow engine will take the value returned from a transition method (method in a state)
 * with this annotation as the name of the state to transition to.<br/>
 * The method may return any type, as long as its <code>toString</code> method
 * represents the state to be reached. Method with this annotation may return
 * <code>null</code> to indicate that the flow should remain in the current state.<br/>
 * This gives us conditional transitions with arbitrary complexity. Consider this code
 * <code><pre>
 * public class SomeObject {
 *     private final String s;
 *     public SomeObject(String s) {
 *         this.s = s;
 *     }
 *
 *     &#64;Override
 *     public String toString() {
 *         return s;
 *     }
 * }
 *
 * public class State {
 *     &#64;Transition
 *     &#64;ReturnsState
 *     public SomeObject foo(int x) {
 *        if (x % 3 == 0) return new SomeObject("a");
 *        return new SomeObject("b");
 *     }
 * }
 *
 * flowInstance.performTransition("foo", 3); // this will transition to "a"
 * flowInstance.performTransition("foo", 1); // this will transition to "b"
 * @author janm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReturnsState {
}
