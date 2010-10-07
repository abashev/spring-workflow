package org.springframework.extensions.workflow.instance;

import org.springframework.extensions.workflow.TransitionDefinition;

import java.util.Set;

/**
 * @author janm
 */
public interface FlowInstanceTransitioner {

    void performTransition(String id, Object... arguments);

    Set<String> getTransitions();

    <T extends FlowInstanceDescriptor> Set<String> getTransitions(final TransitionDefinitionFilter<T> filter);

    Set<TransitionDefinition> getTransitionDefinitions();

    <T extends FlowInstanceDescriptor> Set<TransitionDefinition> getTransitionDefinitions(final TransitionDefinitionFilter<T> filter);

}
