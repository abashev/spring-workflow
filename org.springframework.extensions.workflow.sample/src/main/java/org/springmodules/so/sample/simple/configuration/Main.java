package org.springmodules.so.sample.simple.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springmodules.so.core.FlowDefinition;

/**
 * @author janm
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("/META-INF/spring/module-xml-context.xml");
        FlowDefinition flowDefinition = (FlowDefinition)ac.getBean("flowDefinition");
        System.out.println(flowDefinition);
    }

}