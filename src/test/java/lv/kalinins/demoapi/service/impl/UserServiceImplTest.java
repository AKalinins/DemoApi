package lv.kalinins.demoapi.service.impl;

import lv.kalinins.demoapi.domain.User;
import lv.kalinins.demoapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl target;

    @Mock
    private UserRepository repository;

    /**
     * {@link UserServiceImpl#save(User)}
     */
    @Test
    void testSave() {

        User user = new User();

        when(repository.save(user)).thenReturn(user);

        User result = target.save(user);

        assertSame(user, result);
        verify(repository, times(1)).save(user);
    }

    /**
     * {@link UserServiceImpl#getById(long)}
     */
    @Test
    void testGetById() {

        User user = new User();

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = target.getById(1L);

        assertTrue(result.isPresent());
        assertSame(user, result.get());
        verify(repository, times(1)).findById(1L);
    }
}