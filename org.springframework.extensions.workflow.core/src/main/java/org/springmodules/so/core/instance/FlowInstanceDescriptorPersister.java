package org.springmodules.so.core.instance;

import java.util.Iterator;

/**
 * @author janm
 */
public interface FlowInstanceDescriptorPersister {

    void persist(FlowInstanceDescriptor descriptor);

    Iterator<? extends FlowInstanceDescriptor> getDescriptorsWithTimeoutsAndDateInPast();

}
