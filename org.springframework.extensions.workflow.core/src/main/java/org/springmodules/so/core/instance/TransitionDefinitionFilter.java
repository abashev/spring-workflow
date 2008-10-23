package org.springmodules.so.core.instance;

import org.springmodules.so.core.TransitionDefinition;

/**
 * @author janm
 */
public interface TransitionDefinitionFilter<T extends FlowInstanceDescriptor> {

    boolean filter(TransitionDefinition transitionDefinition, T descriptor);

}
