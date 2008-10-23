package org.springmodules.so.core;

import java.io.Serializable;

/**
 * @author janm
 */
public final class TransitionDefinition implements Serializable {
    private static final long serialVersionUID = 9015525050536415089L;
    private String id;
    private String targetStateId;
    private TransitionAction transitionAction;
    private String roles;
    private String timeoutExpression;
    private boolean internal;

    public TransitionDefinition(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetStateId() {
        return targetStateId;
    }

    public void setTargetStateId(String targetStateId) {
        this.targetStateId = targetStateId;
    }

    public TransitionAction getTransitionAction() {
        return transitionAction;
    }

    public void setTransitionAction(TransitionAction transitionAction) {
        this.transitionAction = transitionAction;
    }

    public String getTimeoutExpression() {
        return timeoutExpression;
    }

    public void setTimeoutExpression(String timeoutExpression) {
        this.timeoutExpression = timeoutExpression;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TransitionDefinition");
        sb.append("{id='").append(id).append('\'');
        sb.append(", targetStateId='").append(targetStateId).append('\'');
        sb.append(", transitionAction=").append(transitionAction);
        sb.append('}');
        return sb.toString();
    }
}
