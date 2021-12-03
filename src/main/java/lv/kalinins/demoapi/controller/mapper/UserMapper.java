package lv.kalinins.demoapi.controller.mapper;

import lv.kalinins.demoapi.controller.dto.UserDto;
import lv.kalinins.demoapi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private Mapper mapper;

    public UserDto convertToDto(User user) {
        UserDto userDto = mapper.getModelMapper().map(user, UserDto.class);
        return userDto;
    }

    public User convertToEntity(UserDto userDto) {
        User user = mapper.getModelMapper().map(userDto, User.class);
        return user;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
