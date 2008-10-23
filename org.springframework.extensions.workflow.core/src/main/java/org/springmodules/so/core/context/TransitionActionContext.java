package org.springmodules.so.core.context;

import java.io.Serializable;

/**
 * @author janm
 */
public final class TransitionActionContext implements Serializable{
    private static final long serialVersionUID = -2438974871913081078L;
    private String targetStateId;
    private Object[] arguments;

    public TransitionActionContext(String targetStateId, Object[] arguments) {
        this.targetStateId = targetStateId;
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public String getTargetStateId() {
        return targetStateId;
    }

    public void setTargetStateId(String targetStateId) {
        this.targetStateId = targetStateId;
    }
}
