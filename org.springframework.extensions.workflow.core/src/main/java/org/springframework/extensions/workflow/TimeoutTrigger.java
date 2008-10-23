package org.springframework.extensions.workflow;

import java.util.Date;

/**
 * @author janm
 */
public interface TimeoutTrigger {

    boolean trigger(Date enteredDate,
                    String expression);

}
