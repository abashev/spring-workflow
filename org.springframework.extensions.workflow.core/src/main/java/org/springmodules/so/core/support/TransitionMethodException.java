package org.springmodules.so.core.support;

import org.springframework.core.NestedRuntimeException;

import java.lang.reflect.Method;

/**
 * @author janm
 */
public class TransitionMethodException extends NestedRuntimeException {
    private static final long serialVersionUID = 6442375164898373045L;
    private Method method;

    public TransitionMethodException(String message, Method method) {
        super(message);
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}
