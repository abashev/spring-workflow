package org.springframework.extensions.workflow.support.naming;

import java.lang.reflect.Method;

/**
 * @author janm
 */
public interface TransitionNamingStrategy {

    String generateId(String id, Object bean, Method method);
}
