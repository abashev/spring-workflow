package org.springframework.extensions.workflow.support;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.extensions.workflow.annotation.FlowInstanceDescriptorArgument;
import org.springframework.extensions.workflow.annotation.ReturnsState;
import org.springframework.extensions.workflow.context.FlowInstanceDescriptorHolder;
import org.springframework.extensions.workflow.context.TransitionActionContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author janm
 */
class TransitionMethodInvoker {
    private Method method;
    private Object target;
    private transient Annotation[][] parametersAnnotations;

    TransitionMethodInvoker(Object target, Method method) {
        Assert.notNull(target, "The 'target' argument must not be null.");
        Assert.notNull(method, "The 'method' argument must not be null.");

        this.target = target;
        this.method = method;
        prepareCall();
    }

    TransitionMethodInvoker(Object target, String methodName) {
        Assert.notNull(target, "The 'target' argument must not be null.");
        Assert.notNull(methodName, "The 'methodName' argument must not be null.");

        this.target = target;
        for (Method method : ReflectionUtils.getAllDeclaredMethods(this.target.getClass())) {
            if (methodName.equals(method.getName())) {
                this.method = method;
                break;
            }
        }
        if (this.method == null) {
            throw new IllegalArgumentException("Object " + target + " does not define [accessible] method " + methodName);
        }
        prepareCall();
    }

    private void prepareCall() {
        this.parametersAnnotations = this.method.getParameterAnnotations();
    }

    Object invoke(TransitionActionContext actionContext) throws IllegalAccessException, InvocationTargetException {
        List<Object> arguments = new ArrayList<Object>();
        int i = 0;
        for (Annotation[] parameterAnnotation : this.parametersAnnotations) {
            boolean fidParameter = false;
            for (Annotation annotation : parameterAnnotation) {
                if (annotation.annotationType().equals(FlowInstanceDescriptorArgument.class)) {
                    fidParameter = true;
                    break;
                }
            }
            if (!fidParameter) {
                arguments.add(actionContext.getArguments()[i]);
                i++;
            } else {
                arguments.add(FlowInstanceDescriptorHolder.getFlowInstanceDescriptor());
            }
        }

        boolean accessable = this.method.isAccessible();
        Object ret;

        try {
            this.method.setAccessible(true);

            ret = this.method.invoke(this.target, arguments.toArray());

            if (this.method.getAnnotation(ReturnsState.class) != null) {
                if (ret != null) {
                    actionContext.setTargetStateId(ret.toString());
                }
            }
        } finally {
            this.method.setAccessible(accessable);
        }

        return ret;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TransitionMethodInvoker");
        sb.append("{method=").append(method);
        sb.append(", target=").append(target);
        sb.append(", parametersAnnotations=").append(parametersAnnotations == null ? "null" : Arrays.asList(parametersAnnotations).toString());
        sb.append('}');
        return sb.toString();
    }
}
