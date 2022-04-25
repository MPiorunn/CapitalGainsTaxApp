package com.capital.gains.tax.app.core.infrastructure.adapters.inbound.rest.user;

import com.capital.gains.tax.app.core.domain.user.User;
import com.capital.gains.tax.app.core.infrastructure.adapters.outbound.db.user.UserService;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public Set<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.of(userService.findById(id));
    }

    @PostMapping("/users")
    public User createUser(@RequestBody String username) {
        return userService.createUser(username);
    }

}
