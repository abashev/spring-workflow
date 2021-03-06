package org.springframework.extensions.workflow.flow;

import org.springframework.extensions.workflow.TransitionHolder;
import org.springframework.extensions.workflow.annotation.Descriptor;
import org.springframework.extensions.workflow.annotation.DescriptorProperty;
import org.springframework.extensions.workflow.annotation.State;
import org.springframework.extensions.workflow.annotation.Transition;
import static org.springframework.extensions.workflow.context.FlowInstanceDescriptorHolder.getFlowInstanceDescriptor;

/**
 * @author janm
 */
@State(start = true)
public class StartState {
    private TransitionHolder lastTransition;

    @Transition(roles = "publisher && author")
    public void one(@Descriptor LongFlowInstanceDescriptor x, long y) {
        x.setSomeValue(y);
        this.lastTransition.setLastTransitionId("StartState.one");
    }

    @Transition(to = "end", timeout = "2s")
    public void end(@DescriptorProperty Long someValue) {
        LongFlowInstanceDescriptor d = getFlowInstanceDescriptor();
        d.setSomeValue(2L);
        this.lastTransition.setLastTransitionId("StartState.end");
    }

    public void setLastTransition(TransitionHolder lastTransition) {
        this.lastTransition = lastTransition;
    }
}
