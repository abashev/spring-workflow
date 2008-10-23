package org.springframework.extensions.workflow.instance;

/**
 * The implementations of this class initialize "empty" {@link FlowInstanceDescriptor}s.<br/>
 * Typically, you will use annonymous implementations of this interface
 * in calls to {@link org.springframework.extensions.workflow.instance.session.FlowSession#start(String, FlowInstanceDescriptorInitializer)}.<br/>
 * You can also the convenience {@link org.springframework.extensions.workflow.instance.ParametrizedFlowInstanceDescriptorInitializer},
 * which offers type parametrization.
 * @author janm
 */
public interface FlowInstanceDescriptorInitializer {

    /**
     * Perform any custom initialization of the <code>flowInstanceDescriptor</code>.
     * @param flowInstanceDescriptor An existing instance of FlowInstanceDescriptor subclass.
     */
    void initialize(FlowInstanceDescriptor flowInstanceDescriptor);

}
