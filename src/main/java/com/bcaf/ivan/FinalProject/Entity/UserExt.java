package com.bcaf.ivan.FinalProject.Entity;

import com.bcaf.ivan.FinalProject.Util.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserExt extends User {
    @Autowired
    private RoleDao roleDao;
    private Role role;

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
    public Role getRole() {
        if(this.role==null){
            this.role=roleDao.findIdByRole(this.getRoleId());
        }
        return this.role;
    }
}
