package org.springmodules.so.core.instance.permissions.extractor;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;

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
        GrantedAuthority[] authorities = authentication.getAuthorities();
        if (authorities == null) return NO_ROLES;

        String[] roles = new String[authorities.length];
        for (int i = 0; i < authorities.length; i++) {
            roles[i] = authorities[i].getAuthority();
        }

        return roles;
    }
}
