package org.springframework.extensions.workflow.instance;

/**
 * The implementations of this interface return initialized instances
 * of the {@link FlowInstanceDescriptor} subclasses.<br/>
 * Typically, you will use annonymous implementations of this interface in
 * calls to {@link org.springframework.extensions.workflow.instance.session.FlowSession#start(String, FlowInstanceDescriptorCreator)}.
 * @author janm
 */
public interface FlowInstanceDescriptorCreator {

    /**
     * Returns a fully constructed and initialized instance of the {@link org.springframework.extensions.workflow.instance.FlowInstanceDescriptor}
     * @return The FlowInstanceDescriptor instance
     */
    FlowInstanceDescriptor create();

}
