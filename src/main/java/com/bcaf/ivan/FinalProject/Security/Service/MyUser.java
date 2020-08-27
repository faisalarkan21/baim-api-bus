package com.bcaf.ivan.FinalProject.Security.Service;

import com.bcaf.ivan.FinalProject.Entity.User;
import com.bcaf.ivan.FinalProject.Entity.UserExt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

public class MyUser implements UserDetails {

    private UserExt user;

    public MyUser(UserExt user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRole());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass=user.getPassword();
        String ecPas=encoder.encode(pass);
        return ecPas;
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
