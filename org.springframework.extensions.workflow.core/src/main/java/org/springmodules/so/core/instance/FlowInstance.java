package org.springmodules.so.core.instance;

/**
 * @author janm
 */
public interface FlowInstance extends FlowInstanceDescriptor, FlowInstanceTransitioner {

    FlowInstanceDescriptor getFlowInstanceDescriptor();    
}
