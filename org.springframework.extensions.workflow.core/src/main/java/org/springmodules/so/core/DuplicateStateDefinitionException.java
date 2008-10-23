package org.springmodules.so.core;

/**
 * @author janm
 */
public class DuplicateStateDefinitionException extends FlowDefinitionException {
    private static final long serialVersionUID = -7396575675191564892L;
    private StateDefinition stateDefinition;

    protected DuplicateStateDefinitionException(String msg, StateDefinition stateDefinition) {
        super(msg);
        this.stateDefinition = stateDefinition;
    }

    public StateDefinition getStateDefinition() {
        return stateDefinition;
    }
}
