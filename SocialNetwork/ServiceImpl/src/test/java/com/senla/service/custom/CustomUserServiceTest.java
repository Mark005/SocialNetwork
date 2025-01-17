package com.senla.service.custom;

import com.senla.model.User;
import static com.senla.prototype.UserPrototype.getUser;
import com.senla.repository.UserRepository;
import com.senla.service.CustomUserService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public class CustomUserServiceTest {

    private UserRepository userRepository;
    private CustomUserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new CustomUserServiceImpl(userRepository);
    }

    /**
     * Test of save method, of class CustomUserService.
     */
    @Test
    public void testSave() {
        when(userRepository.save(any())).thenReturn(getUser());
        User user = userService.save(getUser());
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(getUser().getEmail());
    }

}
