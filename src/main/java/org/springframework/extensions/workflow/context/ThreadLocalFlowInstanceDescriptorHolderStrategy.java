package org.springframework.extensions.workflow.context;

import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;

/**
 * @author janm
 */
public class ThreadLocalFlowInstanceDescriptorHolderStrategy implements FlowInstanceDescriptorHolderStrategy {
    private static final long serialVersionUID = 5846317781576476233L;
    private ThreadLocal<FlowInstanceDescriptor> descriptorThreadLocal;

    public ThreadLocalFlowInstanceDescriptorHolderStrategy() {
        this.descriptorThreadLocal = new ThreadLocal<FlowInstanceDescriptor>();
    }

    public FlowInstanceDescriptor getDescriptor() {
        return this.descriptorThreadLocal.get();
    }

    public void setDescriptor(FlowInstanceDescriptor descriptor) {
        this.descriptorThreadLocal.set(descriptor);
    }
}
