package org.springmodules.so.core.instance;

public class DefaultFlowInstanceDescriptorSource implements FlowInstanceDescriptorSource {

    public FlowInstanceDescriptor createDescriptor() {
        return new DefaultFlowInstanceDescriptor();
    }
}