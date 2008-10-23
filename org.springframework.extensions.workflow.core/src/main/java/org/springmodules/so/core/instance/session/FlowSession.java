package org.springmodules.so.core.instance.session;

import org.springmodules.so.core.instance.FlowInstance;
import org.springmodules.so.core.instance.FlowInstanceDescriptor;
import org.springmodules.so.core.instance.FlowInstanceDescriptorCreator;
import org.springmodules.so.core.instance.FlowInstanceDescriptorInitializer;

/**
 * @author janm
 */
public interface FlowSession {

    FlowInstance start(String id);

    FlowInstance start(String id, FlowInstanceDescriptorCreator instanceDescriptorCreator);

    FlowInstance start(String id, FlowInstanceDescriptorInitializer instanceDescriptorInitializer);

    FlowInstance find(FlowInstanceDescriptor descriptor);

    void processTimeouts();
}
