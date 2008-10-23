package org.springmodules.so.core.context;

import org.springframework.util.Assert;
import org.springmodules.so.core.instance.FlowInstanceDescriptor;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * @author janm
 */
public final class FlowInstanceDescriptorHolder implements Serializable{
    private static final long serialVersionUID = 969547478248429191L;
    private static final String STRATEGY_THREADLOCAL = "threadlocal";
    private static Map<String, FlowInstanceDescriptorHolderStrategy> strategies;
    private static String currentStrategy;

    static {
        strategies = new HashMap<String, FlowInstanceDescriptorHolderStrategy>();
        strategies.put(STRATEGY_THREADLOCAL, new ThreadLocalFlowInstanceDescriptorHolderStrategy());
        currentStrategy = STRATEGY_THREADLOCAL;
    }

    private static FlowInstanceDescriptorHolderStrategy getStrategy() {
        return strategies.get(currentStrategy);
    }

    public static void setStrategy(String strategy) {
        Assert.notNull(currentStrategy, "The 'currentStrategy' argument must not be null.");
        currentStrategy = strategy;
    }

    @SuppressWarnings({"unchecked"})
    public static <T extends FlowInstanceDescriptor> T getFlowInstanceDescriptor() {
        return (T) getStrategy().getDescriptor();
    }

    public static void setFlowInstanceDescriptor(FlowInstanceDescriptor descriptor) {
        getStrategy().setDescriptor(descriptor);
    }

}
