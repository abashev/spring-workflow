package org.springmodules.so.core.support;

import org.springmodules.so.core.instance.FlowInstanceDescriptorSource;
import org.springmodules.so.core.instance.FlowInstanceDescriptor;

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
