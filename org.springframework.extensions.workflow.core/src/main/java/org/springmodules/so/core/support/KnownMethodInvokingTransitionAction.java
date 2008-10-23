package org.springmodules.so.core.support;

import org.springmodules.so.core.TransitionAction;
import org.springmodules.so.core.context.TransitionActionContext;

import java.lang.reflect.Method;

/**
 * @author janm
 */
class KnownMethodInvokingTransitionAction implements TransitionAction {
    private transient TransitionMethodInvoker transitionMethodInvoker;

    public KnownMethodInvokingTransitionAction(Object target, Method method) {
        this.transitionMethodInvoker = new TransitionMethodInvoker(target, method);
    }

    public void perform(TransitionActionContext actionContext) throws Exception {
        this.transitionMethodInvoker.invoke(actionContext);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("KnownMethodInvokingTransitionAction");
        sb.append("{transitionMethodInvoker=").append(transitionMethodInvoker);
        sb.append('}');
        return sb.toString();
    }
}
