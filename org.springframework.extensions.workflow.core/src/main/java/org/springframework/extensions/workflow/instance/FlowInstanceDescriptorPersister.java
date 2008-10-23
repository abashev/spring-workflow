package org.springframework.extensions.workflow.instance;

import java.util.Iterator;

/**
 * @author janm
 */
public interface FlowInstanceDescriptorPersister {

    void persist(FlowInstanceDescriptor descriptor);

    Iterator<? extends FlowInstanceDescriptor> getDescriptorsWithTimeoutsAndDateInPast();

}
