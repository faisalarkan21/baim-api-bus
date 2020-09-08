package com.bcaf.ivan.FinalProject.Controller;

import com.bcaf.ivan.FinalProject.Entity.Agency;
import com.bcaf.ivan.FinalProject.Entity.User;
import com.bcaf.ivan.FinalProject.Util.AgencyDao;
import com.bcaf.ivan.FinalProject.Util.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private AgencyDao agencyDao;

    @Autowired
    private ProfileDao profileDao;


    @GetMapping
    @RequestMapping({"/","/index","/dashboard"})
    public String viewDashboard(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String userId=(String)session.getAttribute("connectedUser");
        System.out.println(userId);
        return "index";
    }

    @GetMapping
    @RequestMapping({"/buses"})
    public String viewBus() {

        return "busView";
    }

    @GetMapping
    @RequestMapping({"/agency"})
    public String viewAgency(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);
        String agencyId = (String) session.getAttribute("agencyId");

        Agency agency =  agencyDao.findById(agencyId).get();
        model.addAttribute("agency", agency);
        return "agencyView";
    }

    @GetMapping
    @RequestMapping({"/profile"})
    public String viewProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);
        String agencyId = (String) session.getAttribute("agencyId");

        User user = profileDao.findProfileByUserId(agencyId);
        model.addAttribute("profile",user);
        return "profileView";
    }

    @GetMapping
    @RequestMapping({"/trips"})
    public String viewTrips() {

        return "tripsView";
    }
}
