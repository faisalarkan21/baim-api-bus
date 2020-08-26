package com.bcaf.ivan.FinalProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping
    @RequestMapping({"/","/index","/dashboard"})
    public String viewDashboard() {
        return "index";
    }

    @GetMapping
    @RequestMapping({"/buses"})
    public String viewBus() {

        return "busView";
    }
}
