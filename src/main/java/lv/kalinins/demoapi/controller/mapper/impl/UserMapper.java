package lv.kalinins.demoapi.controller.mapper.impl;

import lv.kalinins.demoapi.controller.dto.UserInputDto;
import lv.kalinins.demoapi.controller.dto.UserResponseDto;
import lv.kalinins.demoapi.controller.mapper.Mapper;
import lv.kalinins.demoapi.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserInputDto, UserResponseDto> {

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public UserResponseDto convertToResponseDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public User convertToEntity(UserInputDto userDto) {
        return mapper.map(userDto, User.class);
    }
}
