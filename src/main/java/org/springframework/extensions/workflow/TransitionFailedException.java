package org.springframework.extensions.workflow;

/**
 * @author janm
 */
public class TransitionFailedException extends FlowRuntimeException {
    private static final long serialVersionUID = 3134355646058761007L;
    private String transitionId;

    public TransitionFailedException(String msg, String transitionId, Throwable cause) {
        super(msg, cause);
        this.transitionId = transitionId;
    }

    public String getTransitionId() {
        return transitionId;
    }
}
