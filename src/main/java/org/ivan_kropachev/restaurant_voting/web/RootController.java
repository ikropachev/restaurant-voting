package org.ivan_kropachev.restaurant_voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "redirect:index";
    }

    @GetMapping("/index")
    public String index() {
        log.info("index");
        return "index";
    }
}
