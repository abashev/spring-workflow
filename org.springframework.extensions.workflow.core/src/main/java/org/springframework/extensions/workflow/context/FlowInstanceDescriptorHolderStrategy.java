package org.springframework.extensions.workflow.context;

import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;

import java.io.Serializable;

/**
 * @author janm
 */
public interface FlowInstanceDescriptorHolderStrategy extends Serializable{

    FlowInstanceDescriptor getDescriptor();

    void setDescriptor(FlowInstanceDescriptor descriptor);

}
