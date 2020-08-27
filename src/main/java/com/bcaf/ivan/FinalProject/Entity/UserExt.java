package com.bcaf.ivan.FinalProject.Entity;

import com.bcaf.ivan.FinalProject.Util.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserExt extends User implements UserDetails {

    @Autowired
    private RoleDao roleDao;

    private List<Role> role;

    public UserExt() {
    }

    public UserExt(User u) {
        this.setId(u.getId());
        this.setFirstName(u.getFirstName());
        this.setLastName(u.getLastName());
        this.setEmail(u.getEmail());
        this.setPassword(u.getPassword());
        this.setCreatedDate(u.getCreatedDate());
        this.setMobileNumber(u.getMobileNumber());
        this.setRoleId(u.getRoleId());
        this.setUpdatedDate(u.getUpdatedDate());
    }
    public UserExt(User u,List<Role> roleList) {
        this.setId(u.getId());
        this.setFirstName(u.getFirstName());
        this.setLastName(u.getLastName());
        this.setEmail(u.getEmail());
        this.setPassword(u.getPassword());
        this.setCreatedDate(u.getCreatedDate());
        this.setMobileNumber(u.getMobileNumber());
        this.setRoleId(u.getRoleId());
        this.setUpdatedDate(u.getUpdatedDate());
        this.role=roleList;
    }

    @Override
    public List<Role> getAuthorities() {
        if(this.role==null){
            List<Role> r =roleDao.findNameByRole(this.getRoleId());
            this.role.addAll(r);
        }
        return this.role;
    }


    @Override
    public String getUsername() {
        return this.getEmail();
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
