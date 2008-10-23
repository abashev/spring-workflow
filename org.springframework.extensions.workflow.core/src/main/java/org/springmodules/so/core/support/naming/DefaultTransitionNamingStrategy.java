package org.springmodules.so.core.support.naming;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author janm
 */
public class DefaultTransitionNamingStrategy implements TransitionNamingStrategy {
    private static final String TRANSITION = "Transition";

    public String generateId(String id, Object bean, Method method) {
        if (StringUtils.hasText(id)) return id;
        String methodName = method.getName();
        if (StringUtils.endsWithIgnoreCase(methodName, TRANSITION)) {
            return StringUtils.uncapitalize(methodName.substring(0, methodName.length() - TRANSITION.length()));
        } else {
            return StringUtils.uncapitalize(methodName);
        }
    }
}
