package lv.kalinins.demoapi.controller.mapper.impl;

import lv.kalinins.demoapi.controller.dto.UserInputDto;
import lv.kalinins.demoapi.controller.dto.UserResponseDto;
import lv.kalinins.demoapi.controller.mapper.Mapper;
import lv.kalinins.demoapi.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements Mapper<User, UserInputDto, UserResponseDto> {

    private ModelMapper modelMapper;

    @Override
    public UserResponseDto convertToResponseDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public User convertToEntity(UserInputDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public List<UserResponseDto> convertAllToResponseDto(List<User> entities) {
        List<UserResponseDto> result = new ArrayList<>();
        for (User entity : entities) {
            result.add(convertToResponseDto(entity));
        }
        return result;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
