package org.springframework.extensions.workflow.instance.permissions;

import org.springframework.extensions.workflow.FlowRuntimeException;

/**
 * @author janm
 */
public class CannotExtractRolesException extends FlowRuntimeException {
    private static final long serialVersionUID = 3299542710090360787L;

    public CannotExtractRolesException(String msg) {
        super(msg);
    }
}
