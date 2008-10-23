package org.springmodules.so.core;

/**
 * @author janm
 */
public class DisallowedTransitionException extends FlowRuntimeException {
    private static final long serialVersionUID = 4989098103158770781L;

    public DisallowedTransitionException(String msg) {
        super(msg);
    }
}
