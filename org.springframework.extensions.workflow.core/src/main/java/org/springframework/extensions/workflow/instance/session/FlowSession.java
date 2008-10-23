package org.springframework.extensions.workflow.instance.session;

import org.springframework.extensions.workflow.instance.FlowInstance;
import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;
import org.springframework.extensions.workflow.instance.FlowInstanceDescriptorCreator;
import org.springframework.extensions.workflow.instance.FlowInstanceDescriptorInitializer;

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
