package org.springframework.extensions.workflow.instance.permissions.extractor;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author janm
 */
public class SpringSecurityRoleExtractor implements RoleExtractor {
    private static final long serialVersionUID = 4445765023644275300L;
    private static final String[] NO_ROLES = new String[0];

    public String[] getRoles() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) return NO_ROLES;
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) return NO_ROLES;
        Collection<GrantedAuthority> authorities = authentication.getAuthorities();
        
        if ((authorities == null) || (authorities.isEmpty())) {
            return NO_ROLES;
        }

        String[] roles = new String[authorities.size()];
        int i = 0;
        
        for (GrantedAuthority authority : authorities) {
            roles[i++] = authority.getAuthority();
        }

        return roles;
    }
}