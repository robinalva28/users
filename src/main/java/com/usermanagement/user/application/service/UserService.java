package com.usermanagement.user.application.service;

import com.usermanagement.user.application.port.in.CreateUserUseCase;
import com.usermanagement.user.application.port.out.UserPort;
import com.usermanagement.user.common.exceptions.BusinessException;
import com.usermanagement.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService implements CreateUserUseCase {

    private final UserPort userPort;

    @Autowired
    public UserService(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public User create(CreateUserCommand createUserCommand) {

        if (userPort.existsByEmail(createUserCommand.getEmail())) {
            throw new BusinessException("Email is already in use");
        }

        return userPort.createUser(
                createUserCommand.getName(),
                createUserCommand.getEmail(),
                createUserCommand.getPassword(),
                createUserCommand.getPhones(),
                "theToken"
        );
    }
}
