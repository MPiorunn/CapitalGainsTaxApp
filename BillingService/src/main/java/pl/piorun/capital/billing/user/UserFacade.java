package pl.piorun.capital.billing.user;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pl.piorun.capital.billing.user.User.Billing;

@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public Collection<User> getUsers() {
        return userRepository.getUsers();
    }

    public UUID createUser(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException(String.format("Username %s already taken", username));
        }
        User user = User.builder()
            .id(UUID.randomUUID())
            .username(username)
            .monthlyCost(0.0)
            .billing(Billing.STANDARD)
            .build();
        userRepository.save(user);
        return user.getApiKeyId();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}
