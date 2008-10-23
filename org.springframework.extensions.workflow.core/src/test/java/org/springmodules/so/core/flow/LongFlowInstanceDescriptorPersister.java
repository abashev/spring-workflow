package org.springmodules.so.core.flow;

import org.springmodules.so.core.instance.FlowInstanceDescriptor;
import org.springmodules.so.core.instance.FlowInstanceDescriptorPersister;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author janm
 */
public class LongFlowInstanceDescriptorPersister implements FlowInstanceDescriptorPersister {
    private LongFlowInstanceDescriptor descriptor;

    public LongFlowInstanceDescriptor getDescriptor() {
        return descriptor;
    }

    public void persist(FlowInstanceDescriptor descriptor) {
        this.descriptor = (LongFlowInstanceDescriptor)descriptor;
    }

    public Iterator<FlowInstanceDescriptor> getDescriptorsWithTimeoutsAndDateInPast() {
        List<FlowInstanceDescriptor> list = new ArrayList<FlowInstanceDescriptor>();
        list.add(this.descriptor);
        return list.iterator();
    }
}
