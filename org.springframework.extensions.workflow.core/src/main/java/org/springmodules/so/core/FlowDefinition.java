package org.springmodules.so.core;

import org.springframework.util.Assert;
import org.springmodules.so.core.instance.FlowInstanceDescriptorSource;

import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

/**
 * @author janm
 */
public final class FlowDefinition implements Serializable {
    private static final long serialVersionUID = -4288988924628516588L;
    private String id;
    private Set<StateDefinition> stateDefinitions;
    private FlowInstanceDescriptorSource flowInstanceDescriptorSource;

    public FlowDefinition(final String id) {
        Assert.notNull(id, "The 'id' argument must not be null.");
        
        this.stateDefinitions = new HashSet<StateDefinition>();
        this.id = id;
    }

    public FlowDefinition(Set<StateDefinition> stateDefinitions) {
        this.stateDefinitions = stateDefinitions;
    }

    public FlowInstanceDescriptorSource getFlowInstanceDescriptorSource() {
        return flowInstanceDescriptorSource;
    }

    public void setFlowInstanceDescriptorSource(FlowInstanceDescriptorSource flowInstanceDescriptorSource) {
        this.flowInstanceDescriptorSource = flowInstanceDescriptorSource;
    }

    public Set<StateDefinition> getStates() {
        return stateDefinitions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addState(StateDefinition stateDefinition) {
        if (this.stateDefinitions.contains(stateDefinition)) {
            throw new DuplicateStateDefinitionException("State definition " + stateDefinition + " already exists.", stateDefinition);
        }
        this.stateDefinitions.add(stateDefinition);
        verifySingleStart();
        verifySingleEnd();
    }

    private void verifySingleEnd() {
        int count = 0;
        for (StateDefinition stateDefinition : this.stateDefinitions) {
            if (stateDefinition.isEnd()) count++;
            if (count > 1) throw new MultipleStartOrEndStateException("Multiple end definitions", stateDefinition);
        }
    }

    private void verifySingleStart() {
        int count = 0;
        for (StateDefinition stateDefinition : this.stateDefinitions) {
            if (stateDefinition.isStart()) count++;
            if (count > 1) throw new MultipleStartOrEndStateException("Multiple start definitions", stateDefinition);
        }
    }

    public StateDefinition findState(String id) {
        Assert.notNull(id, "The 'id' argument must not be null.");

        for (StateDefinition stateDefinition : this.stateDefinitions) {
            if (id.equals(stateDefinition.getId())) return stateDefinition;
        }
        throw new NoSuchStateDefinitionException("StateDefinition with id '" + id + "' does not exist.", id);
    }

    public StateDefinition findStartState() {
        for (StateDefinition stateDefinition : this.stateDefinitions) {
            if (stateDefinition.isStart()) return stateDefinition;
        }
        throw new NoStartStateException("The flow " + this + " has no start state.");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FlowDefinition");
        sb.append("{id='").append(id).append('\'');
        sb.append(", stateDefinitions=").append(stateDefinitions);
        sb.append('}');
        return sb.toString();
    }
}
