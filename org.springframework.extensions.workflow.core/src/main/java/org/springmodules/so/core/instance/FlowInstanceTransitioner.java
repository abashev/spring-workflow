package org.springmodules.so.core.instance;

import org.springmodules.so.core.TransitionDefinition;

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
