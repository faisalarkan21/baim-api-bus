package com.bcaf.ivan.FinalProject.Security.Service;

import com.bcaf.ivan.FinalProject.Entity.Role;
import com.bcaf.ivan.FinalProject.Entity.User;
import com.bcaf.ivan.FinalProject.Entity.UserExt;
import com.bcaf.ivan.FinalProject.Util.RoleDao;
import com.bcaf.ivan.FinalProject.Util.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findEmailValidation(username);
        user.setActive(true);
        UserExt ut=new UserExt(user);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        userDao.save(user);
        List<GrantedAuthority> authorities = getUserAuthority(roleDao.findNameByRole(user.getRoleId()));
        return new MyUser(ut);
    }


//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String userName) {
//        User user = userDao.findEmailValidation(userName);
//        List<GrantedAuthority> authorities = getUserAuthority(roleDao.findNameByRole(user.getRoleId()));
//        return buildUserForAuthentication(user, authorities);
//    }

    private List<GrantedAuthority> getUserAuthority(Role userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles.getRole()));
        return new ArrayList<>(roles);
    }

}
