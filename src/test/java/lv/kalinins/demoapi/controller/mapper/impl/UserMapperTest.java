package lv.kalinins.demoapi.controller.mapper.impl;

import lv.kalinins.demoapi.controller.dto.UserInputDto;
import lv.kalinins.demoapi.controller.dto.UserResponseDto;
import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapper target;

    @Mock
    private ModelMapper mapper;

    /**
     * {@link UserMapper#convertToEntity(UserInputDto)}
     */
    @Test
    void shouldMapInputDTOToEntity() {

        UserInputDto dto = new UserInputDto();
        target.convertToEntity(dto);

        verify(mapper, times(1)).map(dto, User.class);
    }

    /**
     * {@link UserMapper#convertToResponseDto(User)}
     */
    @Test
    void shouldMapEntityToResponseDTO() {

        User entity = new User();
        entity.getContracts().add(new Contract());

        UserResponseDto dto = new UserResponseDto();

        when(mapper.map(entity, UserResponseDto.class)).thenReturn(dto);

        UserResponseDto result = target.convertToResponseDto(entity);

        assertSame(dto, result);
        assertEquals(1, result.getNumberOfContracts());

        verify(mapper, times(1)).map(entity, UserResponseDto.class);
    }

    /**
     * {@link UserMapper#convertAllToResponseDto(List)}
     */
    @Test
    void shouldMapAllEntitiesToResponseDTOs() {

        List<User> entities = new ArrayList<>();
        entities.add(new User());
        entities.add(new User());

        when(mapper.map(any(), any())).thenReturn(new UserResponseDto());

        target.convertAllToResponseDto(entities);

        verify(mapper, times(2)).map(any(User.class), ArgumentMatchers.<Class<UserResponseDto>>any());
    }
}