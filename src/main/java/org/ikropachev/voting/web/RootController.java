package org.ikropachev.voting.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(description = "Check access for some pages")
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }

    @GetMapping("/")
    @ApiOperation(value = "Redirect to index")
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
