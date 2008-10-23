package org.springmodules.so.core.instance.permissions;

import org.springmodules.so.core.FlowRuntimeException;

/**
 * @author janm
 */
public class CannotExtractRolesException extends FlowRuntimeException {
    private static final long serialVersionUID = 3299542710090360787L;

    public CannotExtractRolesException(String msg) {
        super(msg);
    }
}
