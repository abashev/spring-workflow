package org.springmodules.so.core.support.naming;

/**
 * @author janm
 */
public interface StateAndFlowNamingStrategy {

    String generateId(String id, Object bean, String beanName);
}
