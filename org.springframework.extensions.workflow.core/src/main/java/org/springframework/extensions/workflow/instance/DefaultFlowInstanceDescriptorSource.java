package org.springframework.extensions.workflow.instance;

public class DefaultFlowInstanceDescriptorSource implements FlowInstanceDescriptorSource {

    public FlowInstanceDescriptor createDescriptor() {
        return new DefaultFlowInstanceDescriptor();
    }
}