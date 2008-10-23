package org.springframework.extensions.workflow.flow;

import org.springframework.extensions.workflow.TransitionHolder;
import org.springframework.extensions.workflow.annotation.State;
import org.springframework.extensions.workflow.annotation.Transition;
import org.springframework.extensions.workflow.annotation.ReturnsState;
import static org.springframework.extensions.workflow.context.FlowInstanceDescriptorHolder.getFlowInstanceDescriptor;

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
