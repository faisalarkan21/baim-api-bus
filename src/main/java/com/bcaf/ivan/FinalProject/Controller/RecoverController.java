package com.bcaf.ivan.FinalProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RecoverController {
    @GetMapping
    @RequestMapping("/recover")
    public String recover() {
        return "recover";
    }
}
