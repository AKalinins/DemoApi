package lv.kalinins.demoapi.controller;

import lv.kalinins.demoapi.controller.dto.ContractResponseDto;
import lv.kalinins.demoapi.controller.dto.UserInputDto;
import lv.kalinins.demoapi.controller.dto.UserResponseDto;
import lv.kalinins.demoapi.controller.mapper.impl.ContractMapper;
import lv.kalinins.demoapi.controller.mapper.impl.UserMapper;
import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.User;
import lv.kalinins.demoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;
    private ContractMapper contractMapper;

    @PostMapping("")
    public UserResponseDto addUser(@RequestBody UserInputDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        try {
            user = userService.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid request body");
        }
        return userMapper.convertToResponseDto(user);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable long userId) {
        Optional<User> optionalUser = userService.getById(userId);
        return optionalUser.map(user -> userMapper.convertToResponseDto(user)).orElse(null);
    }

    @GetMapping("/{userId}/contracts")
    public List<ContractResponseDto> getUserContracts(@PathVariable long userId) {
        Optional<User> optionalUser = userService.getById(userId);
        if (optionalUser.isPresent()) {
            List<Contract> contracts = optionalUser.get().getContracts();
            return contractMapper.convertAllToResponseDto(contracts);
        }
        return Collections.emptyList();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setContractMapper(ContractMapper contractMapper) {
        this.contractMapper = contractMapper;
    }
}
