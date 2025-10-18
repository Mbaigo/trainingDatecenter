package com.mbaigo.trainingtools.training_tools.security.services.jwt_service;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {
    private final Utilisateur user;

    public UserPrincipal(Utilisateur user) { this.user = user; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .toList();
    }


    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getEmail(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return user.isEnabled(); }
    public boolean isTwoFactorEnabled() { return user.isTwoFactorEnabled(); }
    public String getSecret2FA() { return user.getSecret2FA(); }
    public Utilisateur getUser() { return user; }
}
