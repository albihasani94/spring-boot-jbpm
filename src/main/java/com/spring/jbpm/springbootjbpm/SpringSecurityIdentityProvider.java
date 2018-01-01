package com.spring.jbpm.springbootjbpm;

import org.kie.internal.identity.IdentityProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpringSecurityIdentityProvider implements IdentityProvider {
    @Override
    public String getName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return auth.getName();
        }
        return "System";
    }

    @Override
    public List<String> getRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            List<String> roles = new ArrayList<>();

            auth.getAuthorities()
                    .stream()
                    .forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority()));

            return roles;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean hasRole(String role) {
        return false;
    }
}
