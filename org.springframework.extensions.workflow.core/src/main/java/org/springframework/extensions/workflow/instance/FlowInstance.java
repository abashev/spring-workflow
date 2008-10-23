package org.springframework.extensions.workflow.instance;

/**
 * @author janm
 */
public interface FlowInstance extends FlowInstanceDescriptor, FlowInstanceTransitioner {

    FlowInstanceDescriptor getFlowInstanceDescriptor();    
}
