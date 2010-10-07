package org.springframework.extensions.workflow.instance.permissions.extractor;

import java.io.Serializable;

/**
 * @author janm
 */
public interface RoleExtractor extends Serializable{

    String[] getRoles();

}
