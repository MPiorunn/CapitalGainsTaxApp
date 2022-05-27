package pl.piorun.capital.billing.user;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.piorun.capital.billing.test.factory.UserTestFactory;

public class UserFacadeTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserFacade userFacade;

    @BeforeEach
    public void setup() {
        userFacade = new UserFacade(userRepository);
    }

    @Test
    public void shouldGetUsers() {
        List<User> testUsers = List.of(UserTestFactory.createUserWithoutKey());
        Mockito.when(userRepository.getUsers())
            .thenReturn(testUsers);

        Collection<User> users = userFacade.getUsers();

        Assertions.assertEquals(testUsers, users);
    }

    @Test
    public void shouldThrowWhenUsernameTaken() {
        String username = "John Snow";

        Mockito.when(userRepository.existsByUsername(username)).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userFacade.createUser(username));
    }

    @Test
    public void shouldCreateAndSaveUserWhenUsernameNotTaken() {
        String username = "John Snow";

        Mockito.when(userRepository.existsByUsername(username)).thenReturn(false);

        Assertions.assertDoesNotThrow(() -> userFacade.createUser(username));
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

}
