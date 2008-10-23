package org.springmodules.so.core;

/**
 * @author janm
 */
public class MultipleStartOrEndStateException extends FlowDefinitionException {
    private static final long serialVersionUID = -3147593970217079478L;

    public MultipleStartOrEndStateException(String msg, StateDefinition stateDefinition) {
        super(msg, stateDefinition);
    }
}
