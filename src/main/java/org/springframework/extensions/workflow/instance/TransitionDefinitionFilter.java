package org.springframework.extensions.workflow.instance;

import org.springframework.extensions.workflow.TransitionDefinition;

/**
 * @author janm
 */
public interface TransitionDefinitionFilter<T extends FlowInstanceDescriptor> {

    boolean filter(TransitionDefinition transitionDefinition, T descriptor);

}
