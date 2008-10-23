package org.springmodules.so.core.instance;

import org.springmodules.so.core.FlowRuntimeException;

/**
 * @author janm
 */
public class FlowInstantiationException extends FlowRuntimeException {
    private static final long serialVersionUID = -6347969648215058216L;

    public FlowInstantiationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FlowInstantiationException(String msg) {
        super(msg);
    }
}
