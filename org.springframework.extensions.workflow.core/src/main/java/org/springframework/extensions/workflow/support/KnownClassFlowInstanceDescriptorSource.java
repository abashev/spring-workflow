package org.springframework.extensions.workflow.support;

import org.springframework.extensions.workflow.instance.FlowInstanceDescriptorSource;
import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;

/**
 * @author janm
 */
class KnownClassFlowInstanceDescriptorSource implements FlowInstanceDescriptorSource {
    private Class<? extends FlowInstanceDescriptor> clazz;

    public KnownClassFlowInstanceDescriptorSource(Class<? extends FlowInstanceDescriptor> clazz) {
        this.clazz = clazz;
    }

    public FlowInstanceDescriptor createDescriptor() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
