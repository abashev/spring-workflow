package org.springframework.extensions.workflow.flow;

import org.springframework.extensions.workflow.instance.FlowInstanceDescriptor;

import java.util.Date;

/**
 * @author janm
 */
public class LongFlowInstanceDescriptor implements FlowInstanceDescriptor{
    private String flowDefinitionId;
    private String stateDefinitionId;
    private Date dateEntered;
    private boolean hasTimeouts;
    private Long someValue;

    public LongFlowInstanceDescriptor() {
        
    }

    public String getFlowDefinitionId() {
        return this.flowDefinitionId;
    }

    public String getStateDefinitionId() {
        return this.stateDefinitionId;
    }

    public void setStateDefinitionId(String stateDefinitionId) {
        this.stateDefinitionId = stateDefinitionId;
    }

    public void setFlowDefinitionId(String flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }

    public Long getSomeValue() {
        return someValue;
    }

    public void setSomeValue(Long someValue) {
        this.someValue = someValue;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public boolean isWithTimeouts() {
        return hasTimeouts;
    }

    public void setWithTimeouts(boolean hasTimeouts) {
        this.hasTimeouts = hasTimeouts;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }
    public FlowInstanceDescriptor getFlowInstanceDescriptor() {
        return this;
    }
}
