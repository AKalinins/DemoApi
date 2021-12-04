package lv.kalinins.demoapi.controller.mapper.impl;

import lv.kalinins.demoapi.controller.dto.UserDto;
import lv.kalinins.demoapi.controller.mapper.Mapper;
import lv.kalinins.demoapi.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public UserDto convertToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        userDto.setId(0);
        return mapper.map(userDto, User.class);
    }
}
