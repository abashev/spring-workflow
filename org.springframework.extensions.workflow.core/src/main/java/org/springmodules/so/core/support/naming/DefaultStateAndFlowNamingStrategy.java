package org.springmodules.so.core.support.naming;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author janm
 */
public final class DefaultStateAndFlowNamingStrategy implements StateAndFlowNamingStrategy {
    private final String allowedEnding;

    public DefaultStateAndFlowNamingStrategy(final String allowedEnding) {
        Assert.notNull(allowedEnding, "The 'allowedEnding' argument must not be null.");
        this.allowedEnding = allowedEnding;
    }

    public String generateId(String userId, Object bean, String beanName) {
        if (StringUtils.hasText(userId)) return userId;

        String id;
        String simpleClassName = bean.getClass().getSimpleName();
        String fullClassName = bean.getClass().getName();
        if (beanName.startsWith(fullClassName)) {
            // this is an anonymous bean => do not consider it at all
            id = generateIdFromClassName(simpleClassName);
        } else {
            // onymous bean => possibly consider its name
            id = generateIdFromClassName(simpleClassName);
            if (id == null) id = beanName;
        }
        if (id == null)
            throw new CannotGenerateStateId("Cannot generate id for state " + bean + " (beanName '" + beanName + "').", bean, beanName);
        return id;
    }

    private String generateIdFromClassName(String simpleClassName) {
        if (StringUtils.endsWithIgnoreCase(simpleClassName, this.allowedEnding)) {
            return StringUtils.uncapitalize(simpleClassName.substring(0, simpleClassName.length() - this.allowedEnding.length()));
        } else {
            return StringUtils.uncapitalize(simpleClassName);
        }
    }
}
