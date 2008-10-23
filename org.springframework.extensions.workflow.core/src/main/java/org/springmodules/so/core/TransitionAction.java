package org.springmodules.so.core;

import org.springmodules.so.core.context.TransitionActionContext;

/**
 * @author janm
 */
public interface TransitionAction {

    void perform(TransitionActionContext actionContext) throws Exception;
    
}