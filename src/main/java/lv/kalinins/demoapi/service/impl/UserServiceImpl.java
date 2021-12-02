package lv.kalinins.demoapi.service.impl;

import lv.kalinins.demoapi.domain.User;
import lv.kalinins.demoapi.repository.UserRepository;
import lv.kalinins.demoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
