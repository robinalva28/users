package com.usermanagement.user.adapter.web.out.persistence.adapter;

import com.usermanagement.user.adapter.web.out.persistence.User.UserRepository;
import com.usermanagement.user.application.port.out.UserPort;
import com.usermanagement.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements UserPort {


    private UserRepository userRepository;

    @Autowired
    UserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
