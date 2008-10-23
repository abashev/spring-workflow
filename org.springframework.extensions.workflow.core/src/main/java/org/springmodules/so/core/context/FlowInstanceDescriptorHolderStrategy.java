package org.springmodules.so.core.context;

import org.springmodules.so.core.instance.FlowInstanceDescriptor;

import java.io.Serializable;

/**
 * @author janm
 */
public interface FlowInstanceDescriptorHolderStrategy extends Serializable{

    FlowInstanceDescriptor getDescriptor();

    void setDescriptor(FlowInstanceDescriptor descriptor);

}
