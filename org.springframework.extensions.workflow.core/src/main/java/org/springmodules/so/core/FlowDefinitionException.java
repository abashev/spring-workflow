package org.springmodules.so.core;

import org.springframework.core.NestedRuntimeException;

/**
 * @author janm
 */
public abstract class FlowDefinitionException extends NestedRuntimeException {
    private static final long serialVersionUID = -6524140068240463434L;
    private StateDefinition stateDefinition;

    protected FlowDefinitionException(String msg) {
        super(msg);
    }

    public FlowDefinitionException(String msg, StateDefinition stateDefinition) {
        super(msg);
        this.stateDefinition = stateDefinition;
    }

    public StateDefinition getStateDefinition() {
        return this.stateDefinition;
    }
}
