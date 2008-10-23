package org.springmodules.so.core.instance.session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.util.ClassUtils;
import org.springmodules.so.core.*;
import org.springmodules.so.core.instance.*;
import org.springmodules.so.core.instance.permissions.extractor.RoleExtractor;
import org.springmodules.so.core.instance.permissions.extractor.SpringSecurityRoleExtractor;

import java.util.Iterator;
import java.util.Map;
import java.util.Date;
import java.io.Serializable;

/**
 * @author janm
 */
public class LocalFlowSession implements InitializingBean, BeanFactoryAware, FlowSession, Serializable {
    private ListableBeanFactory beanFactory;
    private static final Log logger = LogFactory.getLog(LocalFlowSession.class);
    private static final String DEFAULT_PERSISTER = "default";
    private Map<String, FlowInstanceDescriptorPersister> flowInstanceDescriptorPersisters;
    private RoleExtractor roleExtractor;
    private static final long serialVersionUID = 2858255163637178309L;

    public LocalFlowSession() {
        if (ClassUtils.isPresent("org.springframework.security.context.SecurityContextHolder")) {
            this.roleExtractor = new SpringSecurityRoleExtractor();
        }
        if (logger.isDebugEnabled() && this.roleExtractor != null) {
            logger.debug("Automatically guessed RoleExtractor as " + roleExtractor);
        }
    }

    protected void persist(FlowInstanceDescriptor descriptor) {
        FlowInstanceDescriptorPersister persister = findPersister(descriptor.getClass());
        if (persister != null) {
            persister.persist(descriptor);
        }
    }

    protected FlowInstanceDescriptorPersister findPersister(Class<? extends FlowInstanceDescriptor> clazz) {
        FlowInstanceDescriptorPersister persister = this.flowInstanceDescriptorPersisters.get(clazz.getName());
        if (persister != null) return persister;
        return this.flowInstanceDescriptorPersisters.get(DEFAULT_PERSISTER);
    }

    public final void afterPropertiesSet() throws Exception {
        if (this.flowInstanceDescriptorPersisters == null) {
            this.flowInstanceDescriptorPersisters.put(DEFAULT_PERSISTER, new DefaultFlowInstanceDescriptorPersister());
        }
    }

    private FlowInstance doStart(String id, FlowInstanceDescriptorCreator creator, FlowInstanceDescriptorInitializer initializer) {
        Map flowDefinitions = this.beanFactory.getBeansOfType(FlowDefinition.class);
        for (Object o : flowDefinitions.values()) {
            FlowDefinition flowDefinition = (FlowDefinition)o;
            if (id.equals(flowDefinition.getId())) {
                FlowInstanceDescriptor descriptor;
                if (creator != null) {
                    descriptor = creator.create();
                } else {
                    descriptor = flowDefinition.getFlowInstanceDescriptorSource().createDescriptor();
                }

                StateDefinition stateDefinition = flowDefinition.findStartState();
                descriptor.setStateDefinitionId(stateDefinition.getId());
                descriptor.setFlowDefinitionId(id);
                descriptor.setDateEntered(new Date());
                descriptor.setWithTimeouts(stateDefinition.hasTimeouts());

                if (initializer != null) initializer.initialize(descriptor);

                FlowInstance instance = new FlowInstanceDelegate(flowDefinition, descriptor,
                        findPersister(descriptor.getClass()),
                        getRoleExtractor());
                persist(descriptor);
                return instance;
            }
        }
        throw new NoSuchFlowDefinitionException("Flow definition with id '" + id + "' does not exist.");
    }

    public FlowInstance start(String id) {
        return doStart(id, null, null);
    }

    public FlowInstance start(String id, FlowInstanceDescriptorCreator instanceDescriptorCreator) {
        return doStart(id, instanceDescriptorCreator, null);
    }

    public FlowInstance start(String id, FlowInstanceDescriptorInitializer instanceDescriptorInitializer) {
        return doStart(id, null, instanceDescriptorInitializer);
    }

    public FlowInstance find(FlowInstanceDescriptor descriptor) {
        Map flowDefinitions = this.beanFactory.getBeansOfType(FlowDefinition.class);
        for (Object o : flowDefinitions.values()) {
            FlowDefinition flowDefinition = (FlowDefinition)o;
            if (flowDefinition.getId().equals(descriptor.getFlowDefinitionId())) {
                return new FlowInstanceDelegate(flowDefinition, descriptor,
                        findPersister(descriptor.getClass()),
                        getRoleExtractor());
            }
        }
        throw new NoSuchFlowDefinitionException("Flow definition with descriptor '" + descriptor + "' does not exist.");
    }

    public void processTimeouts() {
        for (FlowInstanceDescriptorPersister persister : this.flowInstanceDescriptorPersisters.values()) {
            Iterator<? extends FlowInstanceDescriptor> iterator = persister.getDescriptorsWithTimeoutsAndDateInPast();
            if (iterator == null) continue;
            while (iterator.hasNext()) {
                FlowInstanceDescriptor descriptor = iterator.next();
                processInstanceTimeouts(descriptor);
            }
        }
    }

    private void processInstanceTimeouts(FlowInstanceDescriptor descriptor) {
        FlowInstance flowInstance = find(descriptor);
        for (TransitionDefinition td : flowInstance.getTransitionDefinitions()) {
            final String expression = td.getTimeoutExpression();
            if (expression == null) continue;
            TimeoutTrigger trigger = getTimeoutTrigger(expression);
            if (trigger.trigger(flowInstance.getDateEntered(), expression)) {
                flowInstance.performTransition(td.getId());
            }
        }
    }

    private TimeoutTrigger getTimeoutTrigger(String expression) {
        return new SimpleTimeoutTrigger();
    }


    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (!(beanFactory instanceof ListableBeanFactory)) throw new IllegalArgumentException("The BeanFactory must be a ListableBeanFactory");
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    protected final RoleExtractor getRoleExtractor() {
        return this.roleExtractor;
    }

    public void setFlowInstanceDescriptorPersisters(Map<String, FlowInstanceDescriptorPersister> flowInstanceDescriptorPersisters) {
        this.flowInstanceDescriptorPersisters = flowInstanceDescriptorPersisters;
    }

    public void setRoleExtractor(RoleExtractor roleExtractor) {
        this.roleExtractor = roleExtractor;
    }

}
