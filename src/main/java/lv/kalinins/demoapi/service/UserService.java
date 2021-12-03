package lv.kalinins.demoapi.service;

import lv.kalinins.demoapi.domain.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> getById(long userId);
}
