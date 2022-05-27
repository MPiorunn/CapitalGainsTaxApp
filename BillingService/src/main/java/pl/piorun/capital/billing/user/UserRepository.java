package pl.piorun.capital.billing.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

public interface UserRepository {

    Optional<User> findByApiKeyId(UUID apiKeyId);

    Optional<User> findById(UUID userId);

    boolean existsByUsername(String username);

    void save(User user);

    Collection<User> getUsers();

    @Service
    class UserRepositoryImpl implements UserRepository {

        private final Set<User> users = new HashSet<>();

        @Override
        public Optional<User> findByApiKeyId(UUID apiKeyId) {
            Objects.requireNonNull(apiKeyId, "ApiKey Id cannot be null!");

            return this.users.stream().filter(user -> apiKeyId.equals(user.getApiKeyId())).findFirst();
        }

        @Override
        public Optional<User> findById(UUID userId) {
            Objects.requireNonNull(userId, "User Id cannot be null!");

            return this.users.stream().filter(user -> userId.equals(user.getId())).findFirst();
        }

        @Override
        public boolean existsByUsername(String username) {
            Objects.requireNonNull(username, "Username cannot be null!");
            return this.users
                .stream()
                .anyMatch(user -> username.equals(user.getUsername()));
        }

        @Override
        public void save(User user) {
            this.users.add(user);
        }

        @Override
        public Collection<User> getUsers() {
            return users;
        }
    }
}
