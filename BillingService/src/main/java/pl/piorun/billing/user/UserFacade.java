package pl.piorun.billing.user;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.piorun.billing.user.User.Billing;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public Collection<User> getUsers() {
        return userRepository.getUsers();
    }

    public User createUser(String username) {
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
        return user;
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}
