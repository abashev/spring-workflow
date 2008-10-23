package org.springframework.extensions.workflow;

/**
 * @author janm
 */
public class TransitionHolder {
    private String lastTransitionId;
    private Object[] lastTransitionArguments = new Object[0];

    public String getLastTransitionId() {
        return lastTransitionId;
    }

    public void setLastTransitionId(String lastTransitionId) {
        this.lastTransitionId = lastTransitionId;
    }

    public Object[] getLastTransitionArguments() {
        return lastTransitionArguments;
    }

    public void setLastTransitionArguments(Object... lastTransitionArguments) {
        this.lastTransitionArguments = lastTransitionArguments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.lastTransitionId).append("(");
        for (int i = 0; i < this.lastTransitionArguments.length - 1; i++) {
            if (i > 0) sb.append(", ");
            sb.append(this.lastTransitionArguments[i]);
        }
        sb.append(")");
        return sb.toString();
    }
}
