package org.ivan_kropachev.restaurant_voting.web;

import org.ivan_kropachev.restaurant_voting.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    /*
    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private MenuService menuService;
    */

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

    /*
    @GetMapping("/home")
    public String home() {
        log.info("home");
        return "login";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/dishes")
    public String getDishes(Model model) {
        log.info("dishes");
        model.addAttribute("dishes", dishService.getAll());
        return "dishes";
    }

    @GetMapping("/restaurants")
    public String getRestaurants(Model model) {
        log.info("restaurants");
        model.addAttribute("restaurants", restaurantService.getAll());
        return "restaurants";
    }

    @GetMapping("/votes")
    public String getVotes(Model model) {
        log.info("votes");
        model.addAttribute("votes", voteService.getAll());
        return "votes";
    }

    @GetMapping("/menus")
    public String getMenus(Model model) {
        log.info("menus");
        model.addAttribute("menus", menuService.getAll());
        return "menus";
    }
    */
}
