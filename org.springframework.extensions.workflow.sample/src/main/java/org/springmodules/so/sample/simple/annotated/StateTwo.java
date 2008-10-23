package org.springmodules.so.sample.simple.annotated;

import org.springmodules.so.core.annotation.State;
import org.springmodules.so.core.annotation.Transition;

/**
 * @author janm
 */
@State(flowId = "main", id = "end", end = true)
public class StateTwo {

    @Transition(id = "1", to = "one")
    public void back() {

    }

    @Transition(id = "2", to = "end")
    public void x() {

    }

}