package uikt.uiktpteamretrobnd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.service.UserService;
import uikt.uiktpteamretrobnd.web.requests.UserRequest;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController extends ResponseEntityExceptionHandler {
    private final UserService service;

    private final CustomResponse response;

    @Autowired
    public UserController(UserService userService, CustomResponse response) {
        this.service = userService;
        this.response = response;
    }

    @GetMapping
    public ApiResponse<List<User>> index() {
        List<User> users = service.findAll();

        return this.response.success(users);
    }

    @GetMapping("/{id}")
    public ApiResponse<User> show(@PathVariable("id") Long id) {
        Optional<User> user = service.find(id);

        if (user.isPresent()) {
            return this.response.success(user);
        } else {
            return this.response.notFound(user);
        }
    }

    @PostMapping
    public ApiResponse<User> create(@ModelAttribute UserRequest userRequest) throws IOException {
        User user = service.create(userRequest);

        return this.response.created(user);
    }

    @PostMapping("/{id}")
    public ApiResponse<User> update(@PathVariable("id") Long id, @ModelAttribute UserRequest userRequest) throws IOException {
        User user = service.update(id, userRequest);

        return this.response.success(user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);

        if (deleted) {
            return response.deleted();
        } else {
            return this.response.notFound(null);
        }
    }
}