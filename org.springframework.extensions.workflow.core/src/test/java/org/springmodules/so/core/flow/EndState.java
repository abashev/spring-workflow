package org.springmodules.so.core.flow;

import org.springmodules.so.core.TransitionHolder;
import org.springmodules.so.core.annotation.State;
import org.springmodules.so.core.annotation.Transition;
import org.springmodules.so.core.annotation.ReturnsState;
import static org.springmodules.so.core.context.FlowInstanceDescriptorHolder.getFlowInstanceDescriptor;

/**
 * @author janm
 */
@State(end = true)
public class EndState {
    private TransitionHolder lastTransition;

    @Transition
    @ReturnsState
    public String one(String to) {
        LongFlowInstanceDescriptor d = getFlowInstanceDescriptor();
        d.setSomeValue(3L);
        
        this.lastTransition.setLastTransitionId("EndState.end");
        return to;
    }

    @Transition(to = "start")
    public void restart(int i) {
        this.lastTransition.setLastTransitionId("EndState.restart");
        this.lastTransition.setLastTransitionArguments(i);
        LongFlowInstanceDescriptor d = getFlowInstanceDescriptor();
        d.setSomeValue(4L);
    }

    public void setLastTransition(TransitionHolder lastTransition) {
        this.lastTransition = lastTransition;
    }
}
