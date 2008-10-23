package org.springmodules.so.core;

/**
 * @author janm
 */
public class NoStartStateException extends FlowRuntimeException {
    private static final long serialVersionUID = -6374994671991595343L;

    public NoStartStateException(String msg) {
        super(msg);
    }
}
