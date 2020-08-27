package com.bcaf.ivan.FinalProject.Config;

import com.bcaf.ivan.FinalProject.Entity.Agency;
import com.bcaf.ivan.FinalProject.Entity.User;
import com.bcaf.ivan.FinalProject.Util.AgencyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    AgencyDao agencyDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        HttpSession session = request.getSession();
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("owner".equals(auth.getAuthority())) {
                String userId=((User)authentication.getPrincipal()).getId();
                Agency agency = agencyDao.findAgencyByUserId(userId);
                session.setAttribute( "connectedUser" , userId);
                session.setAttribute( "agencyId" , agency.getId());
                response.sendRedirect("/");
            }
        }
    }
}