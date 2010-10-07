package org.springframework.extensions.workflow;

/**
 * @author janm
 */
public class NoSuchStateDefinitionException extends FlowRuntimeException {
    private String id;
    private static final long serialVersionUID = 529067128789819284L;

    protected NoSuchStateDefinitionException(String msg, String id) {
        super(msg);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
