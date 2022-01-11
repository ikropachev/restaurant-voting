package org.ikropachev.voting.web.menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ikropachev.voting.model.Menu;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public static final String TODAY_MENU_ID_STR = "100012";

    @GetMapping
    @ApiOperation(value = "View a list of all menus for current date")
    public List<Menu> getAllForToday() {
        LocalDate date = LocalDate.now();
        log.info("get all menus for date {}", date);
        return super.getAllByDate(date);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View a menu with dishes")
    public Menu get(@PathVariable @ApiParam(example = TODAY_MENU_ID_STR, required = true) int id) {
        log.info("get menu with id {}", id);
        return super.get(id);
    }
}
