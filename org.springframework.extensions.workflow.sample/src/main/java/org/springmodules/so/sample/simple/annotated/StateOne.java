package org.springmodules.so.sample.simple.annotated;

import org.springmodules.so.core.annotation.State;
import org.springmodules.so.core.annotation.Transition;

/**
 * @author janm
 */
@State(flowId = "main", id = "one", start = true)
public class StateOne {

    @Transition(id = "2", to = "end")
    public void two() {

    }

    @Transition(id = "1")
    public void one() {

    }

}
