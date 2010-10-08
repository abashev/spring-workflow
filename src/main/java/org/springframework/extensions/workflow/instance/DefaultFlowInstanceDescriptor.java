package org.springframework.extensions.workflow.instance;

import java.util.Date;
import java.io.Serializable;

/**
 * @author janm
 */
public class DefaultFlowInstanceDescriptor implements FlowInstanceDescriptor, Serializable {
    private static final long serialVersionUID = 2653308491709868450L;
    private String flowDefinitionId;
    private String stateDefinitionId;
    private Date dateEntered;
    private boolean withTimeouts;

    DefaultFlowInstanceDescriptor() {

    }

    public void setFlowDefinitionId(String flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
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

    public boolean isWithTimeouts() {
        return withTimeouts;
    }

    public void setWithTimeouts(boolean hasTimeouts) {
        this.withTimeouts = hasTimeouts;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DefaultFlowInstanceDescriptor [" +
        		"flowId=" + flowDefinitionId + ", " +
        		"stateId=" + stateDefinitionId + ", " +
        		"dateEntered=" + dateEntered + ", " +
        		"withTimeouts=" + withTimeouts + "]";
    }
}
