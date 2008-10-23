package org.springmodules.so.core;

import org.springframework.core.NestedRuntimeException;

/**
 * @author janm
 */
public abstract class FlowRuntimeException extends NestedRuntimeException {
    private static final long serialVersionUID = 2178491881246742051L;

    protected FlowRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    protected FlowRuntimeException(String msg) {
        super(msg);
    }
}
