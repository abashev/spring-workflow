package org.springmodules.so.sample.simple.annotated;

import org.springmodules.so.core.instance.FlowInstanceDescriptor;

import java.util.Date;

/**
 * @author janm
 */
public class X implements FlowInstanceDescriptor {

    public String getFlowDefinitionId() {
        return null;
    }

    public String getStateDefinitionId() {
        return null;
    }

    public void setStateDefinitionId(String stateDefinitionId) {

    }

    public void setFlowDefinitionId(String flowDefinitionId) {

    }

    public Date getDateEntered() {
        return null;
    }

    public void setWithTimeouts(boolean hasTimeouts) {

    }

    public boolean isWithTimeouts() {
        return false;
    }

    public void setDateEntered(Date date) {
        
    }
    
}
