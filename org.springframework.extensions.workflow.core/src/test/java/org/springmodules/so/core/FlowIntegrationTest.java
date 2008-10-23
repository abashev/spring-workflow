package org.springmodules.so.core;

import static org.hamcrest.collection.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.context.SecurityContextImpl;
import org.springframework.security.providers.anonymous.AnonymousAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springmodules.so.core.flow.LongFlowInstanceDescriptor;
import org.springmodules.so.core.flow.LongFlowInstanceDescriptorPersister;
import org.springmodules.so.core.instance.FlowInstance;
import org.springmodules.so.core.instance.ParametrizedFlowInstanceDescriptorInitializer;
import org.springmodules.so.core.instance.session.FlowSession;

/**
 * @author janm
 */
@ContextConfiguration(locations = {"classpath:/META-INF/spring/flow-integration-test-context.xml"})
public class FlowIntegrationTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    FlowSession flowSession;
    @Autowired
    TransitionHolder lastTransition;
    @Autowired
    LongFlowInstanceDescriptorPersister flowInstanceDescriptorPersister;

    @Test
    public void testWithPermissions() throws InterruptedException {
        setSpringSecurityContext("publisher", "author");
        FlowInstance flowInstance = this.flowSession.start("main", new ParametrizedFlowInstanceDescriptorInitializer<LongFlowInstanceDescriptor>() {
            protected void doInitialize(LongFlowInstanceDescriptor flowInstanceDescriptor) {
                flowInstanceDescriptor.setSomeValue(20L);
            }
        });
        assertThat(flowInstanceDescriptorPersister.getDescriptor().getSomeValue(), equalTo(20L));

        assertThat(flowInstance.getStateDefinitionId(), equalTo("start"));
        long y = 100L;
        flowInstance.performTransition("one", y);
        assertThat(this.flowInstanceDescriptorPersister.getDescriptor().getSomeValue(), equalTo(y));
        assertThat(flowInstance.getStateDefinitionId(), equalTo("start"));
        flowInstance.performTransition("end");
        assertThat(flowInstance.getStateDefinitionId(), equalTo("end"));
        flowInstance.performTransition("restart", 5);
        assertThat(flowInstance.getStateDefinitionId(), equalTo("start"));
        assertThat((Integer) this.lastTransition.getLastTransitionArguments()[0], equalTo(5));
        assertThat(this.lastTransition.getLastTransitionId(), equalTo("EndState.restart"));
        assertThat(this.flowInstanceDescriptorPersister.getDescriptor().getSomeValue(), equalTo(4L));
        
        Thread.sleep(1000L);
        this.flowSession.processTimeouts();
        assertThat(this.flowInstanceDescriptorPersister.getDescriptor().getSomeValue(), equalTo(4L));

        Thread.sleep(2000L);
        this.flowSession.processTimeouts();
        assertThat(this.flowInstanceDescriptorPersister.getDescriptor().getSomeValue(), equalTo(2L));
    }

    @Test
    public void testReturnsState() {
        setSpringSecurityContext("publisher", "author");
        FlowInstance flowInstance = this.flowSession.start("main");

        flowInstance.performTransition("end");
        String to = null;
        flowInstance.performTransition("one", to);
        assertThat(flowInstance.getStateDefinitionId(), equalTo("end"));
        flowInstance.performTransition("one", "start");
        assertThat(flowInstance.getStateDefinitionId(), equalTo("start"));
    }

    @Test(expected = DisallowedTransitionException.class)
    public void testWithoutPermissions() {
        setSpringSecurityContext("publisher");
        FlowInstance flowInstance = this.flowSession.start("main");
        assertThat(flowInstance.getTransitions(), hasItem("end"));
        assertThat(flowInstance.getTransitions().size(), equalTo(1));
        flowInstance.performTransition("one", 1L);
    }

    private void setSpringSecurityContext(String... roles) {
        SecurityContextImpl securityContext = new SecurityContextImpl();
        GrantedAuthority[] grantedAuthorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            grantedAuthorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        securityContext.setAuthentication(new AnonymousAuthenticationToken("me", new Object(), grantedAuthorities));
        SecurityContextHolder.setContext(securityContext);
    }

}
