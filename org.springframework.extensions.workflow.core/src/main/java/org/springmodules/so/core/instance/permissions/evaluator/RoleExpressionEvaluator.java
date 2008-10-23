package org.springmodules.so.core.instance.permissions.evaluator;

import java.io.Serializable;

/**
 * @author janm
 */
public interface RoleExpressionEvaluator extends Serializable{

    boolean evaluate(String expression, String... grantedRoles);

}
