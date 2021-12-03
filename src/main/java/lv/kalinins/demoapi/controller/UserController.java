package lv.kalinins.demoapi.controller;

import lv.kalinins.demoapi.controller.dto.UserDto;
import lv.kalinins.demoapi.controller.mapper.UserMapper;
import lv.kalinins.demoapi.domain.User;
import lv.kalinins.demoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @PostMapping("")
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        user = userService.save(user);
        return userMapper.convertToDto(user);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable long userId) {
        Optional<User> optionalUser = userService.getById(userId);
        if (optionalUser.isPresent()) {
            return userMapper.convertToDto(optionalUser.get());
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
