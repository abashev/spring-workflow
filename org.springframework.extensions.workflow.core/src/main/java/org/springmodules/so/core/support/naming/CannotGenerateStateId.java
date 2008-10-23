package org.springmodules.so.core.support.naming;

import org.springframework.core.NestedRuntimeException;

/**
 * @author janm
 */
public class CannotGenerateStateId extends NestedRuntimeException {
    private static final long serialVersionUID = 2739193225510932188L;
    private Object bean;
    private String beanName;

    public CannotGenerateStateId(String msg, Object bean, String beanName) {
        super(msg);
        this.bean = bean;
        this.beanName = beanName;
    }

    public Object getBean() {
        return bean;
    }

    public String getBeanName() {
        return beanName;
    }
}
