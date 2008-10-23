package org.springmodules.so.core.instance.session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springmodules.so.core.*;
import org.springmodules.so.core.context.FlowInstanceDescriptorHolder;
import org.springmodules.so.core.context.TransitionActionContext;
import org.springmodules.so.core.instance.FlowInstance;
import org.springmodules.so.core.instance.FlowInstanceDescriptor;
import org.springmodules.so.core.instance.FlowInstanceDescriptorPersister;
import org.springmodules.so.core.instance.TransitionDefinitionFilter;
import org.springmodules.so.core.instance.permissions.CannotExtractRolesException;
import org.springmodules.so.core.instance.permissions.evaluator.AntlrRoleExpressionEvaluator;
import org.springmodules.so.core.instance.permissions.evaluator.RoleExpressionEvaluator;
import org.springmodules.so.core.instance.permissions.extractor.RoleExtractor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.io.Serializable;

/**
 * @author janm
 */
public class FlowInstanceDelegate implements FlowInstance {
    private static final Log logger = LogFactory.getLog(FlowInstanceDelegate.class);
    private static final TransitionDefinitionFilter<FlowInstanceDescriptor> DEFAULT_FILTER = new AllIncludedFilter();

    private FlowInstanceDescriptor flowInstanceDescriptor;
    private FlowInstanceDescriptorPersister flowInstanceDescriptorPersister;
    private FlowDefinition flowDefinition;
    private RoleExtractor roleExtractor;
    private RoleExpressionEvaluator roleExpressionEvaluator = new AntlrRoleExpressionEvaluator();

    public FlowInstanceDelegate(FlowDefinition flowDefinition, FlowInstanceDescriptor flowInstanceDescriptor,
                         FlowInstanceDescriptorPersister flowInstanceDescriptorPersister, RoleExtractor roleExtractor) {
        Assert.notNull(flowDefinition, "The 'flowDefinition' argument must not be null.");
        Assert.notNull(flowInstanceDescriptor, "The 'flowInstanceDescriptor' argument must not be null.");
        Assert.notNull(flowInstanceDescriptorPersister, "The 'flowInstanceDescriptorPersister' argument must not be null.");
        Assert.notNull(roleExtractor, "The 'roleExtractor' argument must not be null.");

        this.flowInstanceDescriptor = flowInstanceDescriptor;
        this.flowInstanceDescriptorPersister = flowInstanceDescriptorPersister;
        this.flowDefinition = flowDefinition;
        this.roleExtractor = roleExtractor;
    }

    public String getFlowDefinitionId() {
        return this.flowInstanceDescriptor.getFlowDefinitionId();
    }

    public String getStateDefinitionId() {
        return this.flowInstanceDescriptor.getStateDefinitionId();
    }

    public void setStateDefinitionId(String stateDefinitionId) {
        this.flowInstanceDescriptor.setDateEntered(new Date());
        this.flowInstanceDescriptor.setStateDefinitionId(stateDefinitionId);
    }

    public void setFlowDefinitionId(String flowDefinitionId) {
        this.flowInstanceDescriptor.setFlowDefinitionId(flowDefinitionId);
    }

    public Date getDateEntered() {
        return this.flowInstanceDescriptor.getDateEntered();
    }

    public void setWithTimeouts(boolean hasTimeouts) {
        this.flowInstanceDescriptor.setWithTimeouts(hasTimeouts);
    }

    public boolean isWithTimeouts() {
        return this.flowInstanceDescriptor.isWithTimeouts();
    }

    public void setDateEntered(Date date) {
        this.flowInstanceDescriptor.setDateEntered(date);
    }

    public void performTransition(String id, Object... arguments) {
        String targetStateId;

        StateDefinition stateDefinition = this.flowDefinition.findState(getStateDefinitionId());
        TransitionDefinition transitionDefinition = stateDefinition.findTransitionDefinition(id);
        targetStateId = transitionDefinition.getTargetStateId();
        TransitionActionContext transitionActionContext = new TransitionActionContext(targetStateId, arguments);
        if (StringUtils.hasText(targetStateId)) assertStateExists(targetStateId);
        if (!canPerformTransition(transitionDefinition)) {
            throw new DisallowedTransitionException("Cannot perform transition " + id + ". Permission denied.");
        }
        try {
            FlowInstanceDescriptorHolder.setFlowInstanceDescriptor(this.flowInstanceDescriptor);
            transitionDefinition.getTransitionAction().perform(transitionActionContext);
            targetStateId = transitionDefinition.getTargetStateId();
            if (transitionActionContext.getTargetStateId() != null) {
                targetStateId = transitionActionContext.getTargetStateId();
            }
            if (StringUtils.hasText(targetStateId)) {
                setStateDefinitionId(assertStateExists(targetStateId).getId());
            }
            this.flowInstanceDescriptorPersister.persist(this.flowInstanceDescriptor);
        } catch (Exception e) {
            throw new TransitionFailedException("Could not perform transition '" + id + "' on " + getStateDefinitionId(), id, e);
        }
    }

