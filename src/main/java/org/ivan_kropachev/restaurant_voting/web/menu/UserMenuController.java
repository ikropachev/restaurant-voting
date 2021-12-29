package org.ivan_kropachev.restaurant_voting.web.menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = UserMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for menus from regular user")
public class UserMenuController extends AbstractMenuController {
    private static final Logger log = getLogger(AdminMenuController.class);

    static final String REST_URL = "/rest/user/menus";

    @GetMapping
    @ApiOperation(value = "View a list of all menus for current date")
    public List<Menu> getAllForToday() {
        LocalDate date = LocalDate.now();
        log.info("get all menus for date {}", date);
        return super.getAllByDate(date);
    }
}
