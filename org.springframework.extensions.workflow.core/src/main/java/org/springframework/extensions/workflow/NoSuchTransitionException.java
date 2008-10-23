package org.springframework.extensions.workflow;

/**
 * @author janm
 */
public class NoSuchTransitionException extends FlowRuntimeException {
    private static final long serialVersionUID = -2382696055492449040L;
    private String id;

    public NoSuchTransitionException(String msg, String id) {
        super(msg);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