    private boolean canPerformTransition(TransitionDefinition transitionDefinition) {
        String roleExpression = transitionDefinition.getRoles();
        if (roleExpression != null) {
            if (this.roleExtractor == null) throw new CannotExtractRolesException("Cannot extract roles. RoleExtractor is missing.");
            String[] roles = this.roleExtractor.getRoles();
            if (logger.isDebugEnabled()) {
                logger.debug("Evaluating role expression '" + roleExpression + "' against " + Arrays.toString(roles));
            }
            return this.roleExpressionEvaluator.evaluate(roleExpression, roles);
        }
        return true;
    }

    public Set<String> getTransitions() {
        return getTransitions(DEFAULT_FILTER);
    }

    @SuppressWarnings({"unchecked"})
    public <T extends FlowInstanceDescriptor> Set<String> getTransitions(TransitionDefinitionFilter<T> filter) {
        Assert.notNull(filter, "The 'filter' argument must not be null.");

        StateDefinition stateDefinition = this.flowDefinition.findState(getStateDefinitionId());
        Set<String> transitions = new HashSet<String>();
        T descriptor = (T) this.flowInstanceDescriptor;
        for (TransitionDefinition transitionDefinition : stateDefinition.getTransitionDefinitions()) {
            if (transitionDefinition.isInternal()) continue;
            boolean add = canPerformTransition(transitionDefinition);
            if (!add) continue;
            if (filter != DEFAULT_FILTER) {
                add = filter.filter(transitionDefinition, descriptor);
            }
            if (add) transitions.add(transitionDefinition.getId());
        }
        return transitions;
    }

    public Set<TransitionDefinition> getTransitionDefinitions() {
        return getTransitionDefinitions(DEFAULT_FILTER);
    }

    @SuppressWarnings({"unchecked"})
    public <T extends FlowInstanceDescriptor> Set<TransitionDefinition> getTransitionDefinitions(TransitionDefinitionFilter<T> filter) {
        Assert.notNull(filter, "The 'filter' argument must not be null.");

        StateDefinition stateDefinition = this.flowDefinition.findState(getStateDefinitionId());
        Set<TransitionDefinition> transitions = new HashSet<TransitionDefinition>();
        T descriptor = (T) this.flowInstanceDescriptor;
        for (TransitionDefinition transitionDefinition : stateDefinition.getTransitionDefinitions()) {
            if (transitionDefinition.isInternal()) continue;
            boolean add = canPerformTransition(transitionDefinition);
            if (!add) continue;
            if (filter != DEFAULT_FILTER) {
                add = filter.filter(transitionDefinition, descriptor);
            }
            if (add) transitions.add(transitionDefinition);
        }
        return transitions;
    }

    private StateDefinition assertStateExists(String stateId) {
        if (stateId == null) throw new IllegalTransitionException("Target state is missing.");

        StateDefinition stateDefinition = flowDefinition.findState(stateId);
        if (stateDefinition == null) throw new NoSuchStateException("State with id '" + stateId + "' does not exist.", stateId);
        return stateDefinition;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FlowInstanceDelegate");
        sb.append("{flowInstanceDescriptor=").append(this.flowInstanceDescriptor);
        sb.append(", flowDefinition=").append(this.flowDefinition);
        sb.append('}');
        return sb.toString();
    }
    public FlowInstanceDescriptor getFlowInstanceDescriptor() {
        return this.flowInstanceDescriptor;
    }

    private static class AllIncludedFilter implements TransitionDefinitionFilter<FlowInstanceDescriptor>, Serializable {

        public boolean filter(TransitionDefinition transitionDefinition, FlowInstanceDescriptor descriptor) {
            return true;
        }
    }
    
    

}
