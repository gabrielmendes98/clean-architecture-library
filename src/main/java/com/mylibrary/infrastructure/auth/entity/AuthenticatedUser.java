package com.mylibrary.infrastructure.auth.entity;

import com.mylibrary.domain.user.User;
import com.mylibrary.domain.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthenticatedUser implements UserDetails {
    private final String username;
    private final String password;
    private final String role;
    
    private AuthenticatedUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static AuthenticatedUser from(final User user) {
        return new AuthenticatedUser(
                user.getDocument(),
                user.getPassword().getValue(),
                user.getRole().name()
        );
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        if (getRole().equals(UserRole.ATTENDANT.name())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ATTENDANT"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
