package org.springframework.extensions.workflow.instance.session;

import org.springframework.extensions.workflow.instance.FlowInstance;
import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;
import org.springframework.extensions.workflow.instance.FlowInstanceDescriptorCreator;
import org.springframework.extensions.workflow.instance.FlowInstanceDescriptorInitializer;

/**
 * This interface provides methods to interact with the {@link FlowInstance}s.
 * Typically, the FlowSession instances will be Spring beans and you will
 * call its methods to obtain the <code>FlowInstance</code>s.
 * @author janm
 */
public interface FlowSession {

    /**
     * Starts a flow with the given <code>id</code>. The implementation will find
     * a Spring-registred {@link org.springframework.extensions.workflow.FlowDefinition} whose
     * <code>id</code> matches the argument. If no such flow is found,
     * the method will throw {@link org.springframework.extensions.workflow.NoSuchFlowDefinitionException}.
     * This method will not initialize the {@link org.springframework.extensions.workflow.instance.FlowInstanceDescriptor}
     * attached to the <code>FlowDefinition</code>, it will only call its default constructor. To create the descriptor
     * yourself, call {@link #start(String, org.springframework.extensions.workflow.instance.FlowInstanceDescriptorCreator)};
     * to perform custom initialization on an instance of the <code>FlowInstanceDescriptor</code>,
     * call {@link #start(String, org.springframework.extensions.workflow.instance.FlowInstanceDescriptorInitializer)}
     * @param id The identity of the flow, never <code>null</code>
     * @return The FlowInstance at the starting state
     */
    FlowInstance start(String id);

    /**
     * Starts a flow with the given <code>id</code>. The implementation will find
     * a Spring-registred {@link org.springframework.extensions.workflow.FlowDefinition} whose
     * <code>id</code> matches the argument. If no such flow is found,
     * the method will throw {@link org.springframework.extensions.workflow.NoSuchFlowDefinitionException}.
     * The method will call <code>instanceDescriptorCreator.create()</code> to obtain an
     * instance of the <code>FlowInstanceDescriptor</code> for this flow instance.
     * @param id The identity of the flow, never <code>null</code>
     * @param instanceDescriptorCreator The implementaiton of the FlowInstanceDescriptorCreator, never <code>null</code>
     * @return The FlowInstance at the starting state
     */
    FlowInstance start(String id, FlowInstanceDescriptorCreator instanceDescriptorCreator);

    /**
     * Starts a flow with the given <code>id</code>. The implementation will find
     * a Spring-registred {@link org.springframework.extensions.workflow.FlowDefinition} whose
     * <code>id</code> matches the argument. If no such flow is found,
     * the method will throw {@link org.springframework.extensions.workflow.NoSuchFlowDefinitionException}.
     * The method will call <code>instanceDescriptorInitialize.initialize(FlowInstanceDescriptor)</code> to
     * perform additional initialization of the <code>FlowInstanceDescriptor</code> subclass
     * associated with the <code>FlowDefinition</code>.
     * @param id The identity of the flow, never <code>null</code>
     * @param instanceDescriptorInitializer The implementation of the FlowInstanceDescriptorInitializer, never <code>null</code>
     * @return The FlowInstance at the starting state
     */
    FlowInstance start(String id, FlowInstanceDescriptorInitializer instanceDescriptorInitializer);

    /**
     * Returns a {@link FlowInstance} for the given {@link org.springframework.extensions.workflow.instance.FlowInstanceDescriptor}.
     * This allows you to "step into" the flow -- the <code>FlowInstanceDescriptor</code> carries, amongst other properties,
     * the identity of the current state.<br/>
     * Typically, you will use this method to get the <code>FlowInstance</code> for
     * the <code>FlowInstanceDescriptor</code> you obtained from a database lookup, for instance.
     * @param descriptor The FlowInstanceDescriptor instance, never <code>null</code>
     * @return The FlowInstance at the appropriate state
     */
    FlowInstance find(FlowInstanceDescriptor descriptor);

    /**
     * Process transitions with automatic time-outs. This method is intended
     * to be called from a scheduler such as Quartz.</br>
     * In a typical Spring environment, you can configure a trigger that
     * invokes this method on this "bean".
     * @see org.springframework.extensions.workflow.annotation.Transition#timeout()
     */
    void processTimeouts();
}
