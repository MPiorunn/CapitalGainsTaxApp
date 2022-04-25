package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.db.user;

import com.capital.gains.tax.app.core.domain.user.User;
import com.capital.gains.tax.app.core.domain.user.User.Billing;
import com.capital.gains.tax.app.core.domain.user.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepository {

    private final Set<User> users = new HashSet<>();

    public Optional<User> findById(UUID id) {
        return this.users.stream().filter(user -> id.equals(user.getId())).findFirst();
    }

    public Set<User> getUsers() {
        return this.users;
    }

    @Override
    public Optional<User> findByApiKeyId(UUID apiKeyId) {
        return this.users.stream().filter(user -> apiKeyId.equals(user.getApiKeyId())).findFirst();
    }

    @Override
    public void save(User user) {
        this.users.add(user);
    }

    public User createUser(String username) {
        if (usernameTaken(username)) {
            throw new IllegalArgumentException(String.format("Username %s already taken", username));
        }
        User user = User.builder()
            .id(UUID.randomUUID())
            .username(username)
            .monthlyCost(0.0)
            .billing(Billing.STANDARD)
            .build();
        this.users.add(user);
        return user;
    }

    private boolean usernameTaken(String username) {
        return this.users.stream().anyMatch(user -> username.equals(user.getUsername()));
    }
}
