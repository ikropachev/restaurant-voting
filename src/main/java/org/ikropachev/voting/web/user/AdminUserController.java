package org.ikropachev.voting.web.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ikropachev.voting.View;
import org.ikropachev.voting.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Operations for users from admin")
public class AdminUserController extends AbstractUserController {

    static final String REST_URL = "/rest/admin/users";
    private static final String USER_ID_STR = "100005";
    private static final String USER_FOR_DELETE_ID_STR = "100006";
    private static final String USER_FOR_UPDATE_ID_STR = "100007";

    @Override
    @GetMapping
    @ApiOperation(value = "View a list of all users")
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "View the user by id")
    public User get(@PathVariable @ApiParam(example = USER_ID_STR, required = true) int id) {
        return super.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create the user")
    public ResponseEntity<User> createWithLocation(@Validated(View.Web.class) @RequestBody User user) {
        User created = super.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete the user by id")
    public void delete(@PathVariable @ApiParam(example = USER_FOR_DELETE_ID_STR, required = true) int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update the user by id")
    public void update(@Validated(View.Web.class) @RequestBody User user,
                       @PathVariable @ApiParam(example = USER_FOR_UPDATE_ID_STR, required = true) int id) {
        super.update(user, id);
    }

    @Override
    @GetMapping("/by-email")
    @ApiOperation(value = "View the user by e-mail")
    public User getByMail(@RequestParam @ApiParam(example = "user@gmail.com", required = true) String email) {
        return super.getByMail(email);
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Set enable status for the user")
    public void enable(@PathVariable @ApiParam(example = USER_FOR_UPDATE_ID_STR, required = true) int id,
                       @RequestParam @ApiParam(example = "false", required = true) boolean enabled) {
        super.enable(id, enabled);
    }
}
