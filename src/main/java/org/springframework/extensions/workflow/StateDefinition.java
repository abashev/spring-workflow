package org.springframework.extensions.workflow;

import java.util.Set;
import java.io.Serializable;

/**
 * @author janm
 */
public final class StateDefinition implements Serializable {
    private static final long serialVersionUID = 2036822016716457295L;
    private String id;
    private boolean end;
    private boolean start;
    private Set<TransitionDefinition> transitionDefinitions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<TransitionDefinition> getTransitionDefinitions() {
        return transitionDefinitions;
    }

    public void setTransitionDefinitions(Set<TransitionDefinition> transitionDefinitions) {
        this.transitionDefinitions = transitionDefinitions;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public TransitionDefinition findTransitionDefinition(String id) {
        for (TransitionDefinition transitionDefinition : this.transitionDefinitions) {
            if (id.equals(transitionDefinition.getId())) return transitionDefinition;
        }
        throw new NoSuchTransitionException("TransitionAction with id'" + id + "' does not exist.", id);
    }

    public boolean hasTimeouts() {
        for (TransitionDefinition transitionDefinition : this.transitionDefinitions) {
            if (transitionDefinition.getTimeoutExpression() != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateDefinition)) return false;

        StateDefinition that = (StateDefinition) o;

        return this.id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("StateDefinition");
        sb.append("{id='").append(id).append('\'');
        sb.append(", end=").append(end);
        sb.append(", start=").append(start);
        sb.append(", transitionDefinitions=").append(transitionDefinitions);
        sb.append('}');
        return sb.toString();
    }
}
