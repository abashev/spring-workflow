package org.springframework.extensions.workflow.support;

import static org.springframework.extensions.workflow.context.FlowInstanceDescriptorHolder.getFlowInstanceDescriptor;
import static org.springframework.util.Assert.notNull;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.PropertyAccessor;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.extensions.workflow.annotation.Descriptor;
import org.springframework.extensions.workflow.annotation.DescriptorProperty;
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
    private transient Annotation[][] parameterAnnotations;
    private transient String[] parameterNames;

    TransitionMethodInvoker(Object target, Method method) {
        notNull(target, "The 'target' argument must not be null.");
        notNull(method, "The 'method' argument must not be null.");

        this.target = target;
        this.method = method;
        prepareCall();
    }

    TransitionMethodInvoker(Object target, String methodName) {
        notNull(target, "The 'target' argument must not be null.");
        notNull(methodName, "The 'methodName' argument must not be null.");

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
        parameterAnnotations = method.getParameterAnnotations();

        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

        parameterNames = discoverer.getParameterNames(method);
    }

    Object invoke(TransitionActionContext actionContext) throws IllegalAccessException, InvocationTargetException {
        List<Object> arguments = new ArrayList<Object>();
        int fromContext = 0;

        for (int i = 0; i < parameterAnnotations.length; i++)  {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            boolean fromDescriptor = false;

            for (Annotation annotation : parameterAnnotation) {
                if (annotation.annotationType().equals(Descriptor.class)) {
                    fromDescriptor = true;

                    arguments.add(getFlowInstanceDescriptor());

                    break;
                } else if (annotation.annotationType().equals(DescriptorProperty.class)) {
                    fromDescriptor = true;

                    DescriptorProperty a = (DescriptorProperty) annotation;
                    String propertyName = null;

                    if ((a.value() != null) && (a.value().length() != 0)) {
                        propertyName = a.value();
                    } else {
                        propertyName = parameterNames[i];
                    }

                    if (propertyName == null) {
                        throw new IllegalStateException(
                                "Unable to get property name for target " + target +
                                " and method" + method
                        );
                    }

                    Object propertyValue;

                    try {
                        PropertyAccessor accessor = new DirectFieldAccessor(getFlowInstanceDescriptor());

                        propertyValue = accessor.getPropertyValue(propertyName);
                    } catch (BeansException e) {
                        throw new IllegalStateException("Unable to get property value", e);
                    }

                    arguments.add(propertyValue);

                    break;
                }
            }

            if (!fromDescriptor) {
                arguments.add(actionContext.getArguments()[fromContext]);
                fromContext++;
            }
        }

        method.setAccessible(true); // Set accessible for invokation

        final Object ret = ReflectionUtils.invokeMethod(method, target, arguments.toArray());

        if (this.method.getAnnotation(ReturnsState.class) != null) {
            if (ret != null) {
                actionContext.setTargetStateId(ret.toString());
            }
        }

        return ret;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TransitionMethodInvoker");
        sb.append("{method=").append(method);
        sb.append(", target=").append(target);
        sb.append(", parameterAnnotations=").append(parameterAnnotations == null ? "null" : Arrays.asList(parameterAnnotations).toString());
        sb.append('}');
        return sb.toString();
    }
}
