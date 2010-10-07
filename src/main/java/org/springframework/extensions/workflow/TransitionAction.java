package org.springframework.extensions.workflow;

import org.springframework.extensions.workflow.context.TransitionActionContext;

/**
 * @author janm
 */
public interface TransitionAction {

    void perform(TransitionActionContext actionContext) throws Exception;
    
}