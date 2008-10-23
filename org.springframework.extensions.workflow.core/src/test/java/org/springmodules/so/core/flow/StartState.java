package org.springmodules.so.core.flow;

import org.springmodules.so.core.TransitionHolder;
import org.springmodules.so.core.annotation.FlowInstanceDescriptorArgument;
import org.springmodules.so.core.annotation.State;
import org.springmodules.so.core.annotation.Transition;
import static org.springmodules.so.core.context.FlowInstanceDescriptorHolder.getFlowInstanceDescriptor;

/**
 * @author janm
 */
@State(start = true)
public class StartState {
    private TransitionHolder lastTransition;

    @Transition(roles = "publisher && author")
    public void one(@FlowInstanceDescriptorArgument LongFlowInstanceDescriptor x, long y) {
        x.setSomeValue(y);
        this.lastTransition.setLastTransitionId("StartState.one");
    }

    @Transition(to = "end", timeout = "2s")
    public void end() {
        LongFlowInstanceDescriptor d = getFlowInstanceDescriptor();
        d.setSomeValue(2L);
        this.lastTransition.setLastTransitionId("StartState.end");
    }

    public void setLastTransition(TransitionHolder lastTransition) {
        this.lastTransition = lastTransition;
    }
}
