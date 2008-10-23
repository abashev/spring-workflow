package org.springframework.extensions.workflow.support;

import org.springframework.extensions.workflow.context.TransitionActionContext;
import org.springframework.extensions.workflow.TransitionAction;
import org.springframework.util.Assert;

/**
 * @author janm
 */
public class MethodInvokingTransitionAction implements TransitionAction {
    private transient TransitionMethodInvoker transitionMethodInvoker;

    public MethodInvokingTransitionAction(Object target, String methodName) throws NoSuchMethodException, ClassNotFoundException {
        Assert.notNull(target, "The 'target' argument must not be null.");
        Assert.notNull(methodName, "The 'methodName' argument must not be null.");

        this.transitionMethodInvoker = new TransitionMethodInvoker(target,  methodName);
    }

    public void perform(TransitionActionContext actionContext) throws Exception {
        this.transitionMethodInvoker.invoke(actionContext);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MethodInvokingTransitionAction");
        sb.append("{transitionMethodInvoker=").append(transitionMethodInvoker);
        sb.append('}');
        return sb.toString();
    }
}
