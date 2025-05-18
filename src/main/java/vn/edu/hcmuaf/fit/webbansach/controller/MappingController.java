package vn.edu.hcmuaf.fit.webbansach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MappingController {

    @GetMapping("/")
    public String root() {
        return "redirect:/homePage";
    }

    @GetMapping("/homePage")
    public String homePage() {
        return "index";
    }


    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
