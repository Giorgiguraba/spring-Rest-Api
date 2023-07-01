package com.example.spring.rest.api.entities.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.spring.rest.api.entities.enums.Permission.CUSTOMER_ADD;
import static com.example.spring.rest.api.entities.enums.Permission.CUSTOMER_READ;

public enum Role {
    ADMIN(Set.of(CUSTOMER_READ, CUSTOMER_ADD));
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(i -> new SimpleGrantedAuthority(i.getPermission()))
                .collect(Collectors.toList());
        //authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        authorities.add(new SimpleGrantedAuthority(name()));
        return authorities;
    }
}
