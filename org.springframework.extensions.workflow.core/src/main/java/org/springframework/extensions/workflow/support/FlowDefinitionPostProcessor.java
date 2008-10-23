package org.springframework.extensions.workflow.support;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.StringUtils;
import org.springframework.extensions.workflow.FlowDefinition;
import org.springframework.extensions.workflow.StateDefinition;
import org.springframework.extensions.workflow.TransitionDefinition;
import org.springframework.extensions.workflow.annotation.Flow;
import org.springframework.extensions.workflow.annotation.State;
import org.springframework.extensions.workflow.annotation.Transition;
import org.springframework.extensions.workflow.support.naming.DefaultStateAndFlowNamingStrategy;
import org.springframework.extensions.workflow.support.naming.DefaultTransitionNamingStrategy;
import org.springframework.extensions.workflow.support.naming.StateAndFlowNamingStrategy;
import org.springframework.extensions.workflow.support.naming.TransitionNamingStrategy;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author janm
 */
public class FlowDefinitionPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware {
    private ListableBeanFactory beanFactory;
    private StateAndFlowNamingStrategy stateNamingStrategy = new DefaultStateAndFlowNamingStrategy("State");
    private StateAndFlowNamingStrategy flowNamingStrategy = new DefaultStateAndFlowNamingStrategy("Flow");
    private TransitionNamingStrategy transitionNamingStrategy = new DefaultTransitionNamingStrategy();

    @SuppressWarnings({"unchecked"})
    public Class predictBeanType(Class beanClass, String beanName) {
        if (beanClass.getAnnotation(State.class) != null) return StateDefinition.class;
        if (beanClass.getAnnotation(Flow.class) != null) return FlowDefinition.class;
        return null;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        State state = bean.getClass().getAnnotation(State.class);
        if (state != null) {
            FlowDefinition flowDefinition = lookupFlowBean(state.flowId());
            Set<TransitionDefinition> transitionDefinitions = createTransitionDefinitions(bean);
            StateDefinition stateDefinition = new StateDefinition();
            String id = this.stateNamingStrategy.generateId(state.id(), bean, beanName);
            stateDefinition.setId(id);
            stateDefinition.setStart(state.start());
            stateDefinition.setEnd(state.end());
            stateDefinition.setTransitionDefinitions(transitionDefinitions);
            
            bean = stateDefinition;
            flowDefinition.addState(stateDefinition);
        }
        Flow flow = bean.getClass().getAnnotation(Flow.class);
        if (flow != null) {
            String id = this.flowNamingStrategy.generateId(flow.id(), bean, beanName);
            FlowDefinition flowDefinition = new FlowDefinition(id);
            flowDefinition.setFlowInstanceDescriptorSource(new KnownClassFlowInstanceDescriptorSource(flow.flowInstanceDescriptorClass()));
            return flowDefinition;
        }
        return bean;
    }

    private FlowDefinition lookupFlowBean(String flowId) {
        Collection flowBeans = this.beanFactory.getBeansOfType(FlowDefinition.class).values();
        if (flowBeans.size() == 1 && !StringUtils.hasText(flowId)) {
            return (FlowDefinition) flowBeans.iterator().next();
        }
        for (Object o : flowBeans) {
            FlowDefinition flowDefinition = (FlowDefinition)o;
            if (flowId.equals(flowDefinition.getId())) return flowDefinition;
        }
        throw new BeanInstantiationException(StateDefinition.class, "Cannot create StateDefinition: Missing bean FlowDefinition with id '" + flowId + "'.");
    }

    private Set<TransitionDefinition> createTransitionDefinitions(Object bean) {
        Set<TransitionDefinition> result = new HashSet<TransitionDefinition>();
        for (Method method : bean.getClass().getMethods()) {
            Transition transition = method.getAnnotation(Transition.class);
            if (transition != null) {
                String id = this.transitionNamingStrategy.generateId(transition.id(), bean, method);
                TransitionDefinition td = new TransitionDefinition(id);
                td.setTargetStateId(transition.to());
                if (StringUtils.hasText(transition.timeout())) {
                    td.setTimeoutExpression(transition.timeout());
                }
                td.setTransitionAction(new KnownMethodInvokingTransitionAction(bean, method));
                if (StringUtils.hasText(transition.roles())) {
                    td.setRoles(transition.roles());
                }
                result.add(td);
            }
        }
        return result;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (!(beanFactory instanceof ListableBeanFactory)) throw new IllegalArgumentException("The BeanFactory must be a ListableBeanFactory");
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }
}
