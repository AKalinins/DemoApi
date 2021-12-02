package lv.kalinins.demoapi.controller;

import lv.kalinins.demoapi.domain.User;
import lv.kalinins.demoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
