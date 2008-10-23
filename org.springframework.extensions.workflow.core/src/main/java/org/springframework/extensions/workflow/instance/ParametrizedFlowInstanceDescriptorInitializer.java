package org.springframework.extensions.workflow.instance;

/**
 * @author janm
 */
public abstract class ParametrizedFlowInstanceDescriptorInitializer<T extends FlowInstanceDescriptor>
    implements FlowInstanceDescriptorInitializer {

    protected abstract void doInitialize(T flowInstanceDescriptor);

    @SuppressWarnings({"unchecked"})
    public final void initialize(FlowInstanceDescriptor flowInstanceDescriptor) {
        doInitialize((T) flowInstanceDescriptor);
    }
}
