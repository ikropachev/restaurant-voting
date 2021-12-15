package org.ivan_kropachev.restaurant_voting.web.menu;


import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.service.MenuService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = UserMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMenuController extends AbstractMenuController {
    private static final Logger log = getLogger(AdminMenuController.class);

    static final String REST_URL = "/rest/user/menus";

    @GetMapping
    public List<Menu> getAllByDate() {
        log.info("get all menus for date {}", LocalDate.now());
        return super.getAllByDate(LocalDate.now());
    }
}
