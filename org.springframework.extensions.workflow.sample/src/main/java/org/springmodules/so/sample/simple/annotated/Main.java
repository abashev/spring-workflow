package org.springmodules.so.sample.simple.annotated;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springmodules.so.core.instance.FlowInstance;
import org.springmodules.so.core.instance.session.FlowSession;

/**
 * @author janm
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("/META-INF/spring/module-annotated-context.xml");
        FlowSession flowSession = (FlowSession)ac.getBean("flowSession");
        FlowInstance flowInstance = flowSession.start("main");
        System.out.println(flowInstance);
        flowInstance.performTransition("1");
        System.out.println(flowInstance);
    }
}
