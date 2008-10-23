package org.springmodules.so.core.instance;

import java.util.Date;

/**
 * @author janm
 */
public interface FlowInstanceDescriptor {

    String getFlowDefinitionId();

    String getStateDefinitionId();

    void setStateDefinitionId(String stateDefinitionId);

    void setFlowDefinitionId(String flowDefinitionId);

    void setDateEntered(Date date);

    Date getDateEntered();

    boolean isWithTimeouts();

    void setWithTimeouts(boolean hasTimeouts);
    
}
