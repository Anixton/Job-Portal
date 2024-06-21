package com.anixton.jobportal.util;

import com.anixton.jobportal.entity.Users;
import com.anixton.jobportal.entity.UsersType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of UserDetails for representing user details in Spring Security.
 */
public class CustomUserDetails implements UserDetails {

    private final Users user;

    // Constructor Injection
    @Autowired
    public  CustomUserDetails(Users user) {
        this.user = user;
    }

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return a collection of GrantedAuthority objects representing user roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UsersType usersType = user.getUserTypeId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usersType.getUserTypeName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
