package lv.kalinins.demoapi.controller;

import lv.kalinins.demoapi.controller.dto.UserInputDto;
import lv.kalinins.demoapi.controller.dto.UserResponseDto;
import lv.kalinins.demoapi.controller.mapper.impl.UserMapper;
import lv.kalinins.demoapi.domain.User;
import lv.kalinins.demoapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController target;

    @Mock
    private UserService service;
    @Mock
    private UserMapper mapper;

    /**
     * {@link UserController#addUser(UserInputDto)}
     */
    @Test
    void shouldAddUserToDatabase() {

        UserInputDto inputDto = new UserInputDto();
        User user = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(mapper.convertToEntity(inputDto)).thenReturn(user);
        when(mapper.convertToResponseDto(user)).thenReturn(responseDto);
        when(service.save(user)).thenReturn(user);

        UserResponseDto result = target.addUser(inputDto);

        assertSame(responseDto, result);
        verify(mapper, times(1)).convertToEntity(inputDto);
        verify(service, times(1)).save(user);
        verify(mapper, times(1)).convertToResponseDto(user);
    }

    /**
     * {@link UserController#addUser(UserInputDto)}
     */
    @Test
    void shouldReturnBadRequestStatusInCaseOfDataIntegrityViolationException() {

        UserInputDto inputDto = new UserInputDto();
        User user = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(mapper.convertToEntity(inputDto)).thenReturn(user);
        when(service.save(user)).thenThrow(DataIntegrityViolationException.class);

        UserResponseDto result = null;
        ResponseStatusException exception = null;
        try {
            result = target.addUser(inputDto);
        } catch (ResponseStatusException e) {
            exception = e;
        }

        assertNull(result);
        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(mapper, times(1)).convertToEntity(inputDto);
        verify(service, times(1)).save(user);
        verify(mapper, times(0)).convertToResponseDto(user);
    }

    /**
     * {@link UserController#getUser(long)}
     */
    @Test
    void shouldGetUserById() {

        User user = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(mapper.convertToResponseDto(user)).thenReturn(responseDto);
        when(service.getById(1L)).thenReturn(Optional.of(user));

        UserResponseDto result = target.getUser(1L);

        assertSame(responseDto, result);
        verify(service, times(1)).getById(1L);
        verify(mapper, times(1)).convertToResponseDto(user);
    }

    /**
     * {@link UserController#getUser(long)}
     */
    @Test
    void shouldReturnNotFoundStatusIfUserNotFound() {

        User user = new User();

        when(service.getById(1L)).thenReturn(Optional.empty());

        UserResponseDto result = target.getUser(1L);

        assertNull(result);
        verify(service, times(1)).getById(1L);
        verify(mapper, times(0)).convertToResponseDto(user);
    }
}