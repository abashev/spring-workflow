package org.springframework.extensions.workflow.support;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.extensions.workflow.TransitionAction;

/**
 * @author janm
 */
public class MethodInvokingStateTransitionFactoryBean implements FactoryBean, InitializingBean {
    private Object targetObject;
    private String methodName;
    private TransitionAction transition;

    public Object getObject() throws Exception {
        return this.transition;
    }

    public Class getObjectType() {
        return TransitionAction.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(targetObject, "The [targetObject] property of [" + getClass().getName() + "] must not be null.");
        Assert.notNull(methodName, "The [methodName] property of [" + getClass().getName() + "] must not be null.");

        this.transition = new MethodInvokingTransitionAction(this.targetObject, this.methodName);
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
